package com.api.Scotiabank.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.api.Scotiabank.Entity.Alumno;
import com.api.Scotiabank.Service.IAlumnoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/apiv2/alumno")
public class AlumnoController {

    @Autowired
    private IAlumnoService alumnoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createAlumno(@Validated @RequestBody Mono<Alumno>  alumno) throws Exception {
        return alumnoService.saveAlumno(alumno);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Alumno> getActiveAlumnos() throws Exception{
        return alumnoService.getActiveAlumnos();
    }
}
