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
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class EncontrarTodosProfesoresUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;

    EncontrarTodosProfesoresUseCase useCase;

    @BeforeEach
    void init(){useCase = new EncontrarTodosProfesoresUseCase(mongoViewRepository);}

    @Test
    void encontrarTodosLosProfesoresTest(){
        ProfesorCreado profesorCreado = new ProfesorCreado(
                ProfesorID.of("AAA"),new Nombre("Luis")
        );

        profesorCreado.setAggregateRootId("AAA");

        Flux<VistaProfesor> expectedFlux = Flux.just(
                new VistaProfesor("AAA","Luis")
        );

        Mockito.when(mongoViewRepository.encontrarTodosProfesores()).thenReturn(expectedFlux);

        var useCaseExecuted = mongoViewRepository.encontrarTodosProfesores();

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaProfesor -> vistaProfesor.getNombre().equals("Luis")
        ).expectComplete().verify();
    }
}