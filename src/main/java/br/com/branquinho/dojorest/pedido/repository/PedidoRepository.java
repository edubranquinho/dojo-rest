package br.com.branquinho.dojorest.pedido.repository;

import br.com.branquinho.dojorest.pedido.model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

}
