package br.edu.iftm.tspi.pmvc.sistema_clientes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.edu.iftm.tspi.pmvc.sistema_clientes.repository.ClienteRepository;
import br.edu.iftm.tspi.pmvc.sistema_clientes.domain.Cliente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ClienteController {

    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clientes")
    public String getClientes(Model model) {
        model.addAttribute("clientes", repository.getClientes());
        return "ClientesView";
    }

    @GetMapping("/add")
    public String exibirFormularioAdd(Model model) {
        model.addAttribute("cliente", new Cliente(null, null, null));
        return "clienteAdd";
    }
    @PostMapping("/add")
    public String add(Model model,@ModelAttribute Cliente cliente) {
        repository.addCliente(cliente);
        
        return getClientes(model);
    }
}