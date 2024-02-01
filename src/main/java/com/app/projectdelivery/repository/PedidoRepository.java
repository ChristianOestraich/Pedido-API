package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long>
{
    public Optional<PedidoModel> findByValorTotal ( double valorTotal );
}
