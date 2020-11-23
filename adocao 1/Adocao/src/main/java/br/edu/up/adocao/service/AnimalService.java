package br.edu.up.adocao.service;

import br.edu.up.adocao.model.Animal;
import java.util.List;

//Implementacao BD
public interface AnimalService {

    Animal create(Animal animal);

    Animal update(Animal animal);

    void delete(Animal animal);

    Animal get(Long id);

    List<Animal> list();

}
