package org.backend.application.repository;

import lombok.extern.slf4j.Slf4j;
import org.backend.application.bus.RabbitMQEventBus;
import org.backend.business.gateways.ViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.business.usecases.EliminarTareaUseCase;
import org.backend.domain.commands.EliminarTarea;
import org.backend.domain.commands.EliminarTema;
import org.backend.domain.identifiers.TareaID;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;


@Slf4j
@Repository
public class MongoViewRepository implements ViewRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final RabbitMQEventBus rabbitMQEventBus;

    public MongoViewRepository(ReactiveMongoTemplate reactiveMongoTemplate, RabbitMQEventBus rabbitMQEventBus) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.rabbitMQEventBus = rabbitMQEventBus;
    }


    /* OPERACIONES CON VISTA MATERIALIZADA 'PROFESOR' */

    @Override
    public Mono<VistaProfesor> crearProfesor(VistaProfesor vistaProfesor) {
        return this.reactiveMongoTemplate
            .save(vistaProfesor)
            .doOnError(
                MongoViewRepository::logError
            ).doOnSuccess(
                e -> logSuccessfulOperation(String.format("Profesor %s creado", vistaProfesor.get_id()))
            );
    }


    @Override
    public Flux<VistaProfesor> encontrarTodosProfesores() {
        return reactiveMongoTemplate
            .findAll(VistaProfesor.class)
            .doOnError(
                MongoViewRepository::logError
            )
            .doOnComplete(
                () -> logSuccessfulOperation("Base de datos regresó todos los profesores."));
    }

    @Override
    public Mono<VistaProfesor> encontrarProfesorPorID(String profesorID) {
        Query query = generateFinderQuery("_id", profesorID);

        return reactiveMongoTemplate
            .findOne(query, VistaProfesor.class)
            .switchIfEmpty(Mono.error(new IllegalAccessException("Profesor no encontrado")))
            .doOnError(MongoViewRepository::logError)
            .doOnSuccess(e -> logSuccessfulOperation("Profesor encontrado con exito"));

    }

    @Override
    public void agregarCursoIDaProfesor(String profesorID, String cursoID) {
        Query encontrarProfesor = generateFinderQuery("_id", profesorID);
        Update agregarCursoIDaProfesor = new Update().addToSet("cursosIDS", cursoID);

        this.reactiveMongoTemplate
            .updateFirst(
                encontrarProfesor,
                agregarCursoIDaProfesor,
                VistaProfesor.class

            ).subscribe();
    }

    /* OPERACIONES CON VISTA MATERIALIZADA 'ESTUDIANTE' */

    @Override
    public Flux<VistaEstudiante> encontrarTodosEstudiantes() {
        return reactiveMongoTemplate
            .findAll(VistaEstudiante.class)
            .doOnError(MongoViewRepository::logError)
            .doOnComplete(() -> logSuccessfulOperation("Base de datos regresó todos los estudiantes."));
    }

    @Override
    public Mono<VistaEstudiante> encontrarEstudiantePorID(String estudianteID) {
        Query query = generateFinderQuery("_id", estudianteID);

        return reactiveMongoTemplate
            .findOne(query, VistaEstudiante.class)
            .switchIfEmpty(Mono.error(new IllegalAccessException("Estudiante no encontrado")))
            .doOnError(MongoViewRepository::logError)
            .doOnSuccess(e -> logSuccessfulOperation("Estudiante encontrado con exito"));
    }

    @Override
    public Flux<VistaEstudiante> listarEstudiantesEnCurso(String cursoID) {
        return this.encontrarCursoPorId(cursoID)
            .flatMapMany(vistaCurso -> reactiveMongoTemplate.find(
                        new Query(Criteria
                            .where("_id")
                            .in(vistaCurso.getInscritos())),
                        VistaEstudiante.class
                    )
                    .doOnNext(vistaEstudiante -> logSuccessfulOperation(
                        String.format("Estudiante %s encontrado", vistaEstudiante.getNombre())
                    ))

            );
    }

    @Override
    public Mono<VistaEstudiante> crearEstudiante(VistaEstudiante vistaEstudiante) {
        return this.reactiveMongoTemplate
            .save(vistaEstudiante)
            .doOnError(
                throwable -> log.error(throwable.getMessage())
            ).doOnSuccess(
                e -> log.info(String.format("Estudiante %s creado", vistaEstudiante.get_id()))
            );
    }

    @Override
    public Mono<VistaEstudiante> agregarInscripcion(InscripcionGeneric inscripcionGeneric,
        String estudianteID) {

        return this.reactiveMongoTemplate
            .findAndModify(
                generateFinderQuery("_id", estudianteID),
                new Update().addToSet("inscripciones", inscripcionGeneric),
                new FindAndModifyOptions().returnNew(true),
                VistaEstudiante.class
            );
    }

    @Override
    public void agregarTareaAInscripcion(String cursoID, EstadoTareaGeneric estadoTareaGeneric) {
        this.encontrarCursoPorId(cursoID)
<<<<<<< HEAD:src/main/java/org/backend/application/repository/MongoViewRepository.java
            .subscribe(vistaCurso ->
                vistaCurso.getInscritos()
                    .forEach(inscritoID -> {
                            this.reactiveMongoTemplate.findAndModify(
                                    new Query(Criteria
                                        .where("_id")
                                        .is(inscritoID)
                                        .andOperator(Criteria
                                            .where("inscripciones.cursoID")
                                            .is(cursoID))),
                                    new Update().addToSet("inscripciones.$.estadosTarea", estadoTareaGeneric),

                                    VistaEstudiante.class)
                                .subscribe(vistaEstudiante ->

                                    {
                                        this.ActualizarCumplimiento(vistaEstudiante);
                                        logSuccessfulOperation(String.format(
                                            "Tarea %s añadida al estudiante %s",
                                            estadoTareaGeneric.getTareaID(),
                                            vistaEstudiante.getNombre()));
                                    }

                                );
                        }
                    ));
=======
                .subscribe(vistaCurso ->
                        vistaCurso.getInscritos()
                                .forEach(inscritoID -> {
                                    this.reactiveMongoTemplate.findAndModify(
                                            new Query(Criteria
                                                    .where("_id")
                                                    .is(inscritoID)
                                                    .andOperator(Criteria
                                                            .where("inscripciones.cursoID")
                                                            .is(cursoID))),
                                                    new Update().addToSet("inscripciones.$.estadosTarea", estadoTareaGeneric),
                                                            VistaEstudiante.class)
                                            .doOnSuccess(vistaEstudiante ->
                                                    rabbitMQEventBus.publicarTareaNueva(
                                                            inscritoID,
                                                            estadoTareaGeneric
                                                    ))
                                            .subscribe(vistaEstudiante ->
                                                    {
                                                        this.ActualizarCumplimiento(vistaEstudiante);

                                                        logSuccessfulOperation(String.format(
                                                                "Tarea %s añadida al estudiante %s",
                                                                estadoTareaGeneric.getTareaID(),
                                                                vistaEstudiante.getNombre()));
                                                    }
                                                    );
                                        }
                                ));
>>>>>>> 3475c7dd4385ce82cf1ab4e9aab7ac4a99474d10:unote_data_management/src/main/java/org/backend/application/repository/MongoViewRepository.java
    }

    @Override
    public Mono<VistaEstudiante> entregarTarea(String estudianteID, String cursoID, String tareaID,
        String URLArchivo) {
        return this.reactiveMongoTemplate.save(this.encontrarEstudiantePorID(estudianteID)
<<<<<<< HEAD:src/main/java/org/backend/application/repository/MongoViewRepository.java
            .doOnSuccess(vistaEstudiante ->
                vistaEstudiante.encontrarInscripcion(cursoID)
                    .encontrarEstadoTarea(tareaID)
                    .actualizarTarea(URLArchivo))
            .doOnSuccess(vistaEstudiante -> vistaEstudiante.setAvance()));

    }

    public void ActualizarCumplimiento(VistaEstudiante vistaEstudiante) {
        var update = new Update();
        Query query = new Query(Criteria.where("_id").is(vistaEstudiante.get_id()));
        vistaEstudiante.setAvance();
        update.set("inscripciones", vistaEstudiante.getInscripciones());
        this.reactiveMongoTemplate.updateFirst(query, update,"vistaEstudiante").subscribe();
=======
                .doOnSuccess(vistaEstudiante ->
                        vistaEstudiante.encontrarInscripcion(cursoID)
                                .encontrarEstadoTarea(tareaID)
                                .actualizarTarea(URLArchivo))
                .doOnSuccess(vistaEstudiante -> vistaEstudiante.setAvance())
        );
