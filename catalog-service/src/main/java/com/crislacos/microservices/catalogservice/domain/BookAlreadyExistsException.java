package com.crislacos.microservices.catalogservice.domain;

import java.io.Serial;

public class BookAlreadyExistsException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException(String isbn) {
		super("Um Livro com ISBN " + isbn + " Já existe.");
	}

}
