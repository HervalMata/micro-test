package com.crislacos.microservices.catalogservice.domain;

import java.io.Serial;

public class BookNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String isbn) {
		super("O livro com ISBN " + isbn + " não foi encontrado.");
	}

}
