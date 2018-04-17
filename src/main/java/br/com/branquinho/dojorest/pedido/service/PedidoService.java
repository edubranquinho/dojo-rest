package br.com.branquinho.dojorest.pedido.service;

import br.com.branquinho.dojorest.pedido.exceptions.BusinessException;
import br.com.branquinho.dojorest.pedido.model.Pedido;
import br.com.branquinho.dojorest.pedido.repository.PedidoRepository;
import br.com.branquinho.dojorest.pedido.web.form.PedidoForm;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido atualizarPedido(PedidoForm form, int id) {
        boolean pedidoExiste = pedidoRepository.existsById(id);
        if(!pedidoExiste) {
            throw new BusinessException("Pedido inexistente");
        }
        Pedido pedido = form.toEntity();
        pedido.setPedidoKey(id);
        return pedidoRepository.save(pedido);
    }

    public Iterable<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido obter(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new BusinessException("Pedido inexistente"));
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deletar(int id) {
        pedidoRepository.deleteById(id);
    }
}
