package com.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ArquivoUtil {

    public static List<String> lerArquivo(String arquivo){
        List<String> linhas;

        try {
            linhas= Files.readAllLines(new File(arquivo).toPath());
            //caso nao haja uma forma de ler o arquivo, exception
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return linhas;
    }

    public static void salvarArquivo(String conteudo, String arquivo){
        try {
            Files.write(new File(arquivo).toPath(),conteudo.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
