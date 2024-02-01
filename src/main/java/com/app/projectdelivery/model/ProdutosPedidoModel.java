package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produtos_Pedido")
@Getter
@Setter
public class ProdutosPedidoModel extends BaseModel {
    private Long id_produto;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private PedidoModel pedido;
}
