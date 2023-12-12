package com.projetos.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetos.entidades.Cliente;
import com.projetos.repositorios.ClienteRepositorio;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarTodosClientes() {
        return clienteRepositorio.findAll();
    }

    public Cliente obterClientePorId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

	
}
