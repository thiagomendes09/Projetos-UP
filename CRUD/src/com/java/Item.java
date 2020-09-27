package com.java;

public class Item {
    private String nome;
    private float preco;

    public Item (String nome, float preco){
        //variavel referenciando a classe atual
        this.nome=nome;
        this.preco=preco;
    }

    //encapsular itens com get
    public String getNome() {
        return nome;

    }

    public float getPreco() {
        return preco;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}


