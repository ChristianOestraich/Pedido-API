package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.ProdutosPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutosPedidoRepository extends JpaRepository<ProdutosPedidoModel, Long>
{
    ProdutosPedidoModel save(ProdutosPedidoModel produtosPedidoModel);

    public Optional<ProdutosPedidoModel> findByQuantidade( int quantidade );
}
