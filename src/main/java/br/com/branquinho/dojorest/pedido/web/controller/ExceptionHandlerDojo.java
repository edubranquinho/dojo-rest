package br.com.branquinho.dojorest.pedido.web.controller;

import br.com.branquinho.dojorest.pedido.exceptions.BusinessException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerDojo extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Erro> test(RuntimeException exception, WebRequest request){
        Erro erro = new Erro(exception.getMessage(), ExceptionUtils.getRootCauseMessage(exception));

        return ResponseEntity.badRequest()
                .body(erro);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Erro> test(Exception exception, WebRequest request){
        Erro erro = new Erro(exception.getMessage(), ExceptionUtils.getRootCauseMessage(exception));

        return ResponseEntity.badRequest()
                .body(erro);
    }

    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }
}
