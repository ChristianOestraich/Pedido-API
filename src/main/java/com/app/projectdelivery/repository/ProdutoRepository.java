package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>
{
    public Optional<ProdutoModel> findByNome(String nome);
}
