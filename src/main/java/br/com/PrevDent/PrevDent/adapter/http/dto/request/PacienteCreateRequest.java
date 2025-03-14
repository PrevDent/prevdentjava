package br.com.PrevDent.PrevDent.adapter.http.dto.request;


import br.com.PrevDent.PrevDent.domain.user.PacienteUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteCreateRequest {

    @JsonProperty("id_paciente")
    private String idPaciente;

    @JsonProperty("nome")
    @NotNull
    @NotEmpty
    private String nome;

    @JsonProperty("cpf")
    @NotBlank(message = "CPF não pode estar vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @JsonProperty("data_nascimento")
    @NotNull
    @NotEmpty
    private String dataNascimento;

    @JsonProperty("senha")
    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "Senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial"
    )
    private String senha;

    private PacienteUserRole role;

    @JsonProperty("consultas")
    private List<ConsultaCreatRequest> consultas;
}
