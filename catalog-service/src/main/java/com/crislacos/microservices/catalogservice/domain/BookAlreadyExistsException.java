package com.crislacos.microservices.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException(String isbn) {
		super("Um Livro com ISBN " + isbn + " Já existe.");
	}

}
