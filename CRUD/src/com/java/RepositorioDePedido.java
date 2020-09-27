package com.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDePedido {

    private RepositorioDeItens repositorioDeItens;

    public RepositorioDePedido() {
        this.repositorioDeItens = new RepositorioDeItens(".txt");
    }

    public String create(Pedido pedido){
        String conteudo = repositorioDeItens.formatar(pedido.getItens());
        conteudo += "\nObservacao : " + pedido.getObservacao();
        conteudo += "\nTotal : " + pedido.getTotal();
        return conteudo;
    }

    public Pedido read(String data){
        List<String> linhas = ArquivoUtil.lerArquivo("./pedidos/DadosTrabalho-" + data + ".txt");
        linhas.remove(0);
        linhas.remove(linhas.size()-1);
        String observacao = linhas.get(linhas.size()-1);
        linhas.remove(linhas.size()-1);
        List<Item> items = repositorioDeItens.transformarEmItems(linhas);
        observacao = observacao.substring(observacao.indexOf(":"));
        return new Pedido(items,observacao);
    }

    public List<Pedido> listAll(){
        File pasta = new File("./pedidos");
        File[] arquivos = pasta.listFiles();
        List<Pedido> pedidos = new ArrayList<>();
        for (File arquivo:arquivos){
           String data = arquivo.getName().replace("DadosTrabalho-","").replace(".txt","");
           Pedido pedido = read(data);
           pedido.setArquivo(arquivo.getName());
           pedidos.add(pedido);
        }
        return pedidos;
    }

    public String update(Pedido pedido){
       return create(pedido);
    }

    public boolean delete(String data){
      return new File("./pedidos/DadosTrabalho-" + data + ".txt").delete();


    }

}
