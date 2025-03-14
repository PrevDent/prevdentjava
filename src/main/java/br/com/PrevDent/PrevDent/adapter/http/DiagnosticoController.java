package br.com.PrevDent.PrevDent.adapter.http;

import br.com.PrevDent.PrevDent.adapter.http.dto.mapper.DiagnosticoDtoMapper;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.DiagnosticoCreateDto;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.DiagnosticoUpdateRequest;
import br.com.PrevDent.PrevDent.domain.model.Diagnostico;
import br.com.PrevDent.PrevDent.usecase.service.DiagnosticoService;
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
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @Autowired
    private DiagnosticoDtoMapper diagnosticoDtoMapper;

    @Operation(summary = "Cadastrar um novo diagnóstico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnóstico cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PostMapping
    public ResponseEntity<Diagnostico> cadastrarDiagnostico(@RequestBody @Valid DiagnosticoCreateDto diagnosticoCreateDto) {

        Diagnostico diagnostico = diagnosticoDtoMapper.converteCreateDiagnostico(diagnosticoCreateDto);

        diagnosticoService.cadastrarDiagnostico(diagnostico);

        return ResponseEntity.ok(diagnostico);
    }

    @Operation(summary = "Listar todos os diagnósticos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de diagnósticos retornada com sucesso")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diagnostico>> listarDiagnosticos() {
        List<Diagnostico> diagnosticos = diagnosticoService.listarDiagnosticos();
        return ResponseEntity.ok(diagnosticos);
    }

    @Operation(summary = "Atualizar um diagnóstico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnóstico atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diagnóstico não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Diagnostico> atualizarDiagnostico(@PathVariable String id, @RequestBody @Valid DiagnosticoUpdateRequest diagnosticoUpdateRequest) {

        Diagnostico diagnostico = diagnosticoDtoMapper.converterDiagnosticoDto(diagnosticoUpdateRequest);

        Optional<Diagnostico> atualiza = diagnosticoService.atualizarDiagnostico(id, diagnostico);
        if (atualiza.isPresent()) {
            return ResponseEntity.ok(atualiza.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar um diagnóstico por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnóstico encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diagnóstico não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> buscarDiagnosticoPorID(@PathVariable String id) {
        Diagnostico diagnostico = diagnosticoService.buscarDiagnostico(id);
        if (diagnostico != null) {
            return new ResponseEntity<>(diagnostico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deletar um diagnóstico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Diagnóstico deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diagnóstico não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiagnostico(@PathVariable String id) {
        boolean deletado = diagnosticoService.excluirDiagnostico(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
