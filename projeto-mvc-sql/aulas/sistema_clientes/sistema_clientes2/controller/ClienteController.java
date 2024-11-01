package br.iftm.xande.sistema_clientes2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.iftm.xande.sistema_clientes2.repository.ClienteRepository;

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
        model.addAttribute("cliente", new Cliente());
        return "clienteAdd";
    }

}