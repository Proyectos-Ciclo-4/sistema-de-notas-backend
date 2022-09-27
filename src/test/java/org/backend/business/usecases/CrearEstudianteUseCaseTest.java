package org.backend.business.usecases;


import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.commands.CrearEstudiante;
import org.backend.domain.events.EstudianteCreado;
import org.backend.domain.identifiers.EstudianteID;
import org.backend.domain.valueobjects.Nombre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class CrearEstudianteUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;
    MongoEventRepository mongoEventRepository;
    @InjectMocks
    CrearEstudianteUseCase crearEstudianteUseCase;

    @BeforeEach
    void init() {
        crearEstudianteUseCase = new CrearEstudianteUseCase(mongoViewRepository,mongoEventRepository);
    }

    @Test
    void crearEstudianteTest(){

        CrearEstudiante command = new CrearEstudiante(
                "2222",
                "Jose"
        );



        StepVerifier.create(crearEstudianteUseCase.apply(Mono.just(command)))
                .expectNextMatches(
                        event -> {

                            return "Jose".equals(event.getNombre());

                        }
                ).expectComplete()
                .verify();
    }
}