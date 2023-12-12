package com.projetos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

   // @EntityGraph(attributePaths = "Passagem")
    //List<Cliente> findAll();
}
