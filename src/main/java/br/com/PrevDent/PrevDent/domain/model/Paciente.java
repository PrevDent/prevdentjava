package br.com.PrevDent.PrevDent.domain.model;

import br.com.PrevDent.PrevDent.domain.user.PacienteUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.UUID;

@Data
public class Paciente {

    private String idPaciente;
    private String nome;
    @JsonIgnore
    private String email;
    private String cpf;
    private String dataNascimento;
    @JsonIgnore
    private String senha;
    @JsonIgnore
    private PacienteUserRole role;

    public Paciente() {
        this.idPaciente = UUID.randomUUID().toString();
    }
}
