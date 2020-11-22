package com.irmasantos.apicdc.detalhelivro;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

public class NovoAutorRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	@URL
	private String linkGitHub;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLinkGitHub() {
		return linkGitHub;
	}

	public void setLinkGitHub(String linkGitHub) {
		this.linkGitHub = linkGitHub;
	}

	public Autor novoAutor() {
		return new Autor(nome, linkGitHub);
	}
	
}
