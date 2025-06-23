package br.edu.iftm.tspi.porm.sistema_jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="detalhes_pedido")
public class DetalhePedido {
    @EmbeddedId
    private DetalhePedidoId id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "PedidoID", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "ProdutoID", nullable = false)
    private Produto produto;

    @Column(name = "precoVenda", nullable = false)
    private Double precoVenda;

    @Column(name = "quantidade", nullable = false)
    private Short quantidade;

    @Column(name = "desconto", nullable = false)
    private Double desconto;
}

