package br.edu.up.adocao;

import br.edu.up.adocao.controller.AdocaoController;
import br.edu.up.adocao.controller.AdocaoControllerImpl;
import br.edu.up.adocao.controller.AdotantesController;
import br.edu.up.adocao.controller.AdotantesControllerImpl;
import br.edu.up.adocao.controller.AnimalController;
import br.edu.up.adocao.controller.AnimalControllerImpl;
import br.edu.up.adocao.controller.DoacoesController;
import br.edu.up.adocao.controller.DoacoesControllerImpl;
import br.edu.up.adocao.persistance.PersistenceUtil;
import br.edu.up.adocao.reporsitory.*;
import br.edu.up.adocao.service.AdocaoService;
import br.edu.up.adocao.service.AdocaoServiceImpl;
import br.edu.up.adocao.service.AdotanteService;
import br.edu.up.adocao.service.AdotantesServiceImpl;
import br.edu.up.adocao.service.AnimalService;
import br.edu.up.adocao.service.AnimalServiceImpl;
import br.edu.up.adocao.service.DoacaoService;
import br.edu.up.adocao.service.DoacaoServiceImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManager;

public class App {

    public static Map<Class<?>, Object> context = new HashMap<>();

    public static void init() {
        context.put(EntityManager.class, PersistenceUtil.getEntityManager());

        context.put(AnimalRepository.class, new AnimalRepositoryJpa(getInstance(EntityManager.class)));
        context.put(AdotanteRepository.class, new AdotanteRepositoryJpa(getInstance(EntityManager.class)));
        context.put(AdocaoRepository.class, new AdocaoRepositoryJpa(getInstance(EntityManager.class)));
        context.put(DoacaoRepository.class, new DoacaoRepositoryJpa(getInstance(EntityManager.class)));

        context.put(AnimalService.class, new AnimalServiceImpl(getInstance(AnimalRepository.class)));
        context.put(AdocaoService.class, new AdocaoServiceImpl(getInstance(AdocaoRepository.class)));
        context.put(AdotanteService.class, new AdotantesServiceImpl(getInstance(AdotanteRepository.class)));
        context.put(DoacaoService.class, new DoacaoServiceImpl(getInstance(DoacaoRepository.class)));

        context.put(AdotantesController.class, new AdotantesControllerImpl(getInstance(AdotanteService.class)));
        context.put(AnimalController.class, new AnimalControllerImpl(
            getInstance(AnimalService.class),
            getInstance(AdotantesController.class),
            getInstance(AdocaoService.class)));
        context.put(DoacoesController.class, new DoacoesControllerImpl(getInstance(DoacaoService.class)));
        context.put(AdocaoController.class, new AdocaoControllerImpl(
            getInstance(AnimalController.class),
            getInstance(AdotantesController.class),
            getInstance(DoacoesController.class)));

    }

    public static <T> T getInstance(Class<T> type) {
        return (T) Objects.requireNonNull(context.get(type), type.getName());
    }

    public static void main(String[] args) {
        init();
        AdocaoController adocaoController = getInstance(AdocaoController.class);
        adocaoController.gerenciarAdocoes();
    }

}
