package com.api.Scotiabank.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelError {
	
	private Integer status;
	private String code;
	private String message;
}
