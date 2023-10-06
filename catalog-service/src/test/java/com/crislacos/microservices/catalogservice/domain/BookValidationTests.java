package com.crislacos.microservices.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {

	private static Validator validator;
	
	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		var book = Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
	}
	
	@Test
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		var book = Book.of("a234567890", "Title", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("O ISBN do livro deve ser válido.");
	}

	@Test
	void whenIsbnNotDefinedThenValidationFails() {
		var book = Book.of("", "Title", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(2);
		List<String> constraintViolationMessages = violations.stream()
						.map(ConstraintViolation::getMessage).toList();
		assertThat(constraintViolationMessages).contains("O ISBN do livro deve ser válido.");
	}

	@Test
	void whenTitleIsNotDefinedThenValidationFails() {
		var book = Book.of("a234567890", "", "Author", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("O Título do livro deve ser válido.");
	}

	@Test
	void whenAuthorIsNotDefinedThenValidationFails() {
		var book = Book.of("a234567890", "Title", "", 9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("O Autor do livro deve ser válido.");
	}

	@Test
	void whenPriceIsNotDefinedThenValidationFails() {
		var book = Book.of("a234567890", "Title", "Author", 0.0, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("O Preço do livro deve ser maior que zero.");
	}

	@Test
	void whenPriceDefinedButNegativeThenValidationFails() {
		var book = Book.of("a234567890", "Title", "Author", -9.90, "Polarsophia");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("O Preço do livro deve ser maior que zero.");
	}

	@Test
	void whenPublisherIsNotDefinedThenValidationFails() {
		var book = Book.of("a234567890", "Title", "Author", 9.90, null);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
	}
}
