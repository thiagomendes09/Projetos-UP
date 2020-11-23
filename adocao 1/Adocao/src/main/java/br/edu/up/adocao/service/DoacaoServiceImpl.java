package br.edu.up.adocao.service;

import br.edu.up.adocao.model.Doacao;
import br.edu.up.adocao.reporsitory.DoacaoRepository;


import java.util.List;

//implementacao da JPA aqui

public class DoacaoServiceImpl implements DoacaoService {

    private final DoacaoRepository doacaoRepository;

    public DoacaoServiceImpl(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }


    @Override
    public List<Doacao> listAll() {
        return doacaoRepository.getAll();
    }

    @Override
    public Doacao create(Doacao doacao) {
        doacaoRepository.save(doacao);
        return doacao;
    }

}
