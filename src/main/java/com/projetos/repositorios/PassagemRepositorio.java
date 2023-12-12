package com.projetos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetos.entidades.Passagem;

public interface PassagemRepositorio extends JpaRepository<Passagem, Long> {
}
