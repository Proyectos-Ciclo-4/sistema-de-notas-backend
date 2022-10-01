package org.backend.business.usecases;

import org.backend.application.bus.RabbitMQEventBus;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
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
class CrearTareaUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;
    MongoEventRepository mongoEventRepository;

    CrearTareaUseCase useCase;

    @BeforeEach
    void init(){useCase = new CrearTareaUseCase(mongoEventRepository,mongoViewRepository);}

    @Test
    void crearTareaTest(){
        TareaCreada tareaCreada = new TareaCreada(
                "Quiz#1","Crear un CRUD", 1,"02/02/2023",Float.valueOf(0),"222"
        );
        tareaCreada.setAggregateRootId("333");

        VistaTarea tarea =  new VistaTarea("3434","1212","2323","333","Quiz#1","Crear un CRUD",1,"02/02/2023",Float.valueOf(0));
        Mono<VistaTarea> expectedMono = Mono.just(
                new VistaTarea("3434","1212","2323","333","Quiz#1","Crear un CRUD",1,"02/02/2023",Float.valueOf(0))
        );
        Mockito.when(mongoViewRepository.crearTarea(tarea)).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.crearTarea(tarea);

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaTarea -> vistaTarea.get_id().equals("3434") && vistaTarea.getTitulo().equals("Quiz#1")
        ).verifyComplete();

    }

}