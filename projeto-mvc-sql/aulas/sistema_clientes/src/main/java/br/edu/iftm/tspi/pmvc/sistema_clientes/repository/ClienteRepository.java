package br.edu.iftm.tspi.pmvc.sistema_clientes.repository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.edu.iftm.tspi.pmvc.sistema_clientes.domain.Cliente;

@Component
public class ClienteRepository {
    
    private List<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Carlos", "rua das Couves"));
        clientes.add(new Cliente(2, "Marcos", "rua das Almeidas"));
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }
}
