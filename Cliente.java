package com.example.thiago.app;

/**
 * Created by thiag on 10/07/2016.
 */
public class Cliente {

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    private String nome = "";
    private int id = 0;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
