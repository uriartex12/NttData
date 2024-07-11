package com.api.Scotiabank.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ErrorCodedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	private String code;

    public ErrorCodedException(ErrorsHandle errors) {
        this.status =  errors.getError().getStatus();
        this.message =  errors.getError().getMessage();
        this.code =  errors.getError().getCode();
    }   

}
