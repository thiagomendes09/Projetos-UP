package br.edu.up.adocao.controller;

import br.edu.up.adocao.cli.Prompt;

public class AdocaoControllerImpl implements AdocaoController {

    private final AnimalController animalController;
    private final AdotantesController adotantesController;
    private final DoacoesController doacoesController;

    public AdocaoControllerImpl(AnimalController animalController, AdotantesController adotantesController, DoacoesController doacoesController) {
        this.animalController = animalController;
        this.adotantesController = adotantesController;
        this.doacoesController = doacoesController;
    }

    @Override
    public void gerenciarAdocoes() {
        Prompt prompt = new Prompt();
        prompt.mostrarMensagem("Bem vindo ao Adopet");
        int opcao = 0;
        while (opcao != -1) {
            opcao = prompt.selecionarMenu("Gerenciar animais", "Gerenciar Adotantes", "Gerenciar Doaçoes");
            switch (opcao) {
                case 1:
                    animalController.gerenciarAnimais();
                    break;
                case 2:
                    adotantesController.gerenciarAdotantes();
                    break;
                case 3:
                    doacoesController.gerenciarDoacoes();
                    break;
            }

        }

    }

}
