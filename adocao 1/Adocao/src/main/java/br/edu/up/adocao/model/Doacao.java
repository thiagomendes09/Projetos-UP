package br.edu.up.adocao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doacao")

public class Doacao {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String dataRecebida;
    private String quantidade;
    private String tipo;

    public Doacao() {

    }

    public Doacao(Long id, String nome, String dataRecebida, String quantidade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.dataRecebida = dataRecebida;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataRecebida() {
        return dataRecebida;
    }

    public void setDataRecebida(String dataRecebida) {
        this.dataRecebida = dataRecebida;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
