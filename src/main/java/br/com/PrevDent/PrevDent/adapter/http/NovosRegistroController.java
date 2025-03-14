package br.com.PrevDent.PrevDent.adapter.http;

import br.com.PrevDent.PrevDent.adapter.http.dto.mapper.NovosRegistroDtoMapper;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.NovosRegistroCreatRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.NovosRegistroUpdateRequest;
import br.com.PrevDent.PrevDent.domain.exception.NovosRegistroNotFoundException;
import br.com.PrevDent.PrevDent.domain.model.NovosRegistro;
import br.com.PrevDent.PrevDent.usecase.service.NovosRegistroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/novos-registros")
public class NovosRegistroController {

    @Autowired
    private NovosRegistroService novosRegistroService;

    @Autowired
    private NovosRegistroDtoMapper novosRegistroDtoMapper;

    @Operation(summary = "Cadastrar um novo registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<NovosRegistro> cadastrarRegistro(@RequestBody @Valid NovosRegistroCreatRequest novosRegistroCreateRequest) {

        NovosRegistro novosRegistro = novosRegistroDtoMapper.converteCreateNovosRegistro(novosRegistroCreateRequest);

        novosRegistroService.cadastrarRegistro(novosRegistro);
        return ResponseEntity.ok(novosRegistro);
    }

    @Operation(summary = "Listar todos os registros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de registros retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<NovosRegistro>> listarRegistros() {
        List<NovosRegistro> registros = novosRegistroService.listarRegistros();
        return ResponseEntity.ok(registros);
    }

    @Operation(summary = "Atualizar um registro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<NovosRegistro> atualizarRegistro(@PathVariable String id, @RequestBody @Valid NovosRegistroUpdateRequest novosRegistroUpdateRequest) {

        NovosRegistro novosRegistro = novosRegistroDtoMapper.converterNovosRegistroDto(novosRegistroUpdateRequest);

        Optional<NovosRegistro> registroAtualizado = novosRegistroService.atualizarRegistro(id, novosRegistro);

        return registroAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar um registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable String id) {
        boolean deletado = novosRegistroService.deletarRegistro(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar um registro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NovosRegistro> detalharRegistro(@PathVariable String id) {
        try {
            NovosRegistro registro = novosRegistroService.detalharRegistro(id);
            return ResponseEntity.ok(registro);
        } catch (NovosRegistroNotFoundException e) {
            return ResponseEntity.status(e.HTTP_STATUS_CODE).body(null);
        }
    }
}