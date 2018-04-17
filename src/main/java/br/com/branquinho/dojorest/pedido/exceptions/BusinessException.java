package br.com.branquinho.dojorest.pedido.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException (String mensagem) {
        super(mensagem);
    }
}
