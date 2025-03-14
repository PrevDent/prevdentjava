package br.com.PrevDent.PrevDent.domain.exception;

import org.springframework.http.HttpStatus;

public class DiagnosticoNotFoundException extends RuntimeException {
    private static final String DEFAULT_MENSAGEM = "Não existe um Diagnostico com este ID";

    public static final int HTTP_STATUS_CODE = HttpStatus.NOT_FOUND.value();

    public DiagnosticoNotFoundException() {
        super(DEFAULT_MENSAGEM);
    }
}
