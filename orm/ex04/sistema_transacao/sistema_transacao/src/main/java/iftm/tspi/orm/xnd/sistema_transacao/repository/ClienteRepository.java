package iftm.tspi.orm.xnd.sistema_transacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iftm.tspi.orm.xnd.sistema_transacao.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
