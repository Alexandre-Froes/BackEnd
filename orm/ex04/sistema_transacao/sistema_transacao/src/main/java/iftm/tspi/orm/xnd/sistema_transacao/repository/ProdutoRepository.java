package iftm.tspi.orm.xnd.sistema_transacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iftm.tspi.orm.xnd.sistema_transacao.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
