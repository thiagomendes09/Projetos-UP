package com.java;


import java.util.ArrayList;
import java.util.List;

public class RepositorioDeItens {

    private String tipo;
    private String arquivo;
    public RepositorioDeItens(String arquivo) {
        this.tipo = arquivo.substring(arquivo.lastIndexOf(".")+1);
        this.arquivo = arquivo;
    }

    public String getArquivo() {
        return arquivo;
    }

    //create
    public String create(Item item){
       List<Item> items = listAll();
       items.add(item);
       return formatar(items);
    }

    //read
    public List<Item> listAll(){
        List<String> linhas = ArquivoUtil.lerArquivo(arquivo);
        linhas.remove(0);
        return transformarEmItems(linhas);
    }

    public List<Item> transformarEmItems(List<String> linhas){
        List<Item> items = new ArrayList<>();
        for (String linha:linhas){
            if (tipo.equals("txt")){
                items.add(lerTxt(linha));

            }else{
                items.add(lerCsv(linha));
            }
        }
        return items;
    }

    //update
    public String update(String nome,Item item){
          List<Item> items = listAll();
          for (Item itemAtual:items){
              if (itemAtual.getNome().equals(nome)){
                  itemAtual.setNome(item.getNome());
                  itemAtual.setPreco(item.getPreco());
                  break;
              }
          }
        return formatar(items);
    }

    //delete
    public String delete(Item item){
        List<Item> items = listAll();
        items.removeIf(itemAtual -> itemAtual.getNome().equals(item.getNome()));
        return formatar(items);
    }

    protected String formatar (List<Item> items){
        String titulo;
        if (tipo.equals("txt")){
            titulo = "PRECO\tBEBIDA";

        }else{
            titulo = "PRATO;PRECO";
        }
        List<String> linhas = new ArrayList<>();
        linhas.add(titulo);
        for (Item item:items){
            if (tipo.equals("txt")){
                linhas.add(escreverTxt(item));
            }else{
              linhas.add(escreverCsv(item));
            }
        }
        return String.join("\n",linhas);
    }

    private Item lerTxt(String linha) {
        //leitor de tab
        String[]partes=linha.split("\t");
        //subistituir virgulas malignas
        partes[0]=partes[0].replace(',','.');
        float preco=Float.parseFloat(partes[0]);
        String nome=partes[1];
        return new Item(nome,preco);
    }

    private Item lerCsv(String linha) {
        //leitor de ;
        String[]partes=linha.split(";");
        float preco=Float.parseFloat(partes[1].replace(',','.'));
        String nome=partes[0];
        return new Item(nome,preco);
    }

    private String escreverTxt(Item item){
        return String.format("%.2f",item.getPreco())+"\t"+item.getNome();
    }

    private String escreverCsv(Item item){
        return item.getNome() + ";" + String.format("%.2f",item.getPreco());
    }

}
