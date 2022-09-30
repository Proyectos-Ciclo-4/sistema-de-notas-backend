package org.backend.business.usecases;

import org.backend.application.bus.RabbitMQEventBus;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.domain.events.InscritoEnCurso;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.Avance;
import org.backend.domain.valueobjects.Promedio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class InscribirEstudianteACursoUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;
    MongoEventRepository mongoEventRepository;

    RabbitMQEventBus rabbitMQEventBus;

    InscribirEstudianteACursoUseCase useCase;

    @BeforeEach
    void init(){useCase = new InscribirEstudianteACursoUseCase(mongoEventRepository,mongoViewRepository, rabbitMQEventBus);}

    @Test
    void inscribirEstudianteACursoTest(){
        List<TareaID> tareas = new ArrayList<>();
        tareas.add(TareaID.of("111"));
        tareas.add(TareaID.of("3333"));

        InscritoEnCurso inscritoEnCurso = new InscritoEnCurso(
                CursoID.of("222"), new Promedio(Float.valueOf(0)),
                new Avance(Float.valueOf(0)),tareas
        );

        inscritoEnCurso.setAggregateRootId("6666");
        InscripcionGeneric inscripcionGeneric = new InscripcionGeneric(
                "222", Float.valueOf(0),Float.valueOf(0),"Java", new HashSet<>()
        );

        Mono<VistaEstudiante> expectedMono = Mono.just(
                new VistaEstudiante("8989","Matias",Float.valueOf(0),Float.valueOf(2))
        );

        Mockito.when(mongoViewRepository.agregarInscripcion(inscripcionGeneric,"6666")).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.agregarInscripcion(inscripcionGeneric,"6666");

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaEstudiante -> vistaEstudiante.getNombre().equals("Matias") && vistaEstudiante.getPromedio().equals(Float.valueOf(0))
        ).verifyComplete();
    }
}