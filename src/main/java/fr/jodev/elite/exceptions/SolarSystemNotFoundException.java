package fr.jodev.elite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Bad SolarSystem ID") // 404
public class SolarSystemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public SolarSystemNotFoundException(long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return "The SolarSystem with id="+id+" does not exist.";
	}
}
