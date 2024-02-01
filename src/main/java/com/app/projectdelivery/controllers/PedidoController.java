package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.PedidoModel;
import com.app.projectdelivery.model.ProdutoModel;
import com.app.projectdelivery.model.ProdutosPedidoModel;
import com.app.projectdelivery.repository.PedidoRepository;
import com.app.projectdelivery.repository.ProdutoRepository;
import com.app.projectdelivery.repository.ProdutosPedidoRepository;
import com.app.projectdelivery.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutosPedidoRepository produtoPedidoRepository;
    private final UserRepository userRepository;

    public PedidoController(
            PedidoRepository pedidoRepository,
            ProdutoRepository produtoRepository,
            ProdutosPedidoRepository produtoPedidoRepository,
            UserRepository userRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.produtoPedidoRepository = produtoPedidoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/listAll")
    public List<PedidoModel> findAll() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> findOne(@PathVariable long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<PedidoModel> createPedido( @RequestBody @Valid PedidoModel pedido )
    {
        List<ProdutosPedidoModel> produtosPedidoList = pedido.getProdutosPedidos();

        if ( produtosPedidoList != null && !produtosPedidoList.isEmpty() )
        {
            float valorTotalPedido = 0.0f;

            for ( ProdutosPedidoModel produtosPedido : produtosPedidoList )
            {
                Optional<ProdutoModel> produtoOptional = produtoRepository.findById( produtosPedido.getId_produto() );

                if ( produtoOptional.isPresent() )
                {
                    ProdutoModel produto = produtoOptional.get();
                    float valorProduto = produto.getValor();
                    float valorTotalProduto = valorProduto * produtosPedido.getQuantidade();

                    valorTotalPedido += valorTotalProduto;
                }
            }

            pedido.setValorTotal( valorTotalPedido );
        }

        return ResponseEntity.ok( pedidoRepository.save( pedido ) );
    }

    @PostMapping("/{idPedido}/addProduto/{idProduto}")
    public ResponseEntity<?> addProdutoToPedido(
            @PathVariable long idPedido,
            @PathVariable long idProduto,
            @RequestParam int quantidade
    )
    {
        Optional<PedidoModel> pedidoOptional = pedidoRepository.findById( idPedido );
        Optional<ProdutoModel> produtoOptional = produtoRepository.findById( idProduto );

        if ( pedidoOptional.isPresent() && produtoOptional.isPresent() )
        {
            PedidoModel pedido = pedidoOptional.get();
            ProdutoModel produto = produtoOptional.get();

            ProdutosPedidoModel produtosPedido = new ProdutosPedidoModel();
            produtosPedido.setPedido( pedido );
            produtosPedido.setId_produto( produto.getId() );
            produtosPedido.setQuantidade( quantidade );

            // Calcula o valor total do pedido
            float valorProduto = produto.getValor();
            float valorTotalProduto = valorProduto * quantidade;

            // Adiciona o valor do produto ao valor total do pedido
            float valorTotalPedido = pedido.getValorTotal() + valorTotalProduto;
            pedido.setValorTotal( valorTotalPedido );

            produtoPedidoRepository.save( produtosPedido );

            // Atualiza o valor total do pedido no repositório
            pedidoRepository.save( pedido );

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}