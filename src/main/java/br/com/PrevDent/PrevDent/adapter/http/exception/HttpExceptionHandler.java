package br.com.PrevDent.PrevDent.adapter.http.exception;

import br.com.PrevDent.PrevDent.adapter.http.dto.erros.ResponseErrors;
import br.com.PrevDent.PrevDent.domain.exception.ConsultaNotFoudException;
import br.com.PrevDent.PrevDent.domain.exception.DentistaNotFoudException;
import br.com.PrevDent.PrevDent.domain.exception.DiagnosticoNotFoundException;
import br.com.PrevDent.PrevDent.domain.exception.PacienteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarErrosDeValidacaoFisica(MethodArgumentNotValidException exception){

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        List<ResponseErrors> listaDeErro = new ArrayList<>();

        for (FieldError fieldError : fieldErrors){

            String campo = fieldError.getField();
            String mensagemDeErro = fieldError.getDefaultMessage();

            ResponseErrors responseErro = new ResponseErrors();
            responseErro.setCampo(campo);
            responseErro.setMensagem(mensagemDeErro);

            listaDeErro.add(responseErro);

        }

        return ResponseEntity.badRequest().body(listaDeErro);
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<Object> tratarPacienteNotFoundException(PacienteNotFoundException pacienteNotFoundException){
        ResponseErrors responseErro = new ResponseErrors();
        responseErro.setMensagem(pacienteNotFoundException.getMessage());
        return ResponseEntity.status(pacienteNotFoundException.HTTP_STATUS_CODE).body(responseErro);
    }

    @ExceptionHandler(DentistaNotFoudException.class)
    public ResponseEntity<Object> tratarDentistaNotFoundException(DentistaNotFoudException dentistaNotFoudException){
        ResponseErrors responseErro = new ResponseErrors();
        responseErro.setMensagem(dentistaNotFoudException.getMessage());
        return ResponseEntity.status(dentistaNotFoudException.HTTP_STATUS_CODE).body(responseErro);
    }

    @ExceptionHandler(ConsultaNotFoudException.class)
    public  ResponseEntity<Object> tratarConsultaNotFoundException(ConsultaNotFoudException consultaNotFoudException){
        ResponseErrors responseErro = new ResponseErrors();
        responseErro.setMensagem(consultaNotFoudException.getMessage());
        return ResponseEntity.status(consultaNotFoudException.HTTP_STATUS_CODE).body(responseErro);
    }

    @ExceptionHandler(DiagnosticoNotFoundException.class)
    public ResponseEntity<Object> tratarDiagnosticoNotFoundException(DiagnosticoNotFoundException diagnosticoNotFoundException){
        ResponseErrors responseErro = new ResponseErrors();
        responseErro.setMensagem(diagnosticoNotFoundException.getMessage());
        return ResponseEntity.status(ConsultaNotFoudException.HTTP_STATUS_CODE).body(responseErro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> tratarNovosRegistroNotFoundExceprion(Exception exception){
        ResponseErrors responseErro = new ResponseErrors();
        responseErro.setMensagem(exception.getMessage());
        return ResponseEntity.status(ConsultaNotFoudException.HTTP_STATUS_CODE).body(responseErro);
    }
}
