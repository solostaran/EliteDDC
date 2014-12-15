package fr.jodev.elite.exceptions;

//@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Same name")
public class SameNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SameNameException(String type, String name) {
		super("A "+type+" with the same name '"+name+"' already exists.");
	}
}
