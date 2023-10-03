package com.crislacos.microservices.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BookValidationTests {

	private static Validator validator;
	
	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	void whenAllFieldsCorrectThenValidationSucceds() {
		var book = new Book("1234567890", "Title", "Author", 9.90);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
	}
	
	@Test
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		var book = new Book("a234567890", "Title", "Author", 9.90);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("O ISBN do livro deve ser válido.");
	}
}
