package br.com.branquinho.dojorest.pedido.service;

import br.com.branquinho.dojorest.pedido.exceptions.BusinessException;
import br.com.branquinho.dojorest.pedido.model.Pedido;
import br.com.branquinho.dojorest.pedido.repository.PedidoRepository;
import br.com.branquinho.dojorest.pedido.web.form.PedidoForm;
import br.com.branquinho.dojorest.pedido.web.view.PedidoProjection;
import br.com.branquinho.dojorest.pedido.web.view.PedidoView;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    public Page<PedidoView> listar(Pageable page) {
    	    Page<Pedido> paginaDoPedido = pedidoRepository.findAll(page);
    	    List<Pedido> pedidos = paginaDoPedido.getContent();
    	    List<PedidoView> pedidosView = PedidoView.toView(pedidos);
    	    PageImpl<PedidoView> novaPagina = new PageImpl<>(pedidosView, page, paginaDoPedido.getTotalElements());
    	    return novaPagina;
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
