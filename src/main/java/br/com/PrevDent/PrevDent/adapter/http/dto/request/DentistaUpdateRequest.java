package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentistaUpdateRequest {

    @JsonProperty("id_dentista")
    private String idDentista;

    @JsonProperty("nome_dentista")
    @NotNull
    private String nome;

    @JsonProperty("especializacao")
    @NotNull
    private String especializacao;
}
