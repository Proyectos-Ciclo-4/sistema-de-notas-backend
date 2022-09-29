package org.backend.business.usecases;


import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.events.TareaCreada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CalificarTareaUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    //SocketEstudianteHandler socketEstudianteHandler;

    @InjectMocks
    CalificarTareaUseCase useCase;

    @BeforeEach
    void init(){useCase = new CalificarTareaUseCase(mongoViewRepository /*, socketEstudianteHandler*/);}

    @Test
    void calificarTareaTest(){
        TareaCreada tareaCreada = new TareaCreada(
                "Quiz#1","Agregar DDD al proyecto", 2, "09/02/2023",Float.valueOf(0),"333"
        );

        tareaCreada.setAggregateRootId("333");

        Mono<VistaEstudiante> expectedMono = Mono.just(
                new VistaEstudiante("1212","Juan", Float.valueOf(0),Float.valueOf(9))
        );

        Mockito.when(mongoViewRepository.calificarTarea("1212","333","3434",4,"Sin comentarios"

                )).thenReturn(expectedMono);


        var useCaseExecuted = mongoViewRepository.calificarTarea("1212","333","3434",4,"Sin comentarios");

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaEstudiante -> vistaEstudiante.get_id().equals("1212")
        ).verifyComplete();
    }

}