package com.app.projectdelivery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.projectdelivery.controllers.PedidoController;
import com.app.projectdelivery.model.PedidoModel;
import com.app.projectdelivery.repository.PedidoRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectDeliveryApplicationTests {

    @Autowired
    private PedidoController pedidoController;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Test
    void testFindAll() {
        // Mockando o comportamento do repository
        List<PedidoModel> mockPedidos = Arrays.asList(new PedidoModel(), new PedidoModel());
        when(pedidoRepository.findAll()).thenReturn(mockPedidos);

        // Chamando o método do controller
        List<PedidoModel> result = pedidoController.findAll();

        // Verificando se o resultado é o esperado
        assertEquals(mockPedidos, result);
    }

    @Test
    void testFindOne() {
        // Mockando o comportamento do repository
        long mockId = 1L;
        PedidoModel mockPedido = new PedidoModel();
        when(pedidoRepository.findById(mockId)).thenReturn(Optional.of(mockPedido));

        // Chamando o método do controller
        ResponseEntity<PedidoModel> result = pedidoController.findOne(mockId);

        // Verificando se o resultado é o esperado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockPedido, result.getBody());
    }

    @Test
    void testFindByNumeroPedido() {
        // Mockando o comportamento do repository
        int mockNumeroPedido = 123;
        PedidoModel mockPedido = new PedidoModel();
        when(pedidoRepository.findByNumero(mockNumeroPedido)).thenReturn(Optional.of(mockPedido));

        // Chamando o método do controller
        ResponseEntity<PedidoModel> result = pedidoController.findByNumeroPedido(mockNumeroPedido);

        // Verificando se o resultado é o esperado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockPedido, result.getBody());
    }
}
