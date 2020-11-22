package com.irmasantos.apicdc.site.detalhe;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irmasantos.apicdc.detalhelivro.Livro;
import com.irmasantos.apicdc.detalhelivro.LivroRepository;
import com.irmasantos.apicdc.site.continuapagamento.ItemCompra;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Carrinho {
	
	@Deprecated
	public Carrinho() {}

	private Set<LivroCarrinhoResponse> livros = new LinkedHashSet<>();
	
	public Set<LivroCarrinhoResponse> getLivros() {
		return livros;
	}

	public void setLivros(Set<LivroCarrinhoResponse> livros) {
		this.livros = livros;
	}

	public void adiciona(Livro livro) {
		LivroCarrinhoResponse novoItem = new LivroCarrinhoResponse(livro);
		boolean result = livros.add(novoItem);
		if(!result) {
			LivroCarrinhoResponse itemExistente = livros.stream().filter(novoItem::equals).findFirst().get();
			itemExistente.incrementa();
		}
	}
	
	/**
	 * @param jsonCarrinho possivel json de um carrinho já criado
	 * @return
	 */
	public static Carrinho cria(Optional<String> jsonCarrinho) {
		return jsonCarrinho.map(json -> {
			try {
				return new ObjectMapper().readValue(json, Carrinho.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}).orElse(new Carrinho());
	}

	@Override
	public String toString() {
		return "Carrinho [livros=" + livros + "]";
	}

	public void actualiza(@NotNull Livro livro, @Positive int novaQuantidade) {

		Assert.isTrue(novaQuantidade > 0, "A quantidade de actualização têm de ser maior do que zero");
		
		LivroCarrinhoResponse possivelItemLivroAdicionado = new LivroCarrinhoResponse(livro);
		Optional<LivroCarrinhoResponse> possivelItemLivro = livros.stream().filter(possivelItemLivroAdicionado :: equals).findFirst();
		
		Assert.isTrue(possivelItemLivro.isPresent(), "Não deveria estar actualizar um livro que não existe no carrinho");
		
		LivroCarrinhoResponse livroExistente = possivelItemLivro.get();
		livroExistente.actualizaQuantidade(novaQuantidade);
		
	}
	
	public BigDecimal getTotal() {
		return livros.stream().map(item -> item.getTotal()).reduce(BigDecimal.ZERO, (actual, proximo)->actual.add(proximo));
	}

	public Set<ItemCompra> geraItemsCompra(LivroRepository livroRepository) {
		
		return this.livros.stream().map(itemCarrinho -> {
			return itemCarrinho.novoItemCompra(livroRepository);
		}).collect(Collectors.toSet());
	}
}
