package com.app.projectdelivery.business;

import com.app.projectdelivery.model.PedidoModel;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PedidoBusiness {

    public static ResponseEntity<String> processarPedidos(List<PedidoModel> pedidos) {
        Set<Integer> numerosControleSet = new HashSet<>();

        for (PedidoModel pedido : pedidos) {
            // Regra 1: Limite de 10 pedidos
            if (numerosControleSet.size() >= 10) {
                return ResponseEntity.badRequest().body("Limite de 10 pedidos excedido.");
            }

            // Regra 2: Não aceitar número de controle já cadastrado
            if (!numerosControleSet.add(pedido.getNumero())) {
                return ResponseEntity.badRequest().body("Número de controle duplicado: " + pedido.getNumero());
            }

            // Regra 3: Definir data de cadastro para a data atual, se não fornecida
            if (pedido.getData() == null) {
                pedido.setData(new Date());
            }

            // Regra 4: Definir quantidade para 1, se não fornecida
            if (pedido.getQuantidade() == 0) {
                pedido.setQuantidade(1);
            }

            // Regra 5: Aplicar desconto com base na quantidade
            float valorTotal = pedido.getValor() * pedido.getQuantidade();
            if (pedido.getQuantidade() > 5 && pedido.getQuantidade() < 10) {
                valorTotal *= 0.95; // 5% de desconto
            } else if (pedido.getQuantidade() >= 10) {
                valorTotal *= 0.9; // 10% de desconto
            }

            // Regra 6: Calcular e gravar o valor total do pedido
            pedido.setValor_total(valorTotal);
        }

        // Pode retornar true se todas as regras foram atendidas
        return ResponseEntity.ok("Pedidos processados com sucesso.");
    }
}
