package br.edu.up.adocao.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Formattable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//command line interface
//coracao muito complicado
public class Prompt {

    private final BufferedReader reader;

    public Prompt() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String asString(Boolean valor) {
        if (valor != null) {
            return valor ? "S" : "N";
        }
        return null;
    }

    public static String mostrarTexto(String texto, String valor) {
        String formatado = "";
        if (valor != null) {
            formatado = " (" + valor + ")";
        }
        return texto + formatado;
    }

    public static String lerValor(String valorAtual) {
        String valor = new Prompt().lerString();
        if (valor.isEmpty()) {
            valor = valorAtual;
        }
        return valor;
    }

    public static Boolean lerValor(Boolean valorAtual) {
        Boolean valor = new Prompt().lerBoolean();
        if (valor == null) {
            return valorAtual;
        }
        return valor;
    }

    public void mostrarMensagem(String mensagem) {

        System.out.println(mensagem);

    }

    public int selecionarMenu(String... opcoes) {
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + " - " + opcoes[i]);
        }
        System.out.println("0 para voltar");
        int opcao = lerInt(0, opcoes.length);
        return opcao == 0 ? -1 : opcao;
    }

    public String lerString() {

        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean lerBoolean() {
        while (true) {
            String opcao = lerString();
            if (opcao.isEmpty()) {
                return null;
            }
            if (opcao.equalsIgnoreCase("S") || opcao.equalsIgnoreCase("N")) {
                return opcao.equalsIgnoreCase("S");
            }
            System.out.println("Digite S ou N.");
        }
    }

    public <T> void mostrarLista(List<T> lista, Function<T, Object>... colunas) {
        int[] sizes = calcularTamanhos(lista, colunas);
        int index = 1;
        for (T item : lista) {
            List<String> valores = IntStream.range(0, colunas.length)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(colunas[i], sizes[i]))
                .map(e -> pad(String.valueOf(e.getKey().apply(item)), e.getValue()))
                .collect(Collectors.toList());
            System.out.println("| " + (index++) + " | " + String.join(" | ", valores) + " |");
        }
    }

    public int lerInt(int min, int max) {
        while (true) {
            try {
                int opcao = Integer.parseInt(reader.readLine());
                if (opcao >= min && opcao <= max) {
                    return opcao;
                }
                System.out.println("Digite um numero entre " + min + " e " + max);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido digite novamente.");
            }
        }
    }

    public void exibirDetalhes(Formattable formattable) {

        System.out.printf("%s", formattable);

    }

    private String pad(String valor, Integer size) {
        if (valor == null) {
            valor = "";
        }
        StringBuilder builder = new StringBuilder(valor);
        while (builder.length() <= size) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private <T> int[] calcularTamanhos(List<T> lista, Function<T, Object>[] colunas) {
        int[] sizes = new int[colunas.length];
        for (int i = 0; i < colunas.length; i++) {
            sizes[i] = lista.stream().map(colunas[i]).filter(Objects::nonNull).map(String::valueOf).map(String::length).max(Integer::compareTo).get();
        }
        return sizes;
    }

}
