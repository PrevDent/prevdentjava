package br.com.PrevDent.PrevDent.adapter.http.dto.erros;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseErrors {

    private String campo;
    private String mensagem;
}
