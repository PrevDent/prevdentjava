package br.com.PrevDent.PrevDent.adapter.http;

import br.com.PrevDent.PrevDent.adapter.http.dto.response.PacienteListaResponse;
import br.com.PrevDent.PrevDent.domain.model.Paciente;
import br.com.PrevDent.PrevDent.adapter.http.PacienteController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PacienteModelAssembler implements RepresentationModelAssembler<Paciente, EntityModel<Paciente>> {

    @Override
    public EntityModel<Paciente> toModel(Paciente paciente) {
        return EntityModel.of(paciente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PacienteController.class).buscarPacientePorId(paciente.getIdPaciente())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PacienteController.class).listarPacientes()).withRel("pacientes"));
    }

}