package com.irmasantos.apicdc.site.detalhe;

import com.irmasantos.apicdc.detalhelivro.Autor;

public class AutorLivroDetalheResponse {

	private String nome;
	private String descricao;

	public AutorLivroDetalheResponse(Autor autor) {
		nome = autor.getNome();
		descricao = "Aqui precisa descrição do autor";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
