package br.edu.up.adocao.service;

import br.edu.up.adocao.model.Adotante;
import br.edu.up.adocao.reporsitory.AdotanteRepository;

import java.util.List;

//implementacao da JPA aqui

public class AdotantesServiceImpl implements AdotanteService {

    private final AdotanteRepository adotanteRepository;

    public AdotantesServiceImpl(AdotanteRepository adotanteRepository) {
        this.adotanteRepository = adotanteRepository;
    }

    @Override
    public Adotante update(Adotante adotante) {
        adotanteRepository.update(adotante);
        return adotante;
    }

    @Override
    public Adotante create(Adotante adotante) {
        adotanteRepository.save(adotante);
        return adotante;
    }

    @Override
    public List<Adotante> listAll() {
        return adotanteRepository.getAll();
    }

    @Override
    public void delete(Adotante adotante) {
        adotanteRepository.delete(adotante.getId());
    }

}
