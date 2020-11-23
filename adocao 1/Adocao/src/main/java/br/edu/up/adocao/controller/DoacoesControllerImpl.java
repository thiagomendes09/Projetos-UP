package br.edu.up.adocao.controller;

import br.edu.up.adocao.cli.Prompt;
import br.edu.up.adocao.model.Doacao;
import br.edu.up.adocao.service.DoacaoService;
import java.util.List;

import static br.edu.up.adocao.cli.Prompt.lerValor;
import static br.edu.up.adocao.cli.Prompt.mostrarTexto;

public class DoacoesControllerImpl implements DoacoesController {

    private final DoacaoService doacaoService;
    private final Prompt prompt = new Prompt();

    public DoacoesControllerImpl(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @Override
    public void gerenciarDoacoes() {
        int opcao = 0;
        while (opcao != -1) {
            opcao = prompt.selecionarMenu("Cadastrar doacao", "Listar doacoes");
            switch (opcao) {
                case 1:
                    cadastrarDoacao();
                case 2:
                    listarDoacoes();
            }
        }
    }

    private void listarDoacoes() {
        List<Doacao> doacoes = doacaoService.listAll();
        prompt.mostrarLista(doacoes, Doacao::getId, Doacao::getNome, Doacao::getDataRecebida, Doacao::getQuantidade, Doacao::getTipo);
        prompt.mostrarMensagem("digite 0 para voltar.");
    }

    private void cadastrarDoacao() {
        Doacao doacao = new Doacao();
        prompt.mostrarMensagem("Digite os dados da doacao:");
        prompt.mostrarMensagem(mostrarTexto("Nome:", doacao.getNome()));
        doacao.setNome(lerValor(doacao.getNome()));
        prompt.mostrarMensagem(mostrarTexto("data do recebimento:", doacao.getDataRecebida()));
        doacao.setDataRecebida(lerValor(doacao.getDataRecebida()));
        prompt.mostrarMensagem(mostrarTexto("quantidade:", doacao.getQuantidade()));
        doacao.setQuantidade(lerValor(doacao.getQuantidade()));
        prompt.mostrarMensagem(mostrarTexto("tipo?", doacao.getTipo()));
        doacao.setTipo(lerValor(doacao.getTipo()));
        doacaoService.create(doacao);
    }

}
