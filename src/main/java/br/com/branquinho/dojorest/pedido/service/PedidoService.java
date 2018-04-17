package br.com.branquinho.dojorest.pedido.service;

import br.com.branquinho.dojorest.pedido.exceptions.BusinessException;
import br.com.branquinho.dojorest.pedido.model.Pedido;
import br.com.branquinho.dojorest.pedido.repository.PedidoRepository;
import br.com.branquinho.dojorest.pedido.web.form.PedidoForm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido atualizarPedido(PedidoForm form, int id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(!pedidoOptional.isPresent()) {
            throw new BusinessException("Pedido inexistente");
        }
        Pedido pedidoDoBanco = pedidoOptional.get();

        Pedido pedido = form.toEntity();
        pedido.setPedidoKey(id);
        pedido.setDataCriacao(pedidoDoBanco.getDataCriacao());
        pedido.setStatus(pedidoDoBanco.getStatus());
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

    public void fecharPedido(int id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(!pedidoOptional.isPresent()) {
            throw new BusinessException("Pedido inexistente");
        }

        Pedido pedido = pedidoOptional.get();
        pedido.setStatus("fechado");
        pedido.setDataFechamento(new Date());
        pedidoRepository.save(pedido);
    }
}
