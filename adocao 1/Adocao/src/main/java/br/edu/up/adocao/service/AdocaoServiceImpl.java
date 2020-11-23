package br.edu.up.adocao.service;

import br.edu.up.adocao.model.Adocao;
import br.edu.up.adocao.reporsitory.AdocaoRepository;


public class AdocaoServiceImpl implements AdocaoService {

    //implementacao da JPA aqui

    private final AdocaoRepository adocaoRepository;

    public AdocaoServiceImpl(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }


    @Override
    public Adocao create(Adocao adocao) {
       adocaoRepository.save(adocao);
       return adocao;
    }

}
