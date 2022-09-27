package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.domain.events.CursoCreado;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Titulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EncontrarCursoPorIdUseCaseTest {

    @Mock
    MongoViewRepository mongoViewRepository;

    EncontrarCursoPorIdUseCase encontrarCursoPorIdUseCase;

    @BeforeEach
    void init() {
        encontrarCursoPorIdUseCase = new EncontrarCursoPorIdUseCase(mongoViewRepository);
    }

    @Test
    void encontrarCursoPorIdUseCase() {
        CursoCreado cursoCreado = new CursoCreado(new Titulo("Testing"), ProfesorID.of("123"), new HashSet<>());

        cursoCreado.setAggregateRootId("456");

        Mono<VistaCurso> expectedMono = Mono.just(new VistaCurso("456", "Testing", "123", new HashSet<>()));

        Mockito
                .when(mongoViewRepository
                        .encontrarCursoPorId(Mockito.anyString()))
                .thenReturn(expectedMono);

        var useCaseExecuted = encontrarCursoPorIdUseCase.encontrarCursoPorId("456");

        StepVerifier
                .create(useCaseExecuted)
                .expectNextMatches(vistaCurso -> vistaCurso.getTitulo().equals("Testing"))
                .verifyComplete();

        Mockito.verify(mongoViewRepository).encontrarCursoPorId(Mockito.anyString());
    }

}