package com.projetos.controles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetos.entidades.Cliente;
import com.projetos.repositorios.ClienteRepositorio;

@Controller
@RequestMapping("/clientes")
public class ClienteControle {

    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    
    @GetMapping
    public ModelAndView listarClientes() {
        ModelAndView modelAndView = new ModelAndView("cliente/lista-clientes");
        modelAndView.addObject("clientes", clienteRepositorio.findAll());
        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrarCliente() {
        ModelAndView modelAndView = new ModelAndView("cliente/cadastro-cliente");
        modelAndView.addObject("cliente", new Cliente());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String salvarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
        return "redirect:/home.html";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarCliente(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cliente/editar-cliente");
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);
        modelAndView.addObject("cliente", cliente);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String atualizarCliente(@PathVariable Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepositorio.findById(id).orElse(null);

        if (clienteExistente != null) {
            // Atualizar os campos relevantes
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setSenha(clienteAtualizado.getSenha());

            clienteRepositorio.save(clienteExistente);
        }
        return "redirect:/clientes";
    }

   @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteRepositorio.deleteById(id);
        return "redirect:/clientes";
    }
}