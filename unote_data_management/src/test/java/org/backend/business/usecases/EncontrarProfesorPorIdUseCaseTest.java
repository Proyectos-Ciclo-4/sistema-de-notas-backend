package org.backend.business.usecases;


import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.domain.events.ProfesorCreado;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Nombre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class EncontrarProfesorPorIdUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    EncontrarProfesorPorIdUseCase useCase;

    @BeforeEach
    void init(){useCase = new EncontrarProfesorPorIdUseCase(mongoViewRepository);}

    @Test
    void encontrarProfesorPorIdTest(){
        ProfesorCreado profesorCreado = new ProfesorCreado(
            ProfesorID.of("333"), new Nombre("Edisson")
        );

        profesorCreado.setAggregateRootId("333");

        Mono<VistaProfesor> expectedMono = Mono.just(
               new VistaProfesor("333","Edisson")
        );
        Mockito.when(mongoViewRepository.encontrarProfesorPorID(Mockito.anyString())).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.encontrarProfesorPorID("333");

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaProfesor -> vistaProfesor.get_id().equals("333") && vistaProfesor.getNombre().equals("Edisson")
        ).verifyComplete();
    }
}