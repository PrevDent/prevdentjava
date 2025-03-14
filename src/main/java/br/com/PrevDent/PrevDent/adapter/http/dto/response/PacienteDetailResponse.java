package br.com.PrevDent.PrevDent.adapter.http.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteDetailResponse {

    @JsonProperty("id_paciente")
    private String idPaciente;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("data_nascimento")
    private String dataNascimento;
}
