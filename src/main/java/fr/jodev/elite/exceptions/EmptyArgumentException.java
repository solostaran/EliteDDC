package fr.jodev.elite.exceptions;

//@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Empty Argument") // 404
public class EmptyArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmptyArgumentException(String argname) {
		super("The argument '"+argname+"' is empty !");
	}
}
