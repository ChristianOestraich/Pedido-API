package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity( name = "pedido" )
@Getter
@Setter
public class PedidoModel extends BaseModel
{
    private int numero;
    private Date data;
    private String name;
    private Float valor;
    private Float valor_total;
    private int quantidade;
    private int codigo_cliente;
}
