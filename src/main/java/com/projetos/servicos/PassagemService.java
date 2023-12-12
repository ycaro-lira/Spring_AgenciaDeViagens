package com.projetos.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.entidades.Passagem;
import com.projetos.repositorios.PassagemRepositorio;

@Service
public class PassagemService {

    @Autowired
    private PassagemRepositorio passagemRepositorio;

    public List<Passagem> listarTodasPassagens() {
        return passagemRepositorio.findAll();
    }

    public void salvarPassagem(Passagem passagem) {
        passagemRepositorio.save(passagem);
    }

    public void excluirPassagem(Long id) {
        passagemRepositorio.deleteById(id);
    }

}
