package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.domain.commands.EliminarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EliminarTareaUseCase {

    private final MongoViewRepository mongoViewRepository;
    private final Blockchain blockchain;

    public EliminarTareaUseCase(MongoViewRepository mongoViewRepository, Blockchain blockchain) {
        this.mongoViewRepository = mongoViewRepository;
        this.blockchain = blockchain;
    }


    public Mono<EliminarTarea> apply(Mono<EliminarTarea> eliminarTareaMono){
        return eliminarTareaMono
                .doOnNext(eliminarTarea -> {
                    this.mongoViewRepository.eliminarTareaDeEstudiante(
                            eliminarTarea.getCursoID(),
                            eliminarTarea.getTareaID(),
                            eliminarTarea.getTemaID()
                    );
                }).doOnSuccess(eliminarTarea -> blockchain.saveBlock(eliminarTarea,"unote.tareaEliminada", eliminarTarea.getTareaID()))
                .doOnNext(eliminarTarea -> {
                    this.mongoViewRepository.eliminarTareaDeCurso(
                            eliminarTarea.getCursoID(),
                            eliminarTarea.getTareaID(),
                            eliminarTarea.getTemaID()
                    );
                })
                .doOnNext(eliminarTarea -> {
                            this.mongoViewRepository.eliminarVistaTarea(eliminarTarea.getTareaID());
                });

    }
}
