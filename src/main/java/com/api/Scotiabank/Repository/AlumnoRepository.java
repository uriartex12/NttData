package com.api.Scotiabank.Repository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.api.Scotiabank.Entity.Alumno;
import com.api.Scotiabank.Entity.Estado;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, Integer>{
	
    Flux<Alumno> findByEstado(Estado estado);
    
    @Query("SELECT EXISTS(SELECT 1 FROM alumnos WHERE id = :alumnoId)")
    Mono<Boolean> existsByAlumnoId(Integer alumnoId);
}
