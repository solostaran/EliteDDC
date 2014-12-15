package fr.jodev.elite.exceptions;

//@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bad SolarSystem ID")
public class SolarSystemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SolarSystemNotFoundException(long id) {
		super("The SolarSystem with id="+id+" does not exist.");
	}
}
