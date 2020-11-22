package com.irmasantos.apicdc.detalhelivro;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.URL;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private @NotBlank String nome;
	private @NotBlank @URL String linkGitHub;
	@PastOrPresent
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Deprecated
	public Autor() {}

	public Autor(@NotBlank String nome, @NotBlank @URL String linkGitHub) {
		this.nome = nome;
		this.linkGitHub = linkGitHub;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Autor [nome=" + nome + ", linkGitHub=" + linkGitHub + ", createdAt=" + createdAt + "]";
	}
}
