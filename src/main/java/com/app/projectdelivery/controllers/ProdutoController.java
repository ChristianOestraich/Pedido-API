package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.ProdutoModel;
import com.app.projectdelivery.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController
{
    private final ProdutoRepository produtoRepository;

    public ProdutoController( ProdutoRepository produtoRepository )
    {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/listAll")
    public List<ProdutoModel> findAll()
    {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> findOne(@PathVariable long id )
    {
        return produtoRepository.findById( id )
                                .map( ResponseEntity::ok )
                                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/save")
    public ResponseEntity<ProdutoModel> save( @RequestBody @Valid ProdutoModel produto )
    {
        return saveProduto( produto );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove( @PathVariable Long id )
    {
        return produtoRepository.findById(id)
                                .map( produtoModel -> {
                                    produtoRepository.deleteById( id );
                                    return ResponseEntity.ok().build();
                                } )
                                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/edit")
    public ResponseEntity<ProdutoModel> editPost( @RequestBody @Valid ProdutoModel produto )
    {
        return saveProduto(produto);
    }

    private ResponseEntity<ProdutoModel> saveProduto( ProdutoModel produto )
    {
        return ResponseEntity.ok( produtoRepository.save( produto ) );
    }
}
