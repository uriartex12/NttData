package com.api.Scotiabank;

import static org.mockito.Mockito.times;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.api.Scotiabank.Controller.AlumnoController;
import com.api.Scotiabank.Entity.Alumno;
import com.api.Scotiabank.Entity.Estado;
import com.api.Scotiabank.Repository.AlumnoRepository;
import com.api.Scotiabank.Service.AlumnoService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AlumnoController.class)
@Import({ AlumnoService.class, TestDatabaseConfiguration.class }) 
class AlumnoControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private AlumnoRepository alumnoRepository;
	
	@Test
	@DisplayName("GIVEN data correct WHEN REGISTER STUDENT method THEN Success")
	void testRegisterStudentOk() throws Exception {
		// Given
        Alumno alumno = Alumno.builder()
        		.id(1)
        		.nombre("Jhon")
        		.apellido("Uriarte")
        		.estado(Estado.ACTIVO)
        		.edad(25)
        		.build();
        
        Mockito.when(alumnoRepository.existsByAlumnoId(alumno.getId()))
        .thenReturn(Mono.just(false));
        	
        Mockito.when(alumnoRepository.save(alumno)).thenReturn(Mono.just(alumno));
        // When
        webTestClient.post().uri("/apiv2/alumno")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(alumno), Alumno.class)
                .exchange()

                // Then
                .expectStatus().isCreated();
        
        
        Mockito.verify(alumnoRepository, times(1)).save(alumno);
	}
	
	
	@Test
	@DisplayName("GIVEN alumnoId duplicated WHEN REGISTER STUDENT method THEN ERROR")
	void testRegisterStudentExists() throws Exception {
		// Given
        Alumno alumno = Alumno.builder()
        		.id(1)
        		.nombre("Jhon")
        		.apellido("Uriarte")
        		.estado(Estado.ACTIVO)
        		.edad(25)
        		.build();
        
        Mockito.when(alumnoRepository.existsByAlumnoId(alumno.getId()))
        .thenReturn(Mono.just(true));
        
     
        webTestClient.post().uri("/apiv2/alumno")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(alumno), Alumno.class)
                .exchange()

                // Then
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("$.status").isEqualTo(500)
                .jsonPath("$.code").isEqualTo("E_STUDENT_ID_DUPLICATED")
                .jsonPath("$.message").isEqualTo("The student ID is duplicated.");
 
	}


}
