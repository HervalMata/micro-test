package com.crislacos.microservices.catalogservice;

import com.crislacos.microservices.catalogservice.config.MicroProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private final MicroProperties microProperties;

	public HomeController(MicroProperties microProperties) {
		this.microProperties = microProperties;
	}

	@GetMapping("/")
	public String getGreeting() {
		return microProperties.getGreeting();
	}
}
