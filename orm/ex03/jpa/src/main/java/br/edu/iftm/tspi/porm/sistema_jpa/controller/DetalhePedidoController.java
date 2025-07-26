package br.edu.iftm.tspi.porm.sistema_jpa.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.DetalhePedido;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaConsumoDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.ClienteProdutoConsumoDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.DetalhePedidoProdutoDto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.PedidoClientePeriodoDto;
import br.edu.iftm.tspi.porm.sistema_jpa.repository.DetalhePedidoRepository;

@RestController
@RequestMapping("/detalhes-pedido")
public class DetalhePedidoController {

    private final DetalhePedidoRepository detalhePedidoRepository;

    public DetalhePedidoController(DetalhePedidoRepository detalhePedidoRepository) {
        this.detalhePedidoRepository = detalhePedidoRepository;
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<DetalhePedidoProdutoDto>> buscarDetalhesPorProdutoId(@PathVariable Integer produtoId) {
        List<DetalhePedido> detalhes = detalhePedidoRepository.findByProdutoId(produtoId);
        
        if (detalhes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<DetalhePedidoProdutoDto> resultado = detalhes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    private DetalhePedidoProdutoDto convertToDto(DetalhePedido detalhePedido) {
        // Calcular valor total do produto (preço de venda * quantidade)
        Double valorTotalProduto = detalhePedido.getPrecoVenda() * detalhePedido.getQuantidade();
        
        // Calcular valor total do desconto (desconto * quantidade)
        Double valorTotalDesconto = detalhePedido.getDesconto() * detalhePedido.getQuantidade();

        // Arredondar valorTotalProduto para 2 casas decimais
        valorTotalProduto = Math.round(valorTotalProduto * 100.0) / 100.0;

        return DetalhePedidoProdutoDto.builder()
                .numeroPedido(detalhePedido.getPedido().getId())
                .quantidadeVendida(detalhePedido.getQuantidade())
                .valorTotalProduto(valorTotalProduto)
                .valorTotalDesconto(valorTotalDesconto)
                .build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ClienteProdutoConsumoDto>> buscarConsumoPorClienteId(@PathVariable String clienteId) {
        List<DetalhePedido> detalhes = detalhePedidoRepository.findByClienteId(clienteId);
        
        if (detalhes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Map<Integer, ClienteProdutoConsumoDto> consumoPorProduto = detalhes.stream()
                .collect(Collectors.groupingBy(
                    dp -> dp.getProduto().getCodigo(),
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            DetalhePedido primeiro = list.get(0);
                            Double valorTotal = list.stream()
                                .mapToDouble(dp -> dp.getPrecoVenda() * dp.getQuantidade())
                                .sum();
                            valorTotal = Math.round(valorTotal * 100.0) / 100.0;
                            
                            return ClienteProdutoConsumoDto.builder()
                                .produtoId(primeiro.getProduto().getCodigo())
                                .nomeProduto(primeiro.getProduto().getNome())
                                .valorTotalConsumido(valorTotal)
                                .build();
                        }
                    )
                ));

        return ResponseEntity.ok(consumoPorProduto.values().stream().collect(Collectors.toList()));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<CategoriaConsumoDto> buscarConsumoPorCategoriaId(@PathVariable Integer categoriaId) {
        // Use native SQL queries to avoid DST timestamp issues - industry standard approach
        Double valorTotal = detalhePedidoRepository.calculateCategoryTotalNative(categoriaId);
        String nomeCategoria = detalhePedidoRepository.findCategoryNameById(categoriaId);
        
        if (valorTotal == null || nomeCategoria == null) {
            return ResponseEntity.noContent().build();
        }

        // Apply decimal rounding to 2 places
        valorTotal = Math.round(valorTotal * 100.0) / 100.0;

        CategoriaConsumoDto resultado = CategoriaConsumoDto.builder()
                .categoriaId(categoriaId)
                .nomeCategoria(nomeCategoria)
                .valorTotalConsumido(valorTotal)
                .build();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/cliente/{clienteId}/periodo")
    public ResponseEntity<PedidoClientePeriodoDto> buscarPedidosClientePorPeriodo(
            @PathVariable String clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        
        // Get client name using native query
        String clienteNome = detalhePedidoRepository.findClienteNameById(clienteId);
        if (clienteNome == null) {
            return ResponseEntity.noContent().build();
        }

        // Get orders in the period using native query
        List<Object[]> pedidosRaw = detalhePedidoRepository.findPedidosByClienteAndPeriodoNative(
                clienteId, dataInicio.toString(), dataFim.toString());

        if (pedidosRaw.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Build the orders list
        List<PedidoClientePeriodoDto.PedidoResumoDto> pedidos = new ArrayList<>();
        
        for (Object[] pedidoRaw : pedidosRaw) {
            Integer pedidoId = ((Number) pedidoRaw[0]).intValue();
            Date dataPedidoSql = (Date) pedidoRaw[1];
            LocalDate dataPedido = dataPedidoSql.toLocalDate();
            Double valorTotalPedido = ((Number) pedidoRaw[2]).doubleValue();

            // Get products for this order
            List<Object[]> produtosRaw = detalhePedidoRepository.findProdutosByPedidoNative(pedidoId);
            List<PedidoClientePeriodoDto.ProdutoResumoDto> produtos = new ArrayList<>();

            for (Object[] produtoRaw : produtosRaw) {
                Integer produtoId = ((Number) produtoRaw[0]).intValue();
                String produtoNome = (String) produtoRaw[1];
                Integer quantidade = ((Number) produtoRaw[2]).intValue();
                Double precoVenda = ((Number) produtoRaw[3]).doubleValue();
                Double desconto = ((Number) produtoRaw[4]).doubleValue();
                Double valorTotal = ((Number) produtoRaw[5]).doubleValue();

                PedidoClientePeriodoDto.ProdutoResumoDto produto = PedidoClientePeriodoDto.ProdutoResumoDto.builder()
                        .produtoId(produtoId)
                        .produtoNome(produtoNome)
                        .quantidade(quantidade)
                        .precoVenda(BigDecimal.valueOf(precoVenda).setScale(2, RoundingMode.HALF_UP))
                        .desconto(BigDecimal.valueOf(desconto).setScale(2, RoundingMode.HALF_UP))
                        .valorTotal(BigDecimal.valueOf(valorTotal).setScale(2, RoundingMode.HALF_UP))
                        .build();
                
                produtos.add(produto);
            }

            PedidoClientePeriodoDto.PedidoResumoDto pedido = PedidoClientePeriodoDto.PedidoResumoDto.builder()
                    .pedidoId(pedidoId)
                    .dataPedido(dataPedido)
                    .produtos(produtos)
                    .valorTotalPedido(BigDecimal.valueOf(valorTotalPedido).setScale(2, RoundingMode.HALF_UP))
                    .build();
            
            pedidos.add(pedido);
        }

        // Calculate total invested amount
        Double valorTotalInvestido = detalhePedidoRepository.calculateTotalInvestidoClientePeriodoNative(
                clienteId, dataInicio.toString(), dataFim.toString());

        PedidoClientePeriodoDto resultado = PedidoClientePeriodoDto.builder()
                .clienteId(clienteId)
                .clienteNome(clienteNome)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .pedidos(pedidos)
                .valorTotalInvestido(BigDecimal.valueOf(valorTotalInvestido != null ? valorTotalInvestido : 0.0)
                        .setScale(2, RoundingMode.HALF_UP))
                .build();

        return ResponseEntity.ok(resultado);
    }
}
