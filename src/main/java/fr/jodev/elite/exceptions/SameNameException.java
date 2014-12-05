package fr.jodev.elite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Same name") // 403
public class SameNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String name;
	public SameNameException(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public String getMessage() {
		return "A "+type+" with the same name '"+name+"' already exists.";
	}
}
