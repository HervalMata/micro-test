package com.crislacos.microservices.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.crislacos.microservices.catalogservice.domain.Book;

import java.time.Instant;

@JsonTest
public class BookJsonTests {

	@Autowired
	private JacksonTester<Book> json;
	
	@Test
	void testSerialize() throws Exception {
		var now = Instant.now();
		var book = new Book(394L, "1231231290", "Title", "Author", 9.90,
				"Polarsophia", now, now, 21);
		var jsonContent = json.write(book);

		assertThat(jsonContent).extractingJsonPathStringValue("@.id").isEqualTo(book.id());
		assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
		assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
		assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
		assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
		assertThat(jsonContent).extractingJsonPathStringValue("@.publisher").isEqualTo(book.publisher());
		assertThat(jsonContent).extractingJsonPathStringValue("@.createdDate").isEqualTo(book.createdDate());
		assertThat(jsonContent).extractingJsonPathStringValue("@.lastModifiedDate").isEqualTo(book.lastModifiedDate());
		assertThat(jsonContent).extractingJsonPathStringValue("@.version").isEqualTo(book.version());
	}
	
	@Test
	void testDeserialize() throws Exception {
		var instant = Instant.parse("2023-09-07T22:50:37:135029Z");
		var content = """
				{
					"isbn": "1234567890",
					"title": "Title",
					"author": "Author",
					"price": 9.90,
					"publisher": "Polarsophia",
					"createdDate": "2023-09-07T22:50:37:135029Z",
					"lastModifiedDate": "2023-09-07T22:50:37:135029Z",
					"version": 21
				{
				""";
		
		assertThat(json.parse(content))
		.usingRecursiveComparison()
		.isEqualTo(new Book(394L, "123456790", "Title", "Author", 9.90,
				"Polarsophia", instant, instant, 21));
	}
}
