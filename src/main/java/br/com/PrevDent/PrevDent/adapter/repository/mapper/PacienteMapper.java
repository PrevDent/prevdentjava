package br.com.PrevDent.PrevDent.adapter.repository.mapper;

import br.com.PrevDent.PrevDent.adapter.repository.entity.PacienteEntity;
import br.com.PrevDent.PrevDent.domain.model.Paciente;
import br.com.PrevDent.PrevDent.domain.user.PacienteUserRole;
import br.com.PrevDent.PrevDent.infra.security.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final SecurityConfiguration securityConfig;

    public Paciente converterPaciente(PacienteEntity pacienteEntity){
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteEntity.getNome());
        paciente.setCpf(pacienteEntity.getCpf());
        paciente.setDataNascimento(pacienteEntity.getDataNascimento());
        paciente.setIdPaciente(pacienteEntity.getIdPaciente());
        return paciente;
    }

    public PacienteEntity converterPacienteEntity(Paciente paciente){
        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntity.setNome(paciente.getNome());
        pacienteEntity.setCpf(paciente.getCpf());
        pacienteEntity.setSenha(securityConfig.passwordEncoder().encode(paciente.getSenha()));
        pacienteEntity.setDataNascimento(paciente.getDataNascimento());
        pacienteEntity.setRole(PacienteUserRole.Admin);
        pacienteEntity.setIdPaciente(paciente.getIdPaciente());
        return pacienteEntity;
    }

    public Paciente converterAtualizacaoDoPaciente(PacienteEntity pacienteEntity){
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteEntity.getNome());
        return paciente;
    }

    public void atualizarPacienteEntity(PacienteEntity pacienteEntity, Paciente paciente){
        pacienteEntity.setNome(paciente.getNome());

        if (paciente.getSenha() != null && !paciente.getSenha().isEmpty()) {
            pacienteEntity.setSenha(securityConfig.passwordEncoder().encode(paciente.getSenha()));
        }
    }
}
