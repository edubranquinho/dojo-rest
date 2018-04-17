package br.com.branquinho.dojorest.pedido.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedidoKey;

    private String observacao;

    @Setter(AccessLevel.NONE)
    @Column(updatable = false)
    private Date dataCriacao;

    private Integer lojaKey;

    private Date dataFechamento;

    @PrePersist
    public void onPrePersist() {
        this.dataCriacao = new Date();
    }

}
