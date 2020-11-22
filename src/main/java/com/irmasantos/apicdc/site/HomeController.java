package com.irmasantos.apicdc.site;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irmasantos.apicdc.detalhelivro.LivroRepository;

@RestController
public class HomeController {

	@Autowired
	private LivroRepository livroRepository;

	@GetMapping(value = "/api/home")
	public Iterable<LivroParaHomeResponse> lista(@RequestParam String param) {
		return livroRepository.findAll().stream().map(LivroParaHomeResponse::new).collect(Collectors.toList());
	}
}
