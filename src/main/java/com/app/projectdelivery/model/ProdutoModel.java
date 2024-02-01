package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "produto" )
@Getter
@Setter
public class ProdutoModel extends BaseModel
{
    private String nome;
    private float valor;
    private String categoria;
}
