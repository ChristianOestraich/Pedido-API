package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface PedidoRepository extends JpaRepository<PedidoModel, Long>
{
    Optional<PedidoModel> findByname( String name );
    Optional<PedidoModel> findByNumero( int número_controle );
    Optional<PedidoModel> findByData( Date data_cadastro );

}
