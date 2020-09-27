package com.java;

import java.util.List;

public class Pedido {

    // calcular o total do pedido
   private List <Item> itens;
   private String observacao;
   private String arquivo;

    public Pedido(List<Item> itens, String observacao) {
        this.itens = itens;
        this.observacao = observacao;
    }

    public List<Item> getItens() {
        return itens;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getTotal(){
        float total = 0;
        for (Item item:itens){
            total += item.getPreco();
        }
        return total;
    }

}
