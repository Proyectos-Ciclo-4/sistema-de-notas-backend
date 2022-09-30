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

    public static final String PUBLICAR_TAREA_NUEVA_QUEUE = "estudiante.tarea_nueva";
    public static final String PUBLICAR_TAREA_NUEVA_QUEUE_KEY = "routingKey.tarea_nueva";

    public static final String PUBLICAR_CALIFICACION_QUEUE = "estudiante.calificacion";
    public static final String PUBLICAR_CALIFICACION_QUEUE_KEY = "routingKey.calificacion";

    public static final String PUBLICAR_NUEVO_INSCRITO_QUEUE = "profesor.inscrito_nuevo";
    public static final String PUBLICAR_NUEVO_INSCRITO_KEY = "routingkey.inscrito_nuevo";

    public static final String PUBLICAR_ENTREGA_TAREA_QUEUE = "profesor.entrega_tarea";
    public static final String PUBLICAR_ENTREGA_TAREA_QUEUE_KEY = "routingKey.entregar_tarea";


    // Crear exchange
    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    // Crear queues

    @Bean
    public Queue tareaNuevaQueue() {
        return new Queue(PUBLICAR_TAREA_NUEVA_QUEUE);
    }

    @Bean
    public Queue calificacionQueue() {
        return new Queue(PUBLICAR_CALIFICACION_QUEUE);
    }

    @Bean
    public Queue inscritoNuevoQueue() {
        return new Queue(PUBLICAR_NUEVO_INSCRITO_QUEUE);
    }

    @Bean
    public Queue entregaTareaQueue() {
        return new Queue(PUBLICAR_ENTREGA_TAREA_QUEUE);
    }

    @Bean
    public Binding bindingTareaNuevaQueue() {
        return BindingBuilder
                .bind(tareaNuevaQueue())
                .to(getTopicExchange())
                .with(PUBLICAR_TAREA_NUEVA_QUEUE_KEY);
    }

    @Bean
    public Binding bindingCalificacionQueue() {
        return BindingBuilder
                .bind(calificacionQueue())
                .to(getTopicExchange())
                .with(PUBLICAR_CALIFICACION_QUEUE_KEY);
    }

    @Bean
    public Binding bindingInscritoNuevoQueue() {
        return BindingBuilder
                .bind(inscritoNuevoQueue())
                .to(getTopicExchange())
                .with(PUBLICAR_NUEVO_INSCRITO_KEY);
    }

    @Bean
    public Binding bindingEntregaTareaQueue() {
        return BindingBuilder
                .bind(entregaTareaQueue())
                .to(getTopicExchange())
                .with(PUBLICAR_ENTREGA_TAREA_QUEUE_KEY);
    }
}
