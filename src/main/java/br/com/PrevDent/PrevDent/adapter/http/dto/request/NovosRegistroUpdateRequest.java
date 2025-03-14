package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovosRegistroUpdateRequest {

    @JsonProperty("id_registro")
    private String idRegistro;

    @JsonProperty("tipo")
    @NotNull(message = "O tipo é obrigatório.")
    private String tipo;

    @JsonProperty("ocorrencia")
    @NotNull(message = "A ocorrencia é obrigatória.")
    private String ocorrencia;

    @JsonProperty("intensidade")
    @NotNull(message = "A intensidade é obrigatória.")
    private String intensidade;

    @JsonProperty("informacoes_adicionais")
    @NotNull(message = "As informações adicionais são obrigatórias.")
    private String informacoesAdicionais;
}
