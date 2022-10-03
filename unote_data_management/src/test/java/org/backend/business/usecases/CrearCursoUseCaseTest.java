package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
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
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class CrearCursoUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;
    MongoEventRepository mongoEventRepository;

    @InjectMocks
    CrearCursoUseCase useCase;
    AgregarTemaUseCase agregarTemaUseCase;
    Blockchain blockchain;


    @BeforeEach
    void init(){useCase = new CrearCursoUseCase(mongoEventRepository,mongoViewRepository, blockchain, agregarTemaUseCase);}

    @Test
    void crearCursoTest(){
        Set<TemaGeneric> temas = new HashSet<>();
        temas.add(new TemaGeneric("111","4545",1,"Pruebas unitarias", new HashSet<>()));

        CursoCreado cursoCreado = new CursoCreado(
                new Titulo("Scrum#2"), ProfesorID.of("1212"),temas
        );
        cursoCreado.setAggregateRootId("4545");
        VistaCurso curso = new VistaCurso("4545", "Pruebas unitarias", "1212");

        Mono<VistaCurso> expectedMono = Mono.just(
                new VistaCurso("4545", "Pruebas unitarias", "1212")
        );

        Mockito.when(mongoViewRepository.crearCurso(curso)).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.crearCurso(curso);

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaCurso -> vistaCurso.getTitulo().equals("Pruebas unitarias")
        ).verifyComplete();
    }

}