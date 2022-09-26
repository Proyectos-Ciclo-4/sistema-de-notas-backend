package org.backend.application.repository;

import co.com.sofka.domain.generic.DomainEvent;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.backend.business.gateways.EventRepository;
import org.backend.business.models.vistasmaterializadas.generics.StoredEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Date;

@Slf4j
@Repository
public class MongoEventRepository implements EventRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private final Gson gson = new Gson();

    public MongoEventRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<DomainEvent> findById(String id) {
       var findByIdQuery = new Query(Criteria
                .where("estudiante")
                .is(id));

        return reactiveMongoTemplate
                .find(findByIdQuery, DocumentEventStored.class)
                .sort(Comparator.comparing(documentEventStored ->
                        documentEventStored.getStoredEvent().getOcurredOn()))
                .map(documentEventStored -> {
                    try {
                        return (DomainEvent) gson.fromJson(
                                documentEventStored.getStoredEvent().getEventBody(),
                                Class.forName(documentEventStored.getStoredEvent().getTypeName()));
                    } catch (ClassNotFoundException exception) {
                        exception.printStackTrace();
                        throw new IllegalArgumentException("No se encontró el evento de dominio solicitado");
                    }
                });
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        DocumentEventStored documentEventStored = new DocumentEventStored(
                event.aggregateRootId(),
                new StoredEvent(
                        gson.toJson(event),
                        new Date(),
                        event.getClass().getCanonicalName()
                ));

        return reactiveMongoTemplate.save(documentEventStored)
                .map(eventStored -> {
                    try {
                        return (DomainEvent) gson.fromJson(
                                eventStored.getStoredEvent().getEventBody(),
                                Class.forName(eventStored.getStoredEvent().getTypeName()));
                    } catch (ClassNotFoundException exception) {
                        exception.printStackTrace();
                        throw new IllegalArgumentException("No se encontró el evento de dominio solicitado");
                    }
                });
    }
}
