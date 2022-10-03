package org.backend.business.usecases;


import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.domain.events.ProfesorCreado;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Nombre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith({MockitoExtension.class})
class CrearProfesorUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;
    MongoEventRepository mongoEventRepository;

    @InjectMocks
    CrearProfesorUseCase useCase;
    Blockchain blockchain;

    @BeforeEach
    void init(){useCase =  new CrearProfesorUseCase(mongoEventRepository,mongoViewRepository, blockchain);}

    @Test
    void crearProfesorTest(){
        ProfesorCreado profesorCreado = new ProfesorCreado(
                ProfesorID.of("333"),new Nombre("Camilo")
        );
        profesorCreado.setAggregateRootId("333");
        VistaProfesor profesor = new VistaProfesor("333","Camilo");

        Mono<VistaProfesor> expectedMono = Mono.just(
               new VistaProfesor("333","Camilo")
        );

        Mockito.when(mongoViewRepository.crearProfesor(profesor)).thenReturn(expectedMono);

        var useCaseExecuted = mongoViewRepository.crearProfesor(profesor);

        StepVerifier.create(useCaseExecuted).expectNextMatches(
                vistaProfesor -> vistaProfesor.getNombre().equals("Camilo") && vistaProfesor.get_id().equals("333")
        ).verifyComplete();

    }

}