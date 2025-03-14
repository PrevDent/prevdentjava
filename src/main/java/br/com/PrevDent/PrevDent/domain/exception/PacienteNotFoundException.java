package br.com.PrevDent.PrevDent.domain.exception;


import org.springframework.http.HttpStatus;

public class PacienteNotFoundException extends RuntimeException {

    private static final String DEFAULT_MENSAGEM = "Não existe um paciente com este ID";
    // modificardor de acessos
    public static final int HTTP_STATUS_CODE = HttpStatus.NOT_FOUND.value();

    public PacienteNotFoundException() {
        super(DEFAULT_MENSAGEM);
    }

}
