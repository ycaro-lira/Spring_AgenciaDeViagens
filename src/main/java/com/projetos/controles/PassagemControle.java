package com.projetos.controles;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projetos.entidades.Cliente;
import com.projetos.entidades.Passagem;
import com.projetos.repositorios.PassagemRepositorio;
import com.projetos.servicos.PassagemService;

@Controller
@RequestMapping("/passagens")
public class PassagemControle {

	 @Autowired
	    private PassagemRepositorio passagemRepositorio;
	 
	 @Autowired
	    private PassagemService passagemService;



    @GetMapping("/registrar")
    public ModelAndView mostrarFormulario() {
        return new ModelAndView("passagem/formulario-passagem");
    }

    @PostMapping("/registrar")
    public String registrarPassagem(@RequestParam String saindo, @RequestParam String indo, @RequestParam String valor) {
        Passagem passagem = new Passagem(saindo, indo, valor);
        passagemService.salvarPassagem(passagem);
        return "redirect:/passagens/listar";
    }



    @GetMapping("/editar/{id}")
    public ModelAndView editarPassagem(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("passagem/editar-passagem");
        Passagem passagem = passagemRepositorio.findById(id).orElse(new Passagem());
        modelAndView.addObject("passagem", passagem);
        return modelAndView;
    }


    @PostMapping("/editar/{id}")
    public String atualizarPassagem(@PathVariable Long id, Passagem passagemAtualizada) {
        Passagem passagemExistente = passagemRepositorio.findById(id).orElse(null);

        if (passagemExistente != null) {
            // Atualizar os campos relevantes
            passagemExistente.setSaindo(passagemAtualizada.getSaindo());
            passagemExistente.setIndo(passagemAtualizada.getIndo());
            passagemExistente.setValor(passagemAtualizada.getValor());

            passagemRepositorio.save(passagemExistente);
        }
        return "redirect:/passagens/listar";
    }

    

   
    
    @GetMapping("/deletar/{id}")
    public String deletePassagem(@PathVariable Long id) {
    	passagemService.excluirPassagem(id);
        return "redirect:/passagens/listar";
    }


    @GetMapping("/listar")
    public ModelAndView listarPassagens() {
        List<Passagem> passagens = passagemService.listarTodasPassagens();
        ModelAndView modelAndView = new ModelAndView("passagem/lista-passagens");
        modelAndView.addObject("passagens", passagens);
        return modelAndView;
    }
}