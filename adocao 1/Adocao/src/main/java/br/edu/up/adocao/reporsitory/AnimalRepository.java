package br.edu.up.adocao.reporsitory;

import br.edu.up.adocao.model.Animal;
import java.util.List;

public interface AnimalRepository {

    void save(Animal animal);

    void update(Animal animal);

    void delete(Long id);

    Animal get(Long id);

    List<Animal> getAll();

}
