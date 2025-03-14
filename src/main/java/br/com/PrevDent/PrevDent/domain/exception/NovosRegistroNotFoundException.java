package br.com.PrevDent.PrevDent.domain.exception;

import org.springframework.http.HttpStatus;

public class NovosRegistroNotFoundException extends RuntimeException {

  private static final String DEFAULT_MENSAGEM = "Não existe um novo registro com este ID";
  // modificardor de acessos
  public static final int HTTP_STATUS_CODE = HttpStatus.NOT_FOUND.value();

    public NovosRegistroNotFoundException() {
        super(DEFAULT_MENSAGEM);
    }
}
