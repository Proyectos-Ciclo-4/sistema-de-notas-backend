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
class ListarCursosUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    ListarCursosUseCase useCase;

    @BeforeEach
    void init(){useCase = new ListarCursosUseCase(mongoViewRepository);}

    @Test
    void listarCursosTest(){
        CursoCreado cursoCreado = new CursoCreado(new Titulo("JavaScript"), ProfesorID.of("2323"), new HashSet<>());

    cursoCreado.setAggregateRootId("1212");

        Flux<VistaCurso> expectedMono = Flux.just(
               new VistaCurso("1212", "JavaScript","2323",new HashSet<>())
        );

        Mockito.when(mongoViewRepository.listarCursos()).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.listarCursos();

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaCurso -> vistaCurso.getTitulo().equals("JavaScript")
        ).verifyComplete();

    }


}