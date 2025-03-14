package br.com.PrevDent.PrevDent.adapter.http;

import br.com.PrevDent.PrevDent.adapter.http.dto.request.PacienteCreateRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.PacienteLoginRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.PacienteUpdateRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.mapper.PacienteDtoMapper;
import br.com.PrevDent.PrevDent.domain.model.Paciente;
import br.com.PrevDent.PrevDent.usecase.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteDtoMapper pacienteDtoMapper;

    @Autowired
    private PacienteModelAssembler pacienteModelAssembler;

    @Operation(summary = "Cadastrar um novo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid PacienteCreateRequest pacienteDto) {

        Paciente paciente = pacienteDtoMapper.converterPacienteDto(pacienteDto);

        pacienteService.cadastarPaciente(paciente);

        return ResponseEntity.ok(paciente);
    }

    @Operation(summary = "Realizar login do paciente e obter um token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid PacienteLoginRequest pacienteLoginRequest){

        String token = pacienteService.ValidarLogin(pacienteLoginRequest.getCpf(), pacienteLoginRequest.getSenha());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar todos os pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Paciente>>> listarPacientes() {

        List<EntityModel<Paciente>> pacientes = pacienteService.listarPacientes().stream()
                .map(pacienteModelAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(pacientes,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PacienteController.class).listarPacientes()).withSelfRel()));
    }

    @Operation(summary = "Atualizar um paciente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação na requisição")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable String id, @RequestBody @Valid PacienteUpdateRequest pacienteUpdateDtoDto) {

        Paciente paciente = pacienteDtoMapper.converterPacienUpdateteDto(pacienteUpdateDtoDto);

        Optional<Paciente> atualizado = pacienteService.atualizarPaciente(id, paciente);
        if (atualizado.isPresent()) {

            return ResponseEntity.ok(atualizado.get());

        } else {

            return ResponseEntity.notFound().build();

        }
    }

    @Operation(summary = "Buscar um paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Paciente>> buscarPacientePorId(@PathVariable String id) {

        Paciente paciente = pacienteService.buscarPaciente(id);

        return ResponseEntity.ok(pacienteModelAssembler.toModel(paciente));
    }

    @Operation(summary = "Deletar um paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Paciente> deletarPaciente(@PathVariable String id) {

        boolean deletado = pacienteService.excluirPaciente(id);

        if (deletado) {

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();

        }
    }
}
