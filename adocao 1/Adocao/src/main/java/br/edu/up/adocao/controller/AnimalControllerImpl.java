package br.edu.up.adocao.controller;

import br.edu.up.adocao.cli.Prompt;
import br.edu.up.adocao.model.Adocao;
import br.edu.up.adocao.model.Adotante;
import br.edu.up.adocao.model.Animal;
import br.edu.up.adocao.service.AdocaoService;
import br.edu.up.adocao.service.AnimalService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static br.edu.up.adocao.cli.Prompt.lerValor;
import static br.edu.up.adocao.cli.Prompt.mostrarTexto;

//implamantation
public class AnimalControllerImpl implements AnimalController {

    private final AnimalService animalService;
    private final Prompt prompt = new Prompt();
    private final AdotantesController adotantesController;
    private final AdocaoService adocaoService;

    public AnimalControllerImpl(
        AnimalService animalService,
        AdotantesController adotantesController,
        AdocaoService adocaoService) {
        this.animalService = animalService;
        this.adotantesController = adotantesController;
        this.adocaoService = adocaoService;
    }

    @Override
    public void gerenciarAnimais() {
        int opcao = 0;
        while (opcao != -1) {
            opcao = prompt.selecionarMenu("Cadastrar animal", "Listar Animais");
            switch (opcao) {
                case 1:
                    cadastrarAnimal(null);
                case 2:
                    listarAnimais();
            }
        }
    }

    private void listarAnimais() {
        List<Animal> animais = animalService.list();
        if (animais.isEmpty()) {
            prompt.mostrarMensagem("Não há animais cadastrados");
        } else {
            prompt.mostrarLista(animais, Animal::getId, Animal::getNome, Animal::getEspecie, Animal::getRaca);
        }
        prompt.mostrarMensagem("Selecione o animal:");
        int index = prompt.lerInt(0, animais.size());
        if (index != 0) {
            gerenciarAnimal(animais.get(index - 1));

        }
    }

    private void gerenciarAnimal(Animal animal) {
        int opcao = 0;
        while (opcao != -1) {
            prompt.exibirDetalhes(animal);
            prompt.mostrarMensagem("Digite a opcao que deseja executar:");
            opcao = prompt.selecionarMenu("Editar", "Excluir", "Adotar", "Liberar para adocao", "Marcar retorno");
            switch (opcao) {
                case 1:
                    cadastrarAnimal(animal);
                    break;
                case 2:
                    animalService.delete(animal);
                    break;
                case 3:
                    adotarAnimal(animal);
                    break;
                case 4:
                    liberarParaAdocao(animal);
                    break;
                case 5:
                    adicionarDataRetorno(animal);
                    break;
            }
        }
    }

    private void liberarParaAdocao(Animal animal) {
        if (animal.getLiberadoParaAdocao() != null && animal.getLiberadoParaAdocao()) {
            prompt.mostrarMensagem("Animal ja liberado para adocao.");
            return;
        }
        animal.setLiberadoParaAdocao(true);
        animalService.update(animal);
        prompt.mostrarMensagem("animal liberado para adocao!!");
    }

    private void adotarAnimal(Animal animal) {
        Adotante adotante = adotantesController.selecionarAdotante();
        if (adotante == null) {
            return;
        }
        if (adotante.getMaltratosAnimais()) {
            prompt.mostrarMensagem("Esta pessoa nao é elegivel para adocao!!!");
            return;
        }
        Adocao adocao = new Adocao(animal, adotante);
        animal.setDataAdocao(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
        animalService.update(animal);
        adocaoService.create(adocao);
    }

    private void adicionarDataRetorno(Animal animal) {
        prompt.mostrarMensagem("Digite a data de retorno:");
        String dataDeRetorno = prompt.lerString();
        animal.setDataRetorno(dataDeRetorno);
        animalService.update(animal);
    }

    private void cadastrarAnimal(Animal animal) {
        if (animal == null) {
            animal = new Animal();
        }
        prompt.mostrarMensagem("Digite os dados do animal:");
        prompt.mostrarMensagem(mostrarTexto("Nome:", animal.getNome()));
        animal.setNome(lerValor(animal.getNome()));
        prompt.mostrarMensagem(mostrarTexto("Data de nascimento:", animal.getDataNasc()));
        animal.setDataNasc(lerValor(animal.getDataNasc()));
        prompt.mostrarMensagem(mostrarTexto("Local de Resgate:", animal.getLocalResgate()));
        animal.setLocalResgate(lerValor(animal.getLocalResgate()));
        prompt.mostrarMensagem(mostrarTexto("Identificar especie:", animal.getEspecie()));
        animal.setEspecie(lerValor(animal.getEspecie()));
        prompt.mostrarMensagem(mostrarTexto("Indentificar raca:", animal.getRaca()));
        animal.setRaca(lerValor(animal.getRaca()));
        prompt.mostrarMensagem(mostrarTexto("O animal possui alguma doenca, especifique:", animal.getDoencas()));
        animal.setDoencas(lerValor(animal.getDoencas()));
        prompt.mostrarMensagem(mostrarTexto("Cirurgias:", animal.getCirurgias()));
        animal.setCirurgias(lerValor(animal.getCirurgias()));
        prompt.mostrarMensagem(mostrarTexto("O animal foi castrado?", Prompt.asString(animal.getCastracao())));
        animal.setCastracao(lerValor(animal.getCastracao()));
        prompt.mostrarMensagem(mostrarTexto("O animal foi Vermifugado?", Prompt.asString(animal.getVermifugacao())));
        animal.setVermifugacao(lerValor(animal.getVermifugacao()));
        prompt.mostrarMensagem(mostrarTexto("Vacinas:", animal.getVacinas()));
        animal.setVacinas(lerValor(animal.getVacinas()));
        if (animal.getId() == null) {
            animalService.create(animal);
        } else {
            animalService.update(animal);
        }
    }

}


