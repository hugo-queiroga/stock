package br.com.queiroga.stock.exception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6598066074503723525L;

	public NotFoundException(String message) {
		super(message);
	}
	
}
