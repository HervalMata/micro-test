package com.crislacos.microservices.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String getGreeting() {
		return "Bemvindo para o catálogo de livros";
	}
}
