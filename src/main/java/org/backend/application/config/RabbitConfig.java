package org.backend.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "core-events";

    // Queues
    public static final String VISTA_ESTUDIANTE_QUEUE = "estudiante.general";
    public static final String VISTA_PROFESOR_QUEUE = "profesor.general";

    // Routing keys
    public static final String VISTA_ESTUDIANTE_QUEUE_KEY = "routingKey.estudiante";
    public static final String VISTA_PROFESOR_QUEUE_KEY = "routingKey.profesor";

    // Crear exchange
    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    // Crear queues

    @Bean
    public Queue vistaEstudianteQueue() {
        return new Queue(VISTA_ESTUDIANTE_QUEUE);
    }

    @Bean
    public Queue vistaProfesorQueue() {
        return new Queue(VISTA_PROFESOR_QUEUE);
    }

    @Bean
    public Binding bindingToVistaEstudianteQueue() {
        return BindingBuilder
                .bind(vistaEstudianteQueue())
                .to(getTopicExchange())
                .with(VISTA_ESTUDIANTE_QUEUE_KEY);
    }

    @Bean
    public Binding bindingToVistaProfesor() {
        return BindingBuilder
                .bind(vistaProfesorQueue())
                .to(getTopicExchange())
                .with(VISTA_PROFESOR_QUEUE_KEY);
    }





}
