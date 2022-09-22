package org.backend.application.repository;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.backend.business.gateways.EventRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class MongoEventRepository implements EventRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public MongoEventRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<DomainEvent> findById(String id) {
        return null;
    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        return null;
    }
}
