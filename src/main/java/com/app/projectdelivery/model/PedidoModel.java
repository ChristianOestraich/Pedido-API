package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table( name = "pedido" )
@Getter
@Setter
public class PedidoModel extends BaseModel
{
    private float valorTotal;

    @OneToMany( mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<ProdutosPedidoModel> produtosPedidos;

}
