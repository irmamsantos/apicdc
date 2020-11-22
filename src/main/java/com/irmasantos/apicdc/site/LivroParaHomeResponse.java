package com.irmasantos.apicdc.site;

import com.irmasantos.apicdc.detalhelivro.Livro;

public class LivroParaHomeResponse {

	private Long id;
	private String titulo;
	private String nomeAutor;

	public LivroParaHomeResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.nomeAutor = livro.getAutor().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
}
