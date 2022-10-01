package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.domain.commands.CrearTarea;
import org.backend.domain.events.TareaCreada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class EncontrarTareaPorIDUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    EncontrarTareaPorIDUseCase useCase;

    @BeforeEach
    void init(){useCase = new EncontrarTareaPorIDUseCase(mongoViewRepository);}

    @Test
    void encontrarTareaPorIdTest(){
        TareaCreada tareaCreada = new TareaCreada(
                "Quiz#1","Esta tarea deba contener...", 1, "02/02/2020",Float.valueOf(0),"2"
        );
        tareaCreada.setAggregateRootId("111");

        Mono<VistaTarea> expectedMono = Mono.just(new VistaTarea(
               "3434","111", "222", "444", "Quiz#1", "Esta tarea deba contener...",1, "02/02/2020"
                ,Float.valueOf(0)
        ));

        Mockito.when(mongoViewRepository.encontrarTareaPorID(Mockito.anyString()))
                .thenReturn(expectedMono);

        var useCaseExecute = useCase.encontrarTareaPorID("111");

        StepVerifier.create(useCaseExecute).expectNextMatches(
                vistaTarea ->
                        vistaTarea.getTitulo().equals("Quiz#1")
        ).verifyComplete();
    }

}