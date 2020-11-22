package com.irmasantos.apicdc.detalhelivro;

import java.util.Optional;

public class TituloLivroUnicoValidator extends CampoUnicoLivroValidator {

	private LivroRepository livroRepository;

	public TituloLivroUnicoValidator(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	@Override
	protected String getNomeCampoInvalido() {
		return "titulo";
	}

	@Override
	protected Optional<Livro> buscaLivroPorCampo(NovoLivroRequest novoLivroRequest) {
		return livroRepository.findByTitulo(novoLivroRequest.getTitulo());
	}
}
