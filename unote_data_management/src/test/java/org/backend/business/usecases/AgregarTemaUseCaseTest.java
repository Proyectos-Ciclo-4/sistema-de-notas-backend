package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.gateways.EventRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.commands.CrearTarea;
import org.backend.domain.events.TemaCreado;
import org.backend.domain.valueobjects.Orden;
import org.backend.domain.valueobjects.Titulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class AgregarTemaUseCaseTest {

    @Mock
    MongoViewRepository mongoViewRepository;
    MongoEventRepository mongoEventRepository;

    @InjectMocks
    AgregarTemaUseCase useCase;
    CrearTareaUseCase crearTareaUseCase;
    Blockchain blockchain;

    @BeforeEach
    void init(){useCase = new AgregarTemaUseCase(mongoEventRepository,mongoViewRepository,crearTareaUseCase,  blockchain);}

    @Test
    void agregarTemaTest(){
        Set<CrearTarea> tareas = new HashSet<>();
        tareas.add(new CrearTarea("Java","23/04/2023",Float.valueOf(3)));
        tareas.add(new CrearTarea("Quiz#2","29/04/2023",Float.valueOf(3)));

        TemaCreado temaCreado = new TemaCreado(
                new Orden(1), new Titulo("Programación Java"), tareas
        );

        temaCreado.setAggregateRootId("444");
        TemaGeneric tema =  new TemaGeneric("444", "777", 1, "Programación Java", new HashSet<>());

        Mono<TemaGeneric> expectedMono = Mono.just(
                new TemaGeneric("444", "777", 1, "Programación Java", new HashSet<>())
        );

        Mockito.when(mongoViewRepository.agregarTema(tema)).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.agregarTema(tema);

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                temaGeneric -> temaGeneric.getTemaID().equals("444")
        ).verifyComplete();
    }

}