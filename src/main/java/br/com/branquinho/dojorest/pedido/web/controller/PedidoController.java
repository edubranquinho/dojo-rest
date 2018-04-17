package br.com.branquinho.dojorest.pedido.web.controller;

import br.com.branquinho.dojorest.pedido.exceptions.BusinessException;
import br.com.branquinho.dojorest.pedido.model.Pedido;
import br.com.branquinho.dojorest.pedido.service.PedidoService;
import br.com.branquinho.dojorest.pedido.web.form.PedidoForm;
import br.com.branquinho.dojorest.pedido.web.view.PedidoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoView> listar(){
        return PedidoView.toView(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public PedidoView obter(@PathVariable Integer id) {
        return new PedidoView(pedidoService.obter(id));
    }

    @PostMapping
    public ResponseEntity<PedidoView> criar(@RequestBody PedidoForm form) {
        PedidoView pedidoView = new PedidoView(pedidoService.salvar(form.toEntity()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pedidoView.getPedidoKey()).toUri();

        return ResponseEntity.created(uri).body(pedidoView);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id){
        try {
            pedidoService.deletar(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Recurso inexistente");
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoView> atualizar(@RequestBody PedidoForm form, @PathVariable int id) {
        Pedido pedidoAlterado = pedidoService.atualizarPedido(form, id);

        PedidoView pedidoView = new PedidoView(pedidoAlterado);

        return ResponseEntity.ok(pedidoView);
    }

    @PatchMapping(value = "/{id}/fechar-pedido")
    public ResponseEntity<PedidoView> fecharPedido(@PathVariable int id) {
        pedidoService.fecharPedido(id);
        return ResponseEntity.ok().build();
    }

}
