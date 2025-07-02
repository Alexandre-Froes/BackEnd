package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Produto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.ProdutoDto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    @Query("""
        SELECT p FROM Produto p
        JOIN FETCH p.categoria
        WHERE p.preco = (
            SELECT MAX(p2.preco)
            FROM Produto p2
            WHERE p2.categoria.id = p.categoria.id
        )        
    """)
    List<Produto> findProdutosComMaiorPrecoPorCategoria();

    @Query(nativeQuery = true, value = """
        SELECT p.produtoid as id,
                p.produtonome as nome,
                p.preco as preco,
                p.unidadesemestoque,
                p.imagem,
                p.categoriaID
        FROM produtos p
        WHERE p.preco = (
            SELECT MAX(p2.preco)
            FROM produtos p2
            WHERE p2.categoriaid = p.categoriaid)        
    """)
    List<ProdutoDto> findProdutosComMaiorPrecoPorCategoriaNativo();

    @Query(nativeQuery = true, value = """
        SELECT p.produtoid as id,
            p.produtonome as nome,
            p.preco as preco,
            p.unidadesemestoque,
            p.imagem,
            p.categoriaID
        FROM produtos p
        WHERE p.preco = (
            SELECT MAX(p2.preco)
            FROM produtos p2
            WHERE p2.categoriaid = p.categoriaid
            AND p.categoriaid = :categoriaID)   
    """)
    ProdutoDto findProdutoComMaiorPrecoPorCategoriaNativo
                (@Param("categoriaID") Integer categoria);

}   
