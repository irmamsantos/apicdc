package com.irmasantos.apicdc.site.detalhe;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.irmasantos.apicdc.detalhelivro.Livro;
import com.irmasantos.apicdc.detalhelivro.LivroRepository;
import com.irmasantos.apicdc.site.continuapagamento.ItemCompra;

@JsonIgnoreProperties(ignoreUnknown=true)
public class LivroCarrinhoResponse {

	private String capa;
	private String titulo;
	private BigDecimal preco;
	private Long id;
	private int quantidade = 1;
	
	@Deprecated
	public LivroCarrinhoResponse() {}

	public LivroCarrinhoResponse(Livro livro) {
		id = livro.getId();
		capa = "url fake";//livro.getCapa();
		titulo = livro.getTitulo();
		preco = livro.getPreco();
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivroCarrinhoResponse other = (LivroCarrinhoResponse) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public BigDecimal getTotal() {
		return preco.multiply(new BigDecimal(quantidade));
	}

	@Override
	public String toString() {
		return "LivroCarrinhoResponse [capa=" + capa + ", titulo=" + titulo + ", preco=" + preco + ", id=" + id + "]";
	}

	public void incrementa() {
		quantidade ++;
	}

	public void actualizaQuantidade(@Positive int novaQuantidade) {
		Assert.isTrue(novaQuantidade > 0, "A quantidade de actualização têm de ser maior do que zero");
		
		this.quantidade = novaQuantidade;
	}

	public ItemCompra novoItemCompra(LivroRepository livroRepository) {
		return new ItemCompra(livroRepository.findById(getId()).get(), getQuantidade(), getPreco(), getTotal(), getTitulo());
	}
}
