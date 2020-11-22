package com.irmasantos.apicdc.site.detalhe;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmasantos.apicdc.detalhelivro.Livro;
import com.irmasantos.apicdc.detalhelivro.LivroRepository;
import com.irmasantos.apicdc.shared.Cookies;

@RestController
public class DetalheLivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private Cookies cookies;

	@GetMapping(value = "/api/livro/{id}")
	public LivroDetalheResponse exibeDetalhes(@PathVariable("id") Long id) {
		Livro livro = livroRepository.findById(id).get();
		return new LivroDetalheResponse(livro);
	}

	@PostMapping(value = "/api/carrinho/{idLivro}")
	public String adicionaLivroCarrinho(@PathVariable("idLivro") Long idLivro,
			@CookieValue("carrinho") Optional<String> jsonCarrinho,
			HttpServletResponse response) {
		/**
		 * receber carrinho pelo cookie(json)
		 * se não tiver ainda cookie para o carrinho, então cria novo carrinho
		 * preciso capa, titulo, preco do livro
		 */
		Carrinho carrinho = Carrinho.cria(jsonCarrinho);
		
		System.out.println("carrinho criado");
		
		carrinho.adiciona(livroRepository.findById(idLivro).get());
		
		cookies.writeAsJson("carrinho", carrinho, response);
				
		return carrinho.toString();
	}
}
