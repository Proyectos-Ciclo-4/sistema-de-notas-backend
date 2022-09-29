package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.domain.commands.EliminarTema;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EliminarTemaUseCase {
    private MongoViewRepository mongoViewRepository;

    public EliminarTemaUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<EliminarTema> apply(Mono<EliminarTema> eliminarTemaMono) {
        return eliminarTemaMono
                .doOnNext(eliminarTema -> {
                    this.mongoViewRepository.eliminarTareasPorTema(
                            eliminarTema.getCursoID(),
                            eliminarTema.getTemaID()
                    );
                })
                .doOnNext(eliminarTema -> {
                    this.mongoViewRepository.eliminarTema(
                            eliminarTema.getCursoID(),
                            eliminarTema.getTemaID()
                    );
                })

                ;

    }
}
