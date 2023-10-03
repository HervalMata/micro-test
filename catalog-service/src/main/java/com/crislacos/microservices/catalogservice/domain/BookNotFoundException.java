package com.crislacos.microservices.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String isbn) {
		super("O livro com ISBN " + isbn + " não foi encontrado.");
	}

}
