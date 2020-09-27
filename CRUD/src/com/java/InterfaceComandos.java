package com.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfaceComandos {
    private static RepositorioDeItens repositorioDeVinhos = new RepositorioDeItens("./vinhos-tabulados.txt");
    private static RepositorioDeItens repositorioDePratos = new RepositorioDeItens("./pratos.csv");
    private static RepositorioDeItens repositorioDeBebidas = new RepositorioDeItens("./bebidas-tabuladas.txt");
    private static RepositorioDePedido repositorioDePedido = new RepositorioDePedido();
    public static void main(String[] args) {
        exibirSaudacao();
        selecionarOpcoesPedido();
    }

    private static void selecionarOpcoesPedido() {
        System.out.println("Selecione o que deseja fazer");
        System.out.println("(L)Listar pedidos");
        System.out.println("(N)Novo pedido");
        System.out.println("(A)alterar cardapio");
        System.out.println("(S)Sair");
        String opcao =  lerOpcao(null,'L','N','A','S');
        if (opcao.equals("L")){
            listarPedidos();
        }else if (opcao.equals("N")){
            criarPedido();
        }else if (opcao.equals("A")){
            alterarCardapio();
        } if (!opcao.equals("S")){
            selecionarOpcoesPedido();
        }
    }

    private static void alterarCardapio() {
        RepositorioDeItens repositorioDeItens = escolherCardapio();
        if (repositorioDeItens == null){
            return;
        }
        exibirItems(repositorioDeItens.listAll(),"ITEM");
        System.out.println("Selecione a opercao que deseja fazer, (A)adicionar (M)modificar (D)deletar (V)voltar");
        String opcao = lerOpcao(null,'A','M','D','V');
        if (opcao.equals("A")){
            adicionarItem(repositorioDeItens);
        }else if (opcao.equals("M")){
            modificarItem(repositorioDeItens);
        }else if (opcao.equals("D")){
            deletarItem(repositorioDeItens);
        } if (!opcao.equals("V")) {
            alterarCardapio();
        }
    }

    private static void deletarItem(RepositorioDeItens repositorioDeItens) {
        List<Item> items = repositorioDeItens.listAll();
        exibirItems(items,"ITEM");
        System.out.println("Selecione o item que deseja deletar");
        String opcao = lerOpcao(new Range(1,items.size()));
        Item item = items.get(Integer.parseInt(opcao)-1);
        ArquivoUtil.salvarArquivo(repositorioDeItens.delete(item),repositorioDeItens.getArquivo());
    }

    private static void modificarItem(RepositorioDeItens repositorioDeItens) {
        List<Item> items = repositorioDeItens.listAll();
        exibirItems(items,"ITEM");
        System.out.println("Selecione o item que deseja modificar");
        String opcao = lerOpcao(new Range(1,items.size()));
        Scanner scanner = new Scanner(System.in);
        System.out.println("digite o nome : ");
        String nome = scanner.nextLine();
        System.out.println("digite o preco : ");
        float preco = lerPreco(scanner);
        Item item = new Item(nome,preco);
        ArquivoUtil.salvarArquivo(repositorioDeItens.update(items.get(Integer.parseInt(opcao)-1).getNome(),item),repositorioDeItens.getArquivo());
    }

    private static void adicionarItem(RepositorioDeItens repositorioDeItens) {
           Scanner scanner = new Scanner(System.in);
        System.out.println("digite o nome : ");
        String nome = scanner.nextLine();
        System.out.println("digite o preco : ");
        float preco = lerPreco(scanner);
        Item item = new Item(nome,preco);
        ArquivoUtil.salvarArquivo(repositorioDeItens.create(item),repositorioDeItens.getArquivo());
    }

    private static float lerPreco(Scanner scanner) {
        Float preco = null;
        while (preco == null){
            try {
                preco = Float.parseFloat(scanner.nextLine());
            }catch (Exception e){
                System.out.println("erro bobao!!");
            }
        }
        return preco;
    }


    private static RepositorioDeItens escolherCardapio() {
        System.out.println("Selecione o cardapio que deseja modificar, (P)Pratos (B)Bebidas (V)Vinhos (R)Retornar");
        String opcao = lerOpcao(null,'P','B','V','R');
        if (opcao.equals("R")){
            return null;
        }
        return selecionarRepositorio(opcao);
    }

    private static RepositorioDeItens selecionarRepositorio(String opcao) {
        switch (opcao){
            case "P":
                return repositorioDePratos;
            case "B":
                return repositorioDeBebidas;
            case "V":
                return repositorioDeVinhos;
            default:
                throw new IllegalStateException();
        }
    }

    private static void listarPedidos() {
       List<Pedido> pedidos = repositorioDePedido.listAll();
       exibirPedidos(pedidos);
        System.out.println("O que deseja fazer ? (M)Modificar (D)Deletar (V)Voltar");
        String opcao = lerOpcao(null,'M','D','V');
        if (opcao.equals("M")){
            modificarPedido(pedidos);
        }else if (opcao.equals("D")){
            deletarPedido(pedidos);
        } if (!opcao.equals("V")){
            listarPedidos();
        }
    }

    private static void modificarPedido(List<Pedido> pedidos) {
        System.out.println("Selecione qual pedido deseja modificar:");
        String opcao = lerOpcao(new Range(1,pedidos.size()));
        Pedido pedido =  pedidos.get(Integer.parseInt(opcao)-1);
        Pedido pedidoAtualizado = mostrarCardapios();
        String conteudo = repositorioDePedido.update(pedidoAtualizado);
        ArquivoUtil.salvarArquivo(conteudo,"./pedidos/" + pedido.getArquivo());
    }

    private static void deletarPedido(List<Pedido> pedidos) {
        System.out.println("Selecione qual pedido deseja deletar:");
        String opcao = lerOpcao(new Range(1,pedidos.size()));
        Pedido pedido =  pedidos.get(Integer.parseInt(opcao)-1);
        String data = pedido.getArquivo().replace("DadosTrabalho-","").replace(".txt","");
        repositorioDePedido.delete(data);
    }

    private static void exibirPedidos(List<Pedido> pedidos) {
        System.out.println("|   |" + adicionarEspaco("PEDIDO", 45) + "|");
        for (int i = 0; i < pedidos.size(); i++) {
            String nomeFormatado = adicionarEspaco(pedidos.get(i).getArquivo(), 45);
            String indiceFormatado = adicionarEspaco(String.valueOf(i + 1), 3);
            System.out.println("|" + indiceFormatado + "|" + nomeFormatado + "|");
        }
    }

    private static void criarPedido() {
        Pedido pedido = mostrarCardapios();
        if (exibirPedido(pedido)){
            criarPedido();
        }else{
            String agora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
            ArquivoUtil.salvarArquivo(repositorioDePedido.create(pedido),"./pedidos/DadosTrabalho-"+agora+".txt");
        }

    }

    private static boolean exibirPedido(Pedido pedido) {
      exibirItems(pedido.getItens(),"ITEM");
        System.out.println("Deseja alterar algo no pedido ? (S)sim (N)nao");
        String opcao = lerOpcao(null,'S','N');
        return opcao.equals("S");
    }

    private static void exibirItems(List<Item> itens,String titulo) {
        System.out.println("|   |" + adicionarEspaco(titulo, 45) + "|" + adicionarEspaco("PRECO", 10) + "|");
        for (int i = 0; i < itens.size(); i++) {
            String nomeFormatado = adicionarEspaco(itens.get(i).getNome(), 45);
            String precoFormatado = adicionarEspaco(String.format("%.2f", itens.get(i).getPreco()), 10);
            String indiceFormatado = adicionarEspaco(String.valueOf(i + 1), 3);
            System.out.println("|" + indiceFormatado + "|" + nomeFormatado + "|" + precoFormatado + "|");
        }
    }

    private static Pedido mostrarCardapios() {
       List<Item> items = new ArrayList<>();
       items.addAll(mostrarCardapio(repositorioDePratos.listAll(),"PRATOS"));
       items.addAll(mostrarCardapio(repositorioDeBebidas.listAll(),"BEBIDAS"));
       items.addAll(mostrarCardapio(repositorioDeVinhos.listAll(),"VINHOS"));
       String observacao = lerObservacao();
       return new Pedido(items,observacao);
    }

    private static String lerObservacao() {
        System.out.println("Deseja inserir alguma observacao ?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static List<Item> mostrarCardapio(List<Item> itens, String titulo) {
        String opcao = "";
        List<Item> itemsSelecionados = new ArrayList<>();
        while(!opcao.equals("P")) {
            System.out.println("Selecione o item desejado ou pressione (P) para o proximo");
            exibirItems(itens,titulo);
            opcao = lerOpcao(new Range(1,itens.size()),'P');
            if (!opcao.equals("P")) {
                Integer numero = transformarEmNumero(opcao);
                itemsSelecionados.add(itens.get(numero-1));
            }
        }
        return itemsSelecionados;
    }

    private static String adicionarEspaco(String valor, int tamanho) {
        String formatado= valor;
        while(formatado.length()<tamanho){
            formatado+=" ";
        }
        return formatado;
    }


    private static void exibirSaudacao() {
        System.out.println("Bem vindo ao novo restaurante do gesonel");
    }

    private static String lerOpcao(Range range,char...opcoes){
        Scanner scanner = new Scanner(System.in);
        String opcao = validar (scanner.nextLine(),range,opcoes);
        if (opcao != null ){
            return opcao.toUpperCase();

        }else{
            System.out.println("opcao invalida, seu bobao, seleciona denovo");
            return lerOpcao(range,opcoes);
        }
    }

    private static Integer transformarEmNumero(String texto){
        try{
            return Integer.parseInt(texto);
        }catch (NumberFormatException e){
          return null;
        }
    }

    private static String validar(String opcao, Range range, char[] opcoes) {
        Integer numero = transformarEmNumero(opcao);

        if (range != null && numero != null){
            return numero >= range.min && numero <= range.max?opcao:null;
        }
        if (opcao.length() != 1 || opcoes.length == 0){
            return null;
        }
        for (char c:opcoes){
            if (opcao.toUpperCase().charAt(0) == c){
                return opcao;
            }
        }
        return null;
    }
}

     class Range{
        int min;
        int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

