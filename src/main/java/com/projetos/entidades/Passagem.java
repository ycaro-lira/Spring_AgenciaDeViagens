package com.projetos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Passagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String saindo;
    private String indo;
    private String valor;

    @OneToMany(mappedBy = "passagem")
    private List<Compra> compras;

    // Construtor vazio
    public Passagem() {
    }

    // Construtor com campos
    public Passagem(String saindo, String indo, String valor) {
        this.saindo = saindo;
        this.indo = indo;
        this.valor = valor;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaindo() {
        return saindo;
    }

    public void setSaindo(String saindo) {
        this.saindo = saindo;
    }

    public String getIndo() {
        return indo;
    }

    public void setIndo(String indo) {
        this.indo = indo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}
