package fr.jodev.elite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Empty Argument") // 404
public class EmptyArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String argname;
	public EmptyArgumentException(String argname) {
		this.argname = argname;
	}
	
	public String getMessage() {
		return "The argument '"+argname+"' is empty !";
	}

}
