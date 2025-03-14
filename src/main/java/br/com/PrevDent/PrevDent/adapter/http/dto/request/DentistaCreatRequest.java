package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentistaCreatRequest {

    @JsonProperty("id_dentista")
    private String idDentista;

    @JsonProperty("nome_dentista")
    @NotBlank(message = "O nome do dentista é obrigatório.")
    private String nome;

    @JsonProperty("documento_dentista")
    @NotBlank(message = "O documento do dentista é obrigatório.")
    private String documento;

    @JsonProperty("especializacao")
    @NotBlank(message = "A especialização do dentista é obrigatória.")
    private String especializacao;

    @JsonProperty("consultas")
    private List<ConsultaCreatRequest> consultas;

}
