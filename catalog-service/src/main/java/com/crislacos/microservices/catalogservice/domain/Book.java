package com.crislacos.microservices.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book (

	@Id
	Long id,
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
	Double price,

	String publisher,

	@CreatedDate
	Instant createdDate,

	@LastModifiedDate
	Instant lastModifiedDate,

	@Version
	int version
) {
	public static Book of(
			String isbn, String title, String author, Double price, String publisher
	) {
		return new Book(
				null, isbn, title, author, price, publisher, null, null, 0
		);
	}
}
