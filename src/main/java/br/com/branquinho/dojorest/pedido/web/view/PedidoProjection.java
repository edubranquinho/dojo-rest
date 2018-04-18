package br.com.branquinho.dojorest.pedido.web.view;

import java.util.Date;

import br.com.branquinho.dojorest.pedido.model.Pedido;

//@Projection(name = "pedidoProjection", types = { Pedido.class })
public interface PedidoProjection {
	
	public int getPedidoKey();
 
    public String getObservacao();

    public Date getDataCriacao();

    public int getLojaKey();

    public Date getDataFechamento();

    public String getStatus();
	
}
