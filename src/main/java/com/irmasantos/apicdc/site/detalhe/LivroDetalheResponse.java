package com.irmasantos.apicdc.site.detalhe;

import java.math.BigDecimal;

import com.irmasantos.apicdc.detalhelivro.Livro;
import com.irmasantos.apicdc.shared.Markdown;

public class LivroDetalheResponse {

	private String titulo;
	private String subTitulo;
	private BigDecimal preco;
	private String conteudo;
	private String sumarioOriginal;
	private int numeroPaginas;
	private String isbn;
	private AutorLivroDetalheResponse autor;
	private Long id;
	private String sumarioHtml;

	public LivroDetalheResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.subTitulo = livro.getSubTitulo();
		this.preco = livro.getPreco();
		this.conteudo = livro.getConteudo();
		this.sumarioOriginal = livro.getSumario();
		this.sumarioHtml = Markdown.renderHtml(livro.getSumario());
		this.autor = new AutorLivroDetalheResponse(livro.getAutor());
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
	}
	
	public String getSumarioHtml() {
		return sumarioHtml;
	}

	public void setSumarioHtml(String sumarioHtml) {
		this.sumarioHtml = sumarioHtml;
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

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getSumarioOriginal() {
		return sumarioOriginal;
	}

	public void setSumarioOriginal(String sumarioOriginal) {
		this.sumarioOriginal = sumarioOriginal;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public AutorLivroDetalheResponse getAutor() {
		return autor;
	}

	public void setAutor(AutorLivroDetalheResponse autor) {
		this.autor = autor;
	}
}
