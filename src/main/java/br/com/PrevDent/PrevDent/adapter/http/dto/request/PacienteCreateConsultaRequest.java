package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import br.com.PrevDent.PrevDent.domain.user.PacienteUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteCreateConsultaRequest {

    @JsonProperty("id_paciente")
    private String idPaciente;

    @JsonProperty("nome")
    @NotNull
    private String nome;

    @JsonProperty("cpf")
    @NotBlank(message = "CPF não pode estar vazio")
    private String cpf;

    @JsonProperty("data_nascimento")
    @NotNull
    private String dataNascimento;


    @JsonProperty("consultas")
    private List<ConsultaCreatRequest> consultas;
}
