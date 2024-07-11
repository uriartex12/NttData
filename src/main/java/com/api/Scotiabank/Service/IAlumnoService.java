package com.api.Scotiabank.Service;

import com.api.Scotiabank.Entity.Alumno;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAlumnoService {

	public Mono<Void> saveAlumno(Mono<Alumno> alumno) throws Exception;

    public Flux<Alumno> getActiveAlumnos() throws Exception;
}
