package br.com.PrevDent.PrevDent.adapter.http;


import br.com.PrevDent.PrevDent.adapter.http.dto.mapper.ConsultaDtoMapper;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.ConsultaCreatRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.ConsultaUpdateRequest;
import br.com.PrevDent.PrevDent.domain.model.Consulta;
import br.com.PrevDent.PrevDent.usecase.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ConsultaDtoMapper consultaDtoMapper;



    @Operation(summary = "Cadastrar uma nova consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody @Valid ConsultaCreatRequest consultaCreateRequest) {
        Consulta consulta = consultaDtoMapper.criandoDtoParaConsulta(consultaCreateRequest);

        consultaService.cadastrarConsulta(consulta);
        return ResponseEntity.ok(consulta);
    }

    @Operation(summary = "Listar todas as consultas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consulta>> listarConsultas() {
        List<Consulta> consultas = consultaService.listarConsultas();
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

        Optional<Consulta> consultaAtualizada = consultaService.atualizarConsulta(id, consulta);
        if (consultaAtualizada.isPresent()) {
            return ResponseEntity.ok(consultaAtualizada.get());
        } else {
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
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } else {
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
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
