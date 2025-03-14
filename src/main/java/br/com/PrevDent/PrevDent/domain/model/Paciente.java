package br.com.PrevDent.PrevDent.domain.model;

import br.com.PrevDent.PrevDent.domain.user.PacienteUserRole;
import lombok.Data;
import java.util.UUID;

@Data
public class Paciente {

    private String idPaciente;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String senha;
    private PacienteUserRole role;

    public Paciente() {
        this.idPaciente = UUID.randomUUID().toString();
    }
}
