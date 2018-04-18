package br.com.branquinho.dojorest.pedido.repository;

import br.com.branquinho.dojorest.pedido.model.Pedido;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Integer> {

}
