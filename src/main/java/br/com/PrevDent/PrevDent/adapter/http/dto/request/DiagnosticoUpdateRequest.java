package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticoUpdateRequest {

    @JsonProperty("id_diagnostico")
    private String idDiagnostico;

    @JsonProperty("descricao")
    @NotBlank(message = "Descrição é obrigatória.")
    private String descricao;

    @JsonProperty("recomendacao")
    @NotBlank(message = "Recomendação é obrigatória.")
    private String recomendacao;

}
