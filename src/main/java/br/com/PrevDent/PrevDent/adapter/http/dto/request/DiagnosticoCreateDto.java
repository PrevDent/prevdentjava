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
public class DiagnosticoCreateDto {

    @JsonProperty("id_diagnostico")
    private String idDiagnostico;

    @JsonProperty("descricao")
    @NotBlank(message = "Descrição é obrigatória.")
    @Size(max = 255, message = "Descrição não pode ter mais de 255 caracteres.")
    private String descricao;

    @JsonProperty("recomendacao")
    @NotBlank(message = "Recomendação é obrigatória.")
    @Size(max = 255, message = "Recomendação não pode ter mais de 255 caracteres.")
    private String recomendacao;
}
