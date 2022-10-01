package org.backend.business.usecases;


import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.events.EstudianteCreado;
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

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class CrearEstudianteUseCaseTest {
    @Mock
    MongoViewRepository mongoViewRepository;
    MongoEventRepository mongoEventRepository;
    @InjectMocks
    CrearEstudianteUseCase crearEstudianteUseCase;

    Blockchain blockchain;

    @BeforeEach
    void init() {
        crearEstudianteUseCase = new CrearEstudianteUseCase(mongoViewRepository,mongoEventRepository, blockchain);
    }

    @Test
    void crearEstudianteTest(){

      EstudianteCreado estudianteCreado = new EstudianteCreado(
              new Nombre("Maria"), new HashMap<>()
      );
      VistaEstudiante estudiante = new VistaEstudiante("222", "Maria", Float.valueOf(0),Float.valueOf(0));
      estudianteCreado.setAggregateRootId("222");

      Mono<VistaEstudiante> expectedMono = Mono.just(
              new VistaEstudiante("222", "Maria", Float.valueOf(0),Float.valueOf(0))
      );

      Mockito.when(mongoViewRepository.crearEstudiante(estudiante)).thenReturn(expectedMono);

      var useCaseExecuted = mongoViewRepository.crearEstudiante(estudiante);

      StepVerifier.create(useCaseExecuted).expectNextMatches(
              vistaEstudiante -> vistaEstudiante.getNombre().equals("Maria")
      ).verifyComplete();


    }
}