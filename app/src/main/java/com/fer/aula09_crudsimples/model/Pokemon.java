package com.fer.aula09_crudsimples.model;

public class Pokemon {
    private int cod;
    private String nome, tipo, numero;

    public Pokemon() {
    }

    public Pokemon(String nome, String tipo, String numero) {
        this.nome = nome;
        this.tipo = tipo;
        this.numero = numero;
    }

    public Pokemon(int cod, String nome, String tipo, String numero) {
        this.cod = cod;
        this.nome = nome;
        this.tipo = tipo;
        this.numero = numero;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