>>>>>>> 3475c7dd4385ce82cf1ab4e9aab7ac4a99474d10:unote_data_management/src/main/java/org/backend/application/repository/MongoViewRepository.java

    }

    @Override
    public Mono<VistaEstudiante> calificarTarea(String estudianteID, String cursoID, String tareaID,
        Integer calificacion, String retroalimentacion) {
        return this.reactiveMongoTemplate.save(this.encontrarEstudiantePorID(estudianteID)
            .doOnSuccess(vistaEstudiante ->
                vistaEstudiante.encontrarInscripcion(cursoID)
                    .encontrarEstadoTarea(tareaID)
                    .calificarTarea(calificacion, retroalimentacion)));

    }

    @Override
    public Mono<VistaEstudiante> actualizarPromedio(String cursoID, Float promedio) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> actualizarAvance(String cursoID, Float avance) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> actualizarCalificacion(String cursoID, String tareaID,
        Integer calificacion) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> actualizarTarea(String cursoID, String tareaID,
        EstadoTareaGeneric estadoTareaGeneric) {
        return null;
    }

    /* OPERACIONES CON VISTA MATERIALIZADA 'CURSO' */

    @Override
    public Mono<VistaCurso> encontrarCursoPorId(String cursoID) {
        Query query = generateFinderQuery("_id", cursoID);

        return reactiveMongoTemplate
            .findOne(query, VistaCurso.class)
            .switchIfEmpty(Mono.error(new IllegalAccessException("Curso no encontrado")))
            .doOnError(MongoViewRepository::logError)
            .doOnSuccess(e -> logSuccessfulOperation("Curso encontrado con exito"));
    }

    @Override
    public Flux<VistaCurso> encontrarCursoPorRegex(String regex) {
        Query encontrarRegex = new Query(
            Criteria
                .where("titulo")
                .regex(Pattern.compile(regex, Pattern.CASE_INSENSITIVE))
        );

        return reactiveMongoTemplate
            .find(encontrarRegex, VistaCurso.class);

    }

    @Override
    public Flux<VistaCurso> listarCursos() {
        return reactiveMongoTemplate
            .findAll(VistaCurso.class)
            .doOnError(MongoViewRepository::logError)
            .doOnComplete(() -> logSuccessfulOperation("Base de datos regresó todos los Cursos."));
    }

    @Override
    public Mono<VistaCurso> crearCurso(VistaCurso curso) {
        return reactiveMongoTemplate
            .save(curso)
            .doOnError(MongoViewRepository::logError)
            .doOnNext(vistaCurso ->
                this.agregarCursoIDaProfesor(vistaCurso.getProfesorID(), vistaCurso.get_id()))
            .doOnSuccess(e -> logSuccessfulOperation(String.format("Curso %s creado", curso.get_id())));

    }

    @Override
    public void agregarInscritoACurso(String estudianteID, String cursoID) {
        reactiveMongoTemplate
            .findAndModify(
                generateFinderQuery("_id", cursoID),
                new Update().addToSet("inscritos", estudianteID),
                new FindAndModifyOptions().returnNew(true),
                VistaCurso.class
            ).subscribe();

    }

    @Override
    public Mono<TemaGeneric> agregarTema(TemaGeneric nuevoTema) {
        Query encontrarCursoPadre = generateFinderQuery("_id", nuevoTema.getCursoID());
        Update agregarTemaACurso = new Update();

        return reactiveMongoTemplate
            .findOne(encontrarCursoPadre, VistaCurso.class)
            .doOnNext(vistaCurso -> {
                Set<TemaGeneric> cursoTemas = vistaCurso.getTemas();
                cursoTemas.add(nuevoTema);

                agregarTemaACurso.set("temas", cursoTemas);

                reactiveMongoTemplate
                    .findAndModify(encontrarCursoPadre, agregarTemaACurso, VistaCurso.class)
                    .subscribe();
            })
            .thenReturn(nuevoTema);
    }

    @Override
    public Mono<VistaCurso> agregarTareaID(String tareaID) {
        return null;
    }

    /* OPERACIONES CON VISTA MATERIALIZADA 'TAREA' */

    @Override
    public Mono<VistaTarea> encontrarTareaPorID(String tareaID) {
        Query query = generateFinderQuery("_id", tareaID);

        return reactiveMongoTemplate
            .findOne(query, VistaTarea.class)
            .switchIfEmpty(Mono.error(new IllegalAccessException("Tarea no encontrada")))
            .doOnError(MongoViewRepository::logError)
            .doOnSuccess(e -> logSuccessfulOperation("Tarea encontrada con exito"));
    }

    @Override
    public Flux<VistaTarea> listarTareasPorCurso(String cursoID) {

        Query encontrarTareaPorCursoID = generateFinderQuery("cursoID", cursoID);

        return reactiveMongoTemplate.find(encontrarTareaPorCursoID, VistaTarea.class);
    }


    public void agregarTareaATema(VistaTarea vistaTarea) {
        Query encontrarTemaPadre = generateFinderQuery("temas.temaID", vistaTarea.getTemaID());
        Update agregarTareaATema = new Update().addToSet("temas.$.tareasID", vistaTarea.get_id());
        agregarTareaATema.addToSet("temas.$.tareas", vistaTarea);
        reactiveMongoTemplate
            .findAndModify(
                encontrarTemaPadre,
                agregarTareaATema,
                VistaCurso.class
            ).subscribe();
        ;
    }

    @Override
    public Mono<VistaTarea> crearTarea(VistaTarea vistaTarea) {
        return reactiveMongoTemplate.save(vistaTarea);

    }

    public Mono<TemaGeneric> encontrarTema(String temaID) {
        return reactiveMongoTemplate.
            findOne(new Query(Criteria.where("temas.temaID").is(temaID)), TemaGeneric.class)
            .switchIfEmpty(Mono.error(new IllegalAccessException("Tema no encotrado")))
            .doOnError(MongoViewRepository::logError)
            .doOnSuccess(e -> logSuccessfulOperation("Tema encontrado con exito"));
    }

    @Override
    public void eliminarTareaDeEstudiante(String cursoID, String tareaID, String temaID) {
        this.listarEstudiantesEnCurso(cursoID)
            .flatMap(vistaEstudiante -> {
                vistaEstudiante
                    .encontrarInscripcion(cursoID)
                    .eliminarEstadoTarea(tareaID);
                return this.reactiveMongoTemplate.save(vistaEstudiante);
            })
            .subscribe(vistaEstudiante -> logSuccessfulOperation(
                String.format("Tarea %s eliminada en estudiante %s", tareaID,
                    vistaEstudiante.getNombre())
            ));
    }

    @Override
    public void eliminarTareaDeCurso(String cursoID, String tareaID, String temaID) {
        this.encontrarCursoPorId(cursoID)
            .flatMap(vistaCurso -> {
                vistaCurso
                    .encontrarTema(temaID)
                    .eliminarTareaEnArrays(tareaID);

                return this.reactiveMongoTemplate.save(vistaCurso);
            })
            .subscribe(vistaCurso -> logSuccessfulOperation(
                String.format("Tarea %s eliminada del curso %s", tareaID, vistaCurso.getTitulo())
            ));

    }

    @Override
    public void eliminarVistaTarea(String tareaID) {
        this.reactiveMongoTemplate.findAndRemove(
            generateFinderQuery("_id", tareaID),
            VistaTarea.class
        ).subscribe(vistaTarea -> logSuccessfulOperation(
            String.format("Tarea %s eliminada de su colección", vistaTarea.get_id())
        ));
    }

    @Override
    public void eliminarTareasPorTema(String cursoID, String temaID) {
        this.encontrarCursoPorId(cursoID)
            .subscribe(vistaCurso -> {
                if (vistaCurso.encontrarTema(temaID).hasTareas()) {
                    EliminarTareaUseCase eliminarTareaUseCase = new EliminarTareaUseCase(this);

                        /*
                        vistaCurso.encontrarTema(temaID).getTareasID()
                                .forEach(tareaID -> eliminarTareaUseCase.apply(Mono.just(
                                        new EliminarTarea(cursoID, tareaID, temaID))
                                ).subscribe(eliminarTarea -> logSuccessfulOperation(
                                        String.format("Tarea %s en tema %s eliminada.", tareaID, temaID)
                                )));

                         */

                    vistaCurso.encontrarTema(temaID).getTareasID()
                        .stream().map(tareaID -> new EliminarTarea(cursoID, tareaID, temaID))
                        .forEach(eliminarTarea -> eliminarTareaUseCase.apply(Mono.just(eliminarTarea))
                            .subscribe(eliminarTarea1 -> System.out.println(eliminarTarea1.getTareaID())));
                }
            });

    }

    @Override
    public void eliminarTema(String cursoID, String temaID) {
        this.reactiveMongoTemplate.save(this.encontrarCursoPorId(cursoID)
                .doOnSuccess(vistaCurso -> vistaCurso.eliminarTemaPorID(temaID)))
            .subscribe(vistaCursoActualizado
                -> logSuccessfulOperation(
                String.format("Tema %s en curso %s eliminado.", temaID, cursoID))
            );
    }

    private static Query generateFinderQuery(String objectKey, String targetValue) {
        return new Query(Criteria
            .where(objectKey)
            .is(targetValue)
        );
    }

    public void ActualizarCumplimiento(VistaEstudiante vistaEstudiante) {
        Update update = new Update();
        Query query = new Query(Criteria.where("_id").is(vistaEstudiante.get_id()));
        vistaEstudiante.setAvance();
        update.set("inscripciones", vistaEstudiante.getInscripciones());
        this.reactiveMongoTemplate.updateFirst(query, update,"vistaEstudiante").subscribe();

    }


    private static void logSuccessfulOperation(String successMessage) {
        log.info(successMessage);
    }

    private static void logError(Throwable error) {
        log.error(error.getMessage());
    }

}
