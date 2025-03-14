package br.com.PrevDent.PrevDent.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NovosRegistro {

    private String idRegistro;
    private String tipo;
    private String ocorrencia;
    private String intensidade;

    @JsonProperty("informacoes_adicionais")
    private String informacoesAdicionais;
}
