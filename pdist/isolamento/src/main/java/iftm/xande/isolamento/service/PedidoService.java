package iftm.xande.isolamento.service;

import org.springframework.stereotype.Service;

import java.iftm.xande.isolamento.model.ItemPedido;
import java.iftm.xande.isolamento.model.Pedido;
import java.iftm.xande.isolamento.repository.EstoqueRepository;
import java.iftm.xande.isolamento.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstoqueRepository estoqueRepository;

    public PedidoService(PedidoRepository pedidoRepository, EstoqueRepository estoqueRepository){
        this.pedidoRepository = pedidoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public Pedido salvaPedido(Pedido pedido){
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        
        if(!pedido.getItens().isEmpty()){
            throw new RuntimeException("Não identificado");
        }

        for(ItemPedido item: pedido.getItens()){
            estoqueRepository.atualizaEstoque(item.getProduto().getId(), item.getQuantidade());
        }

        return pedidoSalvo;
    }
    
}