package com.irmasantos.apicdc.detalhelivro;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class CampoUnicoLivroValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovoLivroRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovoLivroRequest novoLivroRequest = (NovoLivroRequest) target;
		
		Optional<Livro> possivelLivro = buscaLivroPorCampo(novoLivroRequest);
		
		if (possivelLivro.isPresent()) {
			String nomeCampoInvalido = getNomeCampoInvalido();
			errors.reject(nomeCampoInvalido, null, nomeCampoInvalido + ": Já existe um livro registado");
		}
	}

	protected abstract String getNomeCampoInvalido();

	protected abstract Optional<Livro> buscaLivroPorCampo(NovoLivroRequest novoLivroRequest);
}
