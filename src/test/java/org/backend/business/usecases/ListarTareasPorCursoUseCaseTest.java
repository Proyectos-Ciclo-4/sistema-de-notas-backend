package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.entities.Tarea;
import org.backend.domain.events.CursoCreado;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.identifiers.TareaID;
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
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class ListarTareasPorCursoUseCaseTest {

    @Mock
    MongoViewRepository mongoViewRepository;

    ListarTareasPorCursoUseCase useCase;

    @BeforeEach
    void init(){useCase = new ListarTareasPorCursoUseCase(mongoViewRepository);}

    @Test
    void listarTareasPorCursoTest(){
        Set<String> tareas = new HashSet<>();
        tareas.add("2222");
        tareas.add("3333");
        tareas.add("4444");

        Set<TemaGeneric> temas = new HashSet<>();
        temas.add(new TemaGeneric(
                "0000", "7878", 1, "Introducción java", tareas
        ));

        CursoCreado cursoCreado = new CursoCreado(
                new Titulo("Introducción al desarrollo"),
                ProfesorID.of("111"), temas

        );
         cursoCreado.setAggregateName("7878");

        Flux<VistaTarea> expectedMono = Flux.just(
                new VistaTarea("2222", "7878", "0000","Quiz#1","ENtregar a tiempo",
                        1, "02/02/2023", Float.valueOf(0)
                )
        );

        Mockito.when(mongoViewRepository.listarTareasPorCurso(Mockito.anyString()))
                .thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.listarTareasPorCurso("7878");

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaTarea -> vistaTarea.get_id().equals("2222")
        ).verifyComplete();
    }
}