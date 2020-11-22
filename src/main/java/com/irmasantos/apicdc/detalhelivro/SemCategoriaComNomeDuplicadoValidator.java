package com.irmasantos.apicdc.detalhelivro;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SemCategoriaComNomeDuplicadoValidator implements Validator {

	private CategoriaRepository categoriaRepository;

	public SemCategoriaComNomeDuplicadoValidator(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest)target;
		Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(novaCategoriaRequest.getNome());

		if (possivelCategoria.isPresent()) {
			errors.rejectValue("nome", null, "Já existe uma categoria com esse nome");
		}
	}
}
