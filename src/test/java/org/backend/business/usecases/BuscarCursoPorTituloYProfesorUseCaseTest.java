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
import reactor.test.StepVerifier;

import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
class BuscarCursoPorTituloYProfesorUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;
    EncontrarCursoPorRegexUseCase encontrarCursoPorRegexUseCase;

    BuscarCursoPorTituloYProfesorUseCase useCase;

    @BeforeEach
    void init(){useCase = new BuscarCursoPorTituloYProfesorUseCase(mongoViewRepository,encontrarCursoPorRegexUseCase);}

    @Test
    void buscarCursoPorTituloYProfesorTest(){
        CursoCreado cursoCreado = new CursoCreado(
                new Titulo("Java"), ProfesorID.of("8989"), new HashSet<>()
        );

        cursoCreado.setAggregateRootId("03030");

        Flux<VistaCurso> expectedMono = Flux.just(
                new VistaCurso("03030","Java","8989")
        );

        Mockito.when(mongoViewRepository.encontrarCursoPorRegex(Mockito.anyString())).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.encontrarCursoPorRegex("03030");

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaCurso -> vistaCurso.get_id().equals("03030")
        ).verifyComplete();
    }
}
