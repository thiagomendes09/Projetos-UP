package br.edu.up.adocao.controller;

import br.edu.up.adocao.cli.Prompt;
import br.edu.up.adocao.model.Adotante;
import br.edu.up.adocao.service.AdotanteService;
import java.util.List;

import static br.edu.up.adocao.cli.Prompt.lerValor;
import static br.edu.up.adocao.cli.Prompt.mostrarTexto;

public class AdotantesControllerImpl implements AdotantesController {

    private final AdotanteService adotanteService;
    private final Prompt prompt = new Prompt();

    public AdotantesControllerImpl(AdotanteService adotanteService) {
        this.adotanteService = adotanteService;
    }

    @Override
    public void gerenciarAdotantes() {
        int opcao = 0;
        while (opcao != -1) {
            opcao = prompt.selecionarMenu("Cadastrar adotante", "Listar adotante");
            switch (opcao) {
                case 1:
                    cadastrarAdotante(null);
                case 2:
                    listarAdotante();
            }
        }
    }

    @Override
    public Adotante selecionarAdotante() {
        prompt.mostrarMensagem("Selecione a opcao desejada:");
        int opcao = prompt.selecionarMenu("Selecionar existente", "Cadastrar novo");
        switch (opcao) {
            case 0:
                return null;
            case 1:
                return selecionarExistente();
            case 2:
                return cadastrarAdotante(null);
            default:
                throw new IllegalStateException();
        }
    }

    private void listarAdotante() {
        prompt.mostrarMensagem("Selecione o adotante:");
        List<Adotante> adotantes = adotanteService.listAll();
        if (adotantes.isEmpty()){
            prompt.mostrarMensagem("Nao existem adotantes cadastrados.");
        }else {
            prompt.mostrarLista(adotantes, Adotante::getId, Adotante::getNomeCompleto, Adotante::getCpf);
        }
        int opcao = prompt.lerInt(0, adotantes.size());
        if (opcao != 0) {
            gerenciarAdotante(adotantes.get(opcao - 1));
        }
    }

    private void gerenciarAdotante(Adotante adotante) {
        int opcao = 0;
        while (opcao != -1) {
            prompt.exibirDetalhes(adotante);
            prompt.mostrarMensagem("Digite a opcao que deseja executar:");
            opcao = prompt.selecionarMenu("Editar", "Excluir");
            switch (opcao) {
                case 1:
                    cadastrarAdotante(adotante);
                    break;
                case 2:
                    adotanteService.delete(adotante);
                    break;
            }
        }
    }

    private Adotante cadastrarAdotante(Adotante adotante) {
        if (adotante == null) {
            adotante = new Adotante();
        }
        prompt.mostrarMensagem("Digite os dados do adotante:");
        prompt.mostrarMensagem(mostrarTexto("Nome completo:", adotante.getNomeCompleto()));
        adotante.setNomeCompleto(lerValor(adotante.getNomeCompleto()));
        prompt.mostrarMensagem(mostrarTexto("endereco:", adotante.getEndereco()));
        adotante.setEndereco(lerValor(adotante.getEndereco()));
        prompt.mostrarMensagem(mostrarTexto("Telefone:", adotante.getTelefone()));
        adotante.setTelefone(lerValor(adotante.getTelefone()));
        prompt.mostrarMensagem(mostrarTexto("Historico de maltrato a animais?",
            Prompt.asString(adotante.getMaltratosAnimais())));
        adotante.setMaltratosAnimais(lerValor(adotante.getMaltratosAnimais()));
        prompt.mostrarMensagem(mostrarTexto("Cpf do adotante:", adotante.getCpf()));
        adotante.setCpf(lerValor(adotante.getCpf()));
        if (adotante.getId() == null) {
            return adotanteService.create(adotante);
        } else {
            return adotanteService.update(adotante);
        }
    }

    private Adotante selecionarExistente() {
        prompt.mostrarMensagem("Selecione o adotante:");
        List<Adotante> adotantes = adotanteService.listAll();
        prompt.mostrarLista(adotantes, Adotante::getId, Adotante::getNomeCompleto, Adotante::getCpf);
        int opcao = prompt.lerInt(0, adotantes.size());
        if (opcao != 0) {
            return adotantes.get(opcao - 1);
        }
        return null;
    }

}
