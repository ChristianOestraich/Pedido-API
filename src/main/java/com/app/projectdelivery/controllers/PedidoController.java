package com.app.projectdelivery.controllers;

import com.app.projectdelivery.business.PedidoBusiness;
import com.app.projectdelivery.model.PedidoModel;
import com.app.projectdelivery.repository.PedidoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "/api/delivery" )
public class PedidoController
{
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository deliveryRepository )
    {
        this.pedidoRepository = deliveryRepository;
    }

    @GetMapping( "/listAll" )
    public List<PedidoModel> findAll()
    {
        return pedidoRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PedidoModel> findOne(@PathVariable long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/numeroPedido/{numeroPedido}")
    public ResponseEntity<PedidoModel> findByNumeroPedido(@PathVariable int numero) {
        return pedidoRepository.findByNumero(numero)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/dataCadastro/{dataCadastro}")
    public ResponseEntity<PedidoModel> findByDataCadastro(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
        return pedidoRepository.findByData(data)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping( "/save" )
    public ResponseEntity<List<PedidoModel>> save(@RequestBody @Valid List<PedidoModel> delivery )
    {
        PedidoBusiness.processarPedidos(delivery);
        return saveDelivery( delivery );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> remove( @PathVariable Long id )
    {
        return pedidoRepository.findById( id )
                                 .map( userModel ->
                                 {
                                     pedidoRepository.deleteById( id );
                                     return ResponseEntity.ok().build();
                                 } )
                                 .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/edit" )
    public ResponseEntity<List<PedidoModel>> editPost(@RequestBody @Valid List<PedidoModel> delivery )
    {
        return saveDelivery( delivery );
    }

    private ResponseEntity<List<PedidoModel>> saveDelivery(List<PedidoModel> delivery) {
        List<PedidoModel> savedDelivery = pedidoRepository.saveAll(delivery);

        return ResponseEntity.ok(savedDelivery);
    }

}
