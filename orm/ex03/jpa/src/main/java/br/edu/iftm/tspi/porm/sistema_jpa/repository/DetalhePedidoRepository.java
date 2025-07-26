package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.DetalhePedido;
import br.edu.iftm.tspi.porm.sistema_jpa.domain.DetalhePedidoId;

@Repository
public interface DetalhePedidoRepository extends JpaRepository<DetalhePedido, DetalhePedidoId> {

    @Query("""
            select dp from DetalhePedido dp
            join fetch dp.pedido p
            join fetch dp.produto pr
            where pr.codigo = :produtoId
            """)
    List<DetalhePedido> findByProdutoId(@Param("produtoId") Integer produtoId);

    @Query("""
            select dp from DetalhePedido dp
            join fetch dp.produto pr
            join fetch dp.pedido pe
            join fetch pe.cliente c
            where c.id = :clienteId
            """)
    List<DetalhePedido> findByClienteId(@Param("clienteId") String clienteId);

    @Query("""
            select dp from DetalhePedido dp
            where dp.produto.categoria.id = :categoriaId
            """)
    List<DetalhePedido> findByCategoriaId(@Param("categoriaId") Integer categoriaId);

    @Query(value = """
            SELECT SUM(dp.precovenda * dp.quantidade) as valorTotal
            FROM detalhes_pedido dp 
            JOIN produtos p ON dp.produtoid = p.produtoid 
            WHERE p.categoriaid = :categoriaId
            """, nativeQuery = true)
    Double calculateCategoryTotalNative(@Param("categoriaId") Integer categoriaId);

    @Query(value = """
            SELECT c.categoria as nomeCategoria
            FROM categorias c 
            WHERE c.categoriaid = :categoriaId
            """, nativeQuery = true)
    String findCategoryNameById(@Param("categoriaId") Integer categoriaId);

    @Query(value = """
            SELECT 
                p.pedidoid as pedidoId,
                DATE(p.datapedido) as dataPedido,
                SUM(dp.precovenda * dp.quantidade * (1 - dp.desconto)) as valorTotalPedido
            FROM pedidos p
            JOIN detalhes_pedido dp ON p.pedidoid = dp.pedidoid
            WHERE p.clienteid = :clienteId 
            AND DATE(p.datapedido) BETWEEN :dataInicio AND :dataFim
            GROUP BY p.pedidoid, DATE(p.datapedido)
            ORDER BY DATE(p.datapedido)
            """, nativeQuery = true)
    List<Object[]> findPedidosByClienteAndPeriodoNative(
        @Param("clienteId") String clienteId,
        @Param("dataInicio") String dataInicio,
        @Param("dataFim") String dataFim
    );

    @Query(value = """
            SELECT 
                dp.produtoid as produtoId,
                pr.produtonome as produtoNome,
                dp.quantidade,
                dp.precovenda,
                dp.desconto,
                (dp.precovenda * dp.quantidade * (1 - dp.desconto)) as valorTotal
            FROM detalhes_pedido dp
            JOIN produtos pr ON dp.produtoid = pr.produtoid
            WHERE dp.pedidoid = :pedidoId
            ORDER BY pr.produtonome
            """, nativeQuery = true)
    List<Object[]> findProdutosByPedidoNative(@Param("pedidoId") Integer pedidoId);

    @Query(value = """
            SELECT c.nome as clienteNome
            FROM clientes c 
            WHERE c.clienteid = :clienteId
            """, nativeQuery = true)
    String findClienteNameById(@Param("clienteId") String clienteId);

    @Query(value = """
            SELECT SUM(dp.precovenda * dp.quantidade * (1 - dp.desconto)) as valorTotal
            FROM pedidos p
            JOIN detalhes_pedido dp ON p.pedidoid = dp.pedidoid
            WHERE p.clienteid = :clienteId 
            AND DATE(p.datapedido) BETWEEN :dataInicio AND :dataFim
            """, nativeQuery = true)
    Double calculateTotalInvestidoClientePeriodoNative(
        @Param("clienteId") String clienteId,
        @Param("dataInicio") String dataInicio,
        @Param("dataFim") String dataFim
    );

}
