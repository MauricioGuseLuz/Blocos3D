package com.blocos.impressao3d.controller;


import com.blocos.impressao3d.entity.Pedido;
import com.blocos.impressao3d.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(description = "Busca todas os pedidos.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success"),
    @ApiResponse(responseCode = "400", description = "Failed"),
    @ApiResponse(responseCode = "404", description = "Página não encontrada.")
    })


    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido){
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @Operation(description = "Busca todas os pedidos.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "400", description = "Failed"), @ApiResponse(responseCode = "404", description = "Página não encontrada.")
    })

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.obterPedidoPorId(id);
        return pedido.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(description = "Busca todas os pedidos.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success"), @ApiResponse(responseCode = "400", description = "Failed"), @ApiResponse(responseCode = "404", description = "Página não encontrada.")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido novoPedido) {
        Pedido pedidoAtualizado = pedidoService.atualizarPedido(id, novoPedido);
        return pedidoAtualizado != null ? ResponseEntity.ok(pedidoAtualizado) : ResponseEntity.notFound().build();
    }
    @Operation(description = "Busca todas os pedidos.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success"),
    @ApiResponse(responseCode = "400", description = "Failed"),
    @ApiResponse(responseCode = "404", description = "Página não encontrada.")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
