package iftm.edu.br.tspi.pmvc.xande.menefreda.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iftm.edu.br.tspi.pmvc.xande.menefreda.repository.PacienteRepository;
import iftm.edu.br.tspi.pmvc.xande.menefreda.repository.PlanoRepository;
import iftm.edu.br.tspi.pmvc.xande.menefreda.repository.ContratoRepository;
import iftm.edu.br.tspi.pmvc.xande.menefreda.domain.Contrato;

@Controller
@RequestMapping("/contratos")
public class ContratoController {
    private final ContratoRepository contratoRepository;
    private final PacienteRepository pacienteRepository;
    private final PlanoRepository planoRepository;

    public static final String URL_LISTA = "contratos/listaContrato";
    public static final String URL_FORM = "contratos/formContrato";
    public static final String URL_FORM_LISTA = "redirect:/contrato";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "contrato";
    public static final String ATRIBUTO_LISTA = "contratos";

    public ContratoController(ContratoRepository contratoRepository, PacienteRepository pacienteRepository, PlanoRepository planoRepository) {
        this.contratoRepository = contratoRepository;
        this.pacienteRepository = pacienteRepository;
        this.planoRepository = planoRepository;
    }

    @GetMapping("")
    public String listar(Model model) {
        List<Contrato> contratos = contratoRepository.listar();
        model.addAttribute(ATRIBUTO_LISTA, contratos);
        return URL_LISTA;
    }
}
