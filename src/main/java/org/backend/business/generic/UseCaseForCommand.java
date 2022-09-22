package org.backend.business.generic;

import co.com.sofka.domain.generic.Command;
import org.backend.business.models.vistasmaterializadas.VistaMaterializada;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public abstract class UseCaseForCommand <R extends Command> implements Function <Mono<R>, Flux<? extends VistaMaterializada>> {

    public abstract Flux<? extends VistaMaterializada> apply(Mono<R> rMono);
}
