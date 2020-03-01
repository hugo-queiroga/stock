package br.com.queiroga.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = -4022828125251467506L;
	
	public BusinessException(String message) {
		super(message);
	}


}
