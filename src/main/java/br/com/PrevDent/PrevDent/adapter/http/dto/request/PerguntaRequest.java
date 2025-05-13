package br.com.PrevDent.PrevDent.adapter.http.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record PerguntaRequest(

        @NotBlank(message = "A pergunta não pode estar vazia")
        @Size(max = 500, message = "A pergunta deve ter no máximo 500 caracteres")
        String pergunta
) {


}
