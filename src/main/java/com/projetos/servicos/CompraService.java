package com.projetos.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.entidades.Compra;
import com.projetos.repositorios.CompraRepositorio;

@Service
public class CompraService {

    private final CompraRepositorio compraRepository;  

    @Autowired
    public CompraService(CompraRepositorio compraRepository) {
        this.compraRepository = compraRepository;
    }

    public void salvarCompra(Compra compra) {
        
        compraRepository.save(compra);
    }
}
