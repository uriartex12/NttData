package com.api.Scotiabank.Entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import reactor.util.annotation.NonNull;

@Data
@Table("alumnos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Alumno {
	private Integer id;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido;
    @NonNull
    private Estado estado;
    @Min(18)
    @Max(75)
    private Integer edad;
}
