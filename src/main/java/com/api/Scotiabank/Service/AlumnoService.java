package com.api.Scotiabank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Scotiabank.Entity.Alumno;
import com.api.Scotiabank.Entity.Estado;
import com.api.Scotiabank.ExceptionHandler.ErrorCodedException;
import com.api.Scotiabank.ExceptionHandler.ErrorsHandle;
import com.api.Scotiabank.Repository.AlumnoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoService implements IAlumnoService {
	
	@Autowired
	private AlumnoRepository alumnoRepository;
 
    @Override
    public Mono<Void> saveAlumno(Mono<Alumno> alumnoMono) {
        return alumnoMono.flatMap(alumno ->
            alumnoRepository.existsByAlumnoId(alumno.getId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new ErrorCodedException(ErrorsHandle.E_ALUMNO_ID_DUPLICATED));
                    } else {
                        return alumnoRepository.save(alumno).then();
                    }
                })
        );
    }
    
    @Override
    public Flux<Alumno> getActiveAlumnos() throws Exception {
        return alumnoRepository.findByEstado(Estado.ACTIVO);
    }

}