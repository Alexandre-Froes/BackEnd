package iftm.edu.br.tspi.pmvc.xande.menefreda.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import iftm.edu.br.tspi.pmvc.xande.menefreda.domain.Plano;
import iftm.edu.br.tspi.pmvc.xande.menefreda.repository.PlanoRepository;

@Controller
public class PlanoController {
    private PlanoRepository repository;

    public List<Plano> listarPlanos() {
        return repository.listarPlanos();
    }

    public List<Plano> buscarPorTipo(String tipo) {
        return repository.buscarPorTipo(tipo);
    }

    public Plano buscaPorCodigo(int codigo) {
        return repository.buscaPorCodigo(codigo);
    }

    public boolean novoPlano(Plano plano) {
        if (plano != null) {
            repository.novoPlano(plano);
            return true;
        }
        return false;
    }

    public boolean updatePlano(Plano plano) {
        if (plano != null && plano.getCodigo() > 0) {
            return repository.updatePlano(plano);
        }
        return false;
    }

    public boolean deletePlano(int codigo) {
        if (codigo > 0) {
            return repository.deletePlano(codigo);
        }
        return false;
    }
}
