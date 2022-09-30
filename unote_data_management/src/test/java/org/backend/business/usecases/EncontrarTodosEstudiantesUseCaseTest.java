package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.entities.Inscripcion;
import org.backend.domain.events.EstudianteCreado;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.valueobjects.Nombre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class EncontrarTodosEstudiantesUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    private EncontrarTodosEstudiantesUseCase useCase;
    @BeforeEach
    void init() {
        useCase = new EncontrarTodosEstudiantesUseCase(mongoViewRepository);
    }

    @Test
    void encontrarEstudiantesTest(){
        EstudianteCreado estudianteCreado = new EstudianteCreado(
             new Nombre("Julian"),
             new HashMap<CursoID, Inscripcion>()
        );

        estudianteCreado.setAggregateRootId("2222");

        Flux<VistaEstudiante> expectedFlux = Flux.just(
                new VistaEstudiante("2222","Julian", Float.valueOf(0),Float.valueOf(0))
        );

        Mockito.when(mongoViewRepository.encontrarTodosEstudiantes()).thenReturn(expectedFlux);

        var useCaseExecuted = mongoViewRepository.encontrarTodosEstudiantes();

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaEstudiante -> vistaEstudiante.get_id().equals("2222")
        ).verifyComplete();

    }

}