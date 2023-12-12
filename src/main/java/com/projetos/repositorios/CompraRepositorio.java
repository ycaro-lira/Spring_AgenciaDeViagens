package com.projetos.repositorios;

import com.projetos.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepositorio extends JpaRepository<Compra, Long> {
    
}
