package fr.jodev.elite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bad Station ID") // 404
public class StationNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public StationNotFoundException(long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return "The Station with id="+id+" does not exist.";
	}
}
