package com.crislacos.microservices.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book (
	
	@NotBlank(message = "O ISBN do livro deve ser definido.")
	@Pattern(
		regexp = "^([0-9] {10} | (0-9) {13})$",
		message = "O formato do ISBN deve ser válido."
	)
	String isbn,
	
	@NotBlank(message = "O título do livro deve ser definido.")
	String title,
	
	@NotBlank(message = "O autor do livro deve ser definido.")
	String author,
	
	@NotBlank(message = "O preço do livro deve ser definido.")
	@Positive(message = "O preço do livro deve ser maior que zero")
	Double price
) {}
