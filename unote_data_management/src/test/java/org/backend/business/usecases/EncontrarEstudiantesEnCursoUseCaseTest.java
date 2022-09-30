package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.events.CursoCreado;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Titulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.Flushable;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
class EncontrarEstudiantesEnCursoUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    @InjectMocks
    EncontrarEstudiantesEnCursoUseCase useCase;

    @BeforeEach
    void init(){useCase = new EncontrarEstudiantesEnCursoUseCase(mongoViewRepository);}

    @Test
    void encontrarEstudiantesEnCursoTest(){
        CursoCreado cursoCreado = new CursoCreado(new Titulo("Testing"), ProfesorID.of("123"), new HashSet<>());

        cursoCreado.setAggregateRootId("456");

        Flux<VistaEstudiante> expectedFlux = Flux.just(
                new VistaEstudiante("0909", "Dahiana",Float.valueOf(0),Float.valueOf(4))
        );

        Mockito.when(mongoViewRepository.listarEstudiantesEnCurso(Mockito.anyString()))
                .thenReturn(expectedFlux);

        var useCaseExpected = mongoViewRepository.listarEstudiantesEnCurso("456");

        StepVerifier.create(useCaseExpected).expectNextMatches(
                vistaEstudiante -> vistaEstudiante.get_id().equals("0909")
        ).verifyComplete();
    }

}