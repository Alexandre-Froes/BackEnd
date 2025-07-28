package iftm.tspi.orm.xnd.sistema_transacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import iftm.tspi.orm.xnd.sistema_transacao.domain.Cliente;
import iftm.tspi.orm.xnd.sistema_transacao.domain.Pedido;
import iftm.tspi.orm.xnd.sistema_transacao.domain.Produto;
import iftm.tspi.orm.xnd.sistema_transacao.repository.ClienteRepository;
import iftm.tspi.orm.xnd.sistema_transacao.repository.PedidoRepository;
import iftm.tspi.orm.xnd.sistema_transacao.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, 
                        ProdutoRepository produtoRepository,
                        ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void cancelarPedido(Long pedidoId) {
        Pedido pedido = findById(pedidoId);
        validarCancelamento(pedido);
        estornarPagamento(pedido);
        devolverItensAoEstoque(pedido);
        pedido.setStatus("CANCELADO");
        pedidoRepository.save(pedido);
    }

    private void validarCancelamento(Pedido pedido) {
        if (pedido.getStatus().equalsIgnoreCase("CANCELADO")) {
            throw new RuntimeException("Pedido já está cancelado");
        }
        
        if (pedido.getStatus().equalsIgnoreCase("ENTREGUE")) {
            throw new RuntimeException("Não é possível cancelar pedido já entregue");
        }
    }

    private void estornarPagamento(Pedido pedido) {
        Cliente cliente = pedido.getCliente();
        Double valorEstorno = pedido.getPrecoVenda() * pedido.getQuantidade();
        cliente.setConta(cliente.getConta() + valorEstorno);
        clienteRepository.save(cliente);
    }

    private void devolverItensAoEstoque(Pedido pedido) {
        Produto produto = pedido.getProduto();
        Integer quantidadeDevolvida = pedido.getQuantidade();
        produto.setEstoque(produto.getEstoque() + quantidadeDevolvida);
        produtoRepository.save(produto);
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pedido não existe: " + id));
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
}
