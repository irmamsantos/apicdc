package com.irmasantos.apicdc.site.carrinho;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irmasantos.apicdc.detalhelivro.Livro;
import com.irmasantos.apicdc.detalhelivro.LivroRepository;
import com.irmasantos.apicdc.shared.Cookies;
import com.irmasantos.apicdc.site.detalhe.Carrinho;

@RestController
public class CarrinhoController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private Cookies cookies;

	@PutMapping(value = "api/carrinho/{idLivro}")
	public void actualiza(@PathVariable("idLivro") Long idLivro,
			@RequestParam int novaQuantidade,
			@CookieValue("carrinho") String jsonCarrinho,
			HttpServletResponse response) {

		Carrinho carrinho = Carrinho.cria(Optional.of(jsonCarrinho));
		
		Livro livro = livroRepository.findById(idLivro).get();
		
		carrinho.actualiza(livro, novaQuantidade);
		
		cookies.writeAsJson("carrinho", carrinho, response);
	}
}
