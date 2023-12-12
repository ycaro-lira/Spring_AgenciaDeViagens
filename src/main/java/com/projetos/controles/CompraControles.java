package com.projetos.controles;

import com.projetos.entidades.Cliente;
import com.projetos.entidades.Compra;
import com.projetos.entidades.Passagem;
import com.projetos.repositorios.ClienteRepositorio;
import com.projetos.repositorios.CompraRepositorio;
import com.projetos.repositorios.PassagemRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraControles {

    private final CompraRepositorio compraRepositorio;
    private final ClienteRepositorio clienteRepositorio; 
    private final PassagemRepositorio passagemRepositorio; 

    @Autowired
    public CompraControles(CompraRepositorio compraRepositorio, ClienteRepositorio clienteRepositorio, PassagemRepositorio passagemRepositorio) {
        this.compraRepositorio = compraRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.passagemRepositorio = passagemRepositorio;
    }

    @GetMapping
    public String getAllCompras(Model model) {
        List<Compra> compras = compraRepositorio.findAll();
        model.addAttribute("compras", compras);
        return "/listaCompras";
    }

    @GetMapping("/{id}")
    public String getCompraById(@PathVariable Long id, Model model) {
        Compra compra = compraRepositorio.findById(id).orElse(null);
        model.addAttribute("compra", compra);
        return "compras/detalhesCompra";
    }

    @GetMapping("/formulario")
    public String showFormulario(Model model) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        List<Passagem> passagens = passagemRepositorio.findAll();

        model.addAttribute("compra", new Compra());
        model.addAttribute("clientes", clientes);
        model.addAttribute("passagens", passagens);

        return "formulario-Compra";
    }
   
    @PostMapping("/comprar")
    public String createCompra(@ModelAttribute("compra") Compra compra) {
        System.out.println("Cliente ID: " + compra.getCliente().getId());
        System.out.println("Passagem ID: " + compra.getPassagem().getId());
        System.out.println("Data da Compra: " + compra.getDataCompra());
        System.out.println("confirme o total da compra: " + compra.getValorTotal());
        
        compraRepositorio.save(compra);
        return "redirect:/home.html";
    }



    @GetMapping("/editar/{id}")
    public String showFormularioEdicao(@PathVariable Long id, Model model) {
        Compra compra = compraRepositorio.findById(id).orElse(null);
        List<Cliente> clientes = clienteRepositorio.findAll();
        List<Passagem> passagens = passagemRepositorio.findAll();

        if (compra != null) {
            model.addAttribute("compra", compra);
            model.addAttribute("clientes", clientes);
            model.addAttribute("passagens", passagens);
            return "editar-compra.html";  
        } else {
            
            return "redirect:/compras";
        }
    }
    
    @PostMapping("/editar/{id}")
    public String processarFormularioEdicao(@PathVariable Long id, @ModelAttribute("compra") Compra compraEditada) {
        
        

        Compra compraExistente = compraRepositorio.findById(id).orElse(null);

        if (compraExistente != null) {
            // Atualizar os campos necessários com base na compraEditada
            compraExistente.setCliente(compraEditada.getCliente());
            compraExistente.setPassagem(compraEditada.getPassagem());
            compraExistente.setValorTotal(compraEditada.getValorTotal());
            compraExistente.setDataCompra(compraEditada.getDataCompra());

            // Salvar as alterações
            compraRepositorio.save(compraExistente);

            return "redirect:/compras";  
        } else {
            
            return "redirect:/compras";
        }
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhesCompra(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/detalhes");

        Compra compra = compraRepositorio.findById(id).orElse(null);

        if (compra != null) {
            modelAndView.addObject("compra", compra);
        } else {
            
            modelAndView.setViewName("redirect:/compras");
        }

        return modelAndView;
    }




    @GetMapping("/deletar/{id}")
    public String deleteCompra(@PathVariable Long id) {
        compraRepositorio.deleteById(id);
        return "redirect:/compras";
    }
}