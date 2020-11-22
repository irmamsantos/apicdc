package com.irmasantos.apicdc.detalhelivro;

import java.util.Optional;

public class IsbnUnicoValidator extends CampoUnicoLivroValidator {

	private LivroRepository livroRepository;

	public IsbnUnicoValidator(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	@Override
	protected String getNomeCampoInvalido() {
		return "isbn";
	}

	@Override
	protected Optional<Livro> buscaLivroPorCampo(NovoLivroRequest novoLivroRequest) {
		return livroRepository.findByIsbn(novoLivroRequest.getIsbn());
	}
}
