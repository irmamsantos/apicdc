package com.irmasantos.apicdc.detalhelivro;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//3
@RestController
public class CrudAutoresController {
	
	//1
	@Autowired
	private AutorRepository autorRepository;

	//1
	@PostMapping(value = "/api/autor")
	@Transactional
	public void novo(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {
		
		//1
		Autor novoAutor = novoAutorRequest.novoAutor();
		autorRepository.save(novoAutor);
		
		System.out.println(novoAutor);
	}

}
