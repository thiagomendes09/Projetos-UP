package br.edu.up.adocao.reporsitory;

import br.edu.up.adocao.model.Animal;
import java.util.List;
import javax.persistence.EntityManager;

public class AnimalRepositoryJpa implements AnimalRepository {

    private final EntityManager entityManager;

    public AnimalRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Animal animal) {
        entityManager.getTransaction().begin();
        entityManager.persist(animal);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Animal animal) {
        entityManager.getTransaction().begin();
        entityManager.merge(animal);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Animal animal = entityManager.find(Animal.class, id);
        entityManager.remove(animal);
        entityManager.getTransaction().commit();
    }

    @Override
    public Animal get(Long id) {
        return entityManager.find(Animal.class, id);
    }

    @Override
    public List<Animal> getAll() {
        return entityManager.createQuery("select a from Animal a", Animal.class).getResultList();
    }

}
