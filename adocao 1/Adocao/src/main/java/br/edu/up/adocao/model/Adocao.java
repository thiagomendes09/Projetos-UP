package br.edu.up.adocao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adocao")

public class Adocao {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Animal animal;
    @ManyToOne
    private Adotante adotante;

    public Adocao(Animal animal, Adotante adotante) {
        this.animal = animal;
        this.adotante = adotante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Adotante getAdotante() {
        return adotante;
    }

    public void setAdotante(Adotante adotante) {
        this.adotante = adotante;
    }

}
