package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteUpdateRequest {

    @JsonProperty("id_paciente")
    private String idPaciente;

    @JsonProperty("nome")
    @NotNull
    @NotEmpty
    private String nome;
}
