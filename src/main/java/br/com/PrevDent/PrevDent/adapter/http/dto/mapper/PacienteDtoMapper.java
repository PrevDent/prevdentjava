package br.com.PrevDent.PrevDent.adapter.http.dto.mapper;

import br.com.PrevDent.PrevDent.adapter.http.dto.request.PacienteCreateRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.PacienteLoginRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.PacienteUpdateRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.response.PacienteListaResponse;
import br.com.PrevDent.PrevDent.domain.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteDtoMapper {

    public Paciente converterPacienteDto(PacienteCreateRequest pacienteCreateRequest) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteCreateRequest.getNome());
        paciente.setCpf(pacienteCreateRequest.getCpf());
        paciente.setDataNascimento(pacienteCreateRequest.getDataNascimento());
        paciente.setSenha(pacienteCreateRequest.getSenha());
        paciente.setRole(pacienteCreateRequest.getRole());
        return paciente;
    }


    public Paciente converterPacienUpdateteDto(PacienteUpdateRequest pacienteUpdateDtoDto) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteUpdateDtoDto.getNome());
        return paciente;
    }

    public Paciente converterPacienteListaPaciente(Paciente paciente) {
        PacienteListaResponse response = new PacienteListaResponse();
        response.setIdPaciente(paciente.getIdPaciente());
        response.setNome(paciente.getNome());
        response.setDataNascimento(paciente.getDataNascimento());
        return paciente;
    }

    public Paciente converterPacienteLoginDto(PacienteLoginRequest pacienteLoginRequest) {
        Paciente paciente = new Paciente();
        paciente.setCpf(pacienteLoginRequest.getCpf());
        paciente.setSenha(pacienteLoginRequest.getSenha());
        return paciente;
    }


}
