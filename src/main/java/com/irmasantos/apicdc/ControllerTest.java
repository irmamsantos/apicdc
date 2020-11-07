package com.irmasantos.apicdc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

	@GetMapping("/api/test")
	public String test() {
		return "configurado";
	}
}
