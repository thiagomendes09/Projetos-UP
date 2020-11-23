package br.edu.up.adocao.service;

import br.edu.up.adocao.model.Animal;
import br.edu.up.adocao.reporsitory.AnimalRepository;
import java.util.List;

//Modificar return null para classe de Banco de dados pode ser utilizado o Animal
//implementacao da JPA aqui
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal create(Animal animal) {
        animalRepository.save(animal);
        return animal;
    }

    @Override
    public Animal update(Animal animal) {
        animalRepository.update(animal);
        return animal;
    }

    @Override
    public void delete(Animal animal) {
        animalRepository.delete(animal.getId());
    }

    @Override
    public Animal get(Long id) {
        return animalRepository.get(id);
    }

    @Override
    public List<Animal> list() {
        return animalRepository.getAll();
    }

}
