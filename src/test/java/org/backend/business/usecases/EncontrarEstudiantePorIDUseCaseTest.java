package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.events.EstudianteCreado;
import org.backend.domain.valueobjects.Nombre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EncontrarEstudiantePorIDUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    EncontrarEstudiantePorIDUseCase useCase;

    @BeforeEach
    void init(){ useCase = new EncontrarEstudiantePorIDUseCase(mongoViewRepository);}

    @Test
    void encontrarEstudiantePorIdTest(){
        EstudianteCreado estudianteCreado = new EstudianteCreado(
                new Nombre("Camila"),
                new HashMap<>()
        );
        estudianteCreado.setAggregateRootId("12121");

        Mono<VistaEstudiante> expectedMono = Mono.just(new VistaEstudiante(
                "12121", "Camila", Float.valueOf(0), Float.valueOf(0)
        ));

        Mockito
                .when(mongoViewRepository
                        .encontrarEstudiantePorID(Mockito.anyString()))
                .thenReturn(expectedMono);
        var useCaseExecute = useCase.encontrarEstudiantePorID("12121");

        StepVerifier.create(useCaseExecute)
                .expectNextMatches(
                        vistaEstudiante ->
                            vistaEstudiante.getNombre().equals("Camila")

                ).verifyComplete();


    }
}