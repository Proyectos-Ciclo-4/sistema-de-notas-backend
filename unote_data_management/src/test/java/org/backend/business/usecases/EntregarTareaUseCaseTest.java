package org.backend.business.usecases;

import org.backend.application.bus.RabbitMQEventBus;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.entities.Tarea;
import org.backend.domain.events.TareaCreada;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.FechaLimite;
import org.backend.domain.valueobjects.Porcentaje;
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

@ExtendWith(MockitoExtension.class)
class EntregarTareaUseCaseTest {

    @Mock
    MongoViewRepository mongoViewRepository;

    RabbitMQEventBus rabbitMQEventBus;

    @InjectMocks
    EntregarTareaUseCase useCase;
    Blockchain blockchain;

    @BeforeEach
    void init(){useCase = new EntregarTareaUseCase(mongoViewRepository, rabbitMQEventBus, blockchain);}

    @Test
    void entregarTareaTest(){
        TareaCreada tareaCreada = new TareaCreada(
                "Quiz#1","Agregar DDD al proyecto", 2, "09/02/2023",Float.valueOf(0),"333"
        );
        Tarea tarea = new Tarea(TareaID.of("4545"),new Titulo("Quiz#1"), new FechaLimite("09/02/2023"), new Porcentaje(Float.valueOf(0)));

        tareaCreada.setAggregateRootId("333");

        Mono<VistaEstudiante> expectedMono = Mono.just(
                new VistaEstudiante("1212","Juan", Float.valueOf(0),Float.valueOf(9))
        );

        Mockito.when(mongoViewRepository.entregarTarea("1212","333","4545","" ))
                .thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.entregarTarea("1212","333","4545","" );

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaEstudiante -> vistaEstudiante.get_id().equals("1212")
        ).verifyComplete();
    }

}