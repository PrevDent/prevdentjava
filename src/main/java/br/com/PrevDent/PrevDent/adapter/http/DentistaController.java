package br.com.PrevDent.PrevDent.adapter.http;

import br.com.PrevDent.PrevDent.adapter.http.dto.mapper.DentistaDtoMapper;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.DentistaCreatRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.DentistaUpdateRequest;
import br.com.PrevDent.PrevDent.domain.model.Dentista;
import br.com.PrevDent.PrevDent.usecase.service.DentistaService;
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
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @Autowired
    private DentistaDtoMapper dentistaDtoMapper;


    @Operation(summary = "Cadastrar um novo dentista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentista cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PostMapping
    public ResponseEntity<Dentista> cadastrarDentista(@RequestBody @Valid DentistaCreatRequest dentistaCreatRequest) {

        Dentista dentista = dentistaDtoMapper.createDentista(dentistaCreatRequest);

        dentistaService.cadastrarDentista(dentista);
        return ResponseEntity.ok(dentista);
    }

    @Operation(summary = "Listar todos os dentistas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de dentistas retornada com sucesso")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarDentistas() {
        List<Dentista> dentistas = dentistaService.listarDentistas();
        return ResponseEntity.ok(dentistas);
    }

    @Operation(summary = "Atualizar um dentista existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentista atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Dentista não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Dentista> atualizarDentista(@PathVariable String id, @RequestBody @Valid DentistaUpdateRequest dentistaUpdateRequest) {

        Dentista dentista = dentistaDtoMapper.converterDentistaUpdateteDto(dentistaUpdateRequest);

        Optional<Dentista> atualiza = dentistaService.atualizarDentista(id, dentista);
        if (atualiza.isPresent()) {
            return ResponseEntity.ok(atualiza.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar um dentista por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dentista encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Dentista não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscarDentistaPorID(@PathVariable String id) {
        Dentista dentista = dentistaService.buscarDentista(id);
        if (dentista != null) {
            return new ResponseEntity<>(dentista, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deletar um dentista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dentista deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Dentista não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Dentista> deletarDentista(@PathVariable String id) {
        boolean deletado = dentistaService.removerDentista(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
