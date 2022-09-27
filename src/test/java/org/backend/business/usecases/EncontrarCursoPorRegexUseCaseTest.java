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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
class EncontrarCursoPorRegexUseCaseTest {

    @Mock
    MongoViewRepository mongoViewRepository;

    EncontrarCursoPorRegexUseCase useCase;

    @BeforeEach
    void init(){useCase = new EncontrarCursoPorRegexUseCase(mongoViewRepository);}

    @Test
    void encontrarCursoPorRegexTest(){
        CursoCreado cursoCreado = new CursoCreado(new Titulo("C#"),
                ProfesorID.of("8989"),
                new HashSet<>());

        cursoCreado.setAggregateRootId("09090");

        Flux<VistaCurso> expectedMono = Flux.just(
                new VistaCurso("09090", "C#", "8989", new HashSet<>()));

        Mockito.when(mongoViewRepository.encontrarCursoPorRegex(Mockito.anyString()))
                .thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.encontrarCursoPorRegex("C#");

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaCurso -> vistaCurso.getTitulo().equals("C#")
        ).verifyComplete();

    }

}