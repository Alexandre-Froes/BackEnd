package iftm.tspi.orm.xande.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iftm.tspi.orm.xande.jpa.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    public List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
