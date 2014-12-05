package fr.jodev.elite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bad GoodsDesignation ID") // 404
public class GoodsDesignationNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public GoodsDesignationNotFoundException(long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return "The Goods' designation with id="+id+" does not exist.";
	}
}
