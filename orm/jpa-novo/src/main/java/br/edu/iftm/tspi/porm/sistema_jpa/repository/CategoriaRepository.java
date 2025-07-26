package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Categoria;
import br.edu.iftm.tspi.porm.sistema_jpa.domain.DetalhePedido;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaPrecoDTO;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaQuantidadeDTO;

@Repository
public interface CategoriaRepository extends 
                            JpaRepository<Categoria, Integer>{

    public List<Categoria> findByNomeContainingIgnoreCase(String nome);

    @Query("""
        SELECT new br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaQuantidadeDTO(
            c.id,
            c.nome,
            COUNT(p)
        )
        FROM Produto p
        JOIN p.categoria c
        GROUP BY c.id, c.nome
    """)
    List<CategoriaQuantidadeDTO> contarProdutosPorCategoria();

    @Query(nativeQuery = true, value = """
        SELECT c.categoriaid AS idCategoria,
            c.categoria AS nomeCategoria,
            COUNT(p.produtoid) AS quantidade
        FROM produtos p
        JOIN categorias c ON p.categoriaid = c.categoriaid
        GROUP BY c.categoriaid, c.categoria
    """)
    List<CategoriaQuantidadeDTO> contarProdutosPorCategoriaNativo();

    @Query("""
        SELECT new br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaQuantidadeDTO(
            c.id,
            c.nome,
            COUNT(p)
        )
        FROM Produto p
        JOIN p.categoria c
        WHERE c.id = :categoriaId
        GROUP BY c.id, c.nome
    """)
    CategoriaQuantidadeDTO contarProdutoPorCategoria
        (@Param("categoriaId") Integer categoria);

    // @Query("""
    //     SELECT new br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaPrecoDTO(
    //         c.id,
    //         c.nome,
    //         SUM(dp.precoVenda * dp.quantidade * (dp.desconto - 1))
    //     )
    //     FROM DetalhePedido dp
    //     JOIN dp.produto p

    // """)
    // CategoriaPrecoDTO contarProdutosPorCategoriaPreco();
}
