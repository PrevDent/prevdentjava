package br.com.PrevDent.PrevDent.adapter.http;


import br.com.PrevDent.PrevDent.adapter.http.dto.mapper.ConsultaDtoMapper;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.ConsultaCreatRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.ConsultaUpdateRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.PerguntaRequest;
import br.com.PrevDent.PrevDent.domain.model.Consulta;
import br.com.PrevDent.PrevDent.usecase.service.ConsultaService;
import br.com.PrevDent.PrevDent.usecase.service.serviceImpl.ConsultaIAServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaDtoMapper consultaDtoMapper;

    @Autowired
    private ConsultaIAServiceImpl consultaIAService;



    @Operation(summary = "Cadastrar uma nova consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody @Valid ConsultaCreatRequest consultaCreateRequest) {

        Consulta consulta = consultaDtoMapper.criandoDtoParaConsulta(consultaCreateRequest);

        log.info("Cadastrando consulta: {}", consulta);

        consultaService.cadastrarConsulta(consulta);

        log.info("Consulta cadastrada com sucesso: {}", consulta);

        return ResponseEntity.ok(consulta);
    }

    @Operation(summary = "Listar todas as consultas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Consulta>> listarConsultas() {

        List<Consulta> consultas = consultaService.listarConsultas();

        log.info("Listando consultas: {}", consultas);

        return ResponseEntity.ok(consultas);
    }


    @Operation(summary = "Atualizar uma consulta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable String id, @RequestBody @Valid ConsultaUpdateRequest consultaUpdateRequest) {

        Consulta consulta = consultaDtoMapper.converterConsultaUpdateteDto(consultaUpdateRequest);

        log.info("Atualizando consulta com ID {}: {}", id, consulta);

        Optional<Consulta> consultaAtualizada = consultaService.atualizarConsulta(id, consulta);
        if (consultaAtualizada.isPresent()) {
            log.info("Consulta atualizada com sucesso: {}", consultaAtualizada.get());
            return ResponseEntity.ok(consultaAtualizada.get());
        } else {
            log.warn("Consulta com ID {} não encontrada", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar uma consulta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaPorID(@PathVariable String id) {
        Consulta consulta = consultaService.buscarConsulta(id);

        if (consulta != null) {
            log.info("Consulta encontrada: {}", consulta);
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } else {
            log.warn("Consulta com ID {} não encontrada", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deletar uma consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Consulta deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable String id) {
        boolean deletado = consultaService.excluirConsulta(id);

        if (deletado) {
            log.info("Consulta com ID {} deletada com sucesso", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Consulta com ID {} não encontrada para deleção", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<Consulta>> minhasConsultas(@RequestHeader("Authorization") String authorizationHeader) {

        String tokenJwt = authorizationHeader.replace("Bearer ", "");

        List<Consulta> consultas = consultaService.listarConsultasPorEmail(tokenJwt);

        return ResponseEntity.ok(consultas);
    }


    @GetMapping("/ia/resumo/{id}")
    public ResponseEntity<String> gerarResumo(@PathVariable String id) {

        Consulta consulta = consultaService.buscarConsulta(id);

        String resumo = consultaIAService.gerarResumoConsulta(consulta);

        return ResponseEntity.ok(resumo);
    }

    @GetMapping("/ia/prioridade/{id}")
    public ResponseEntity<String> classificar(@PathVariable String id) {

        Consulta consulta = consultaService.buscarConsulta(id);

        String prioridade = consultaIAService.classificarPrioridade(consulta);

        return ResponseEntity.ok(prioridade);
    }

    @PostMapping("/ia/pergunta")
    public ResponseEntity<String> responderPergunta(@RequestBody PerguntaRequest perguntaRequest) {

        log.info("Pergunta recebida: {}", perguntaRequest.pergunta());

        List<Consulta> consultas = consultaService.listarConsultas();

        String resposta = consultaIAService.responderPergunta(perguntaRequest.pergunta(), consultas);

        log.info("Resposta gerada: {}", resposta);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/ia/traduzir/{id}")
    public ResponseEntity<String> traduzirConsulta
            (@PathVariable String id,
             @RequestParam String idioma) {

        Consulta consulta = consultaService.buscarConsulta(id);

        String traduzido = consultaIAService.traduzirConsulta(consulta, idioma);

        return ResponseEntity.ok(traduzido);
    }
}
