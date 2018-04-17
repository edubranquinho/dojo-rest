package br.com.branquinho.dojorest.pedido.web.form;

import br.com.branquinho.dojorest.pedido.model.Pedido;
import lombok.Data;

@Data
public class PedidoForm {

    private String observacao;
    private int lojaKey;

    public Pedido toEntity() {
        Pedido pedido = new Pedido();
        pedido.setLojaKey(this.getLojaKey());
        pedido.setObservacao(this.getObservacao());
        return pedido;
    }

}