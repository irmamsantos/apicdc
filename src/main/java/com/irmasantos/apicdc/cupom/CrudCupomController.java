package com.irmasantos.apicdc.cupom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrudCupomController {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/api/cupom")
	@Transactional
	public String cria(@RequestBody @Valid NovoCupomRequest novoCupom) {
		
		Cupom cupom = novoCupom.novoCupom();
		manager.persist(cupom);
		
		return "criado";
	}

}
