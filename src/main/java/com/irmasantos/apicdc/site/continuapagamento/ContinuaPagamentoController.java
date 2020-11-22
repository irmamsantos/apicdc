package com.irmasantos.apicdc.site.continuapagamento;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmasantos.apicdc.cupom.CupomRepository;
import com.irmasantos.apicdc.detalhelivro.LivroRepository;
import com.irmasantos.apicdc.site.detalhe.Carrinho;

@RestController
public class ContinuaPagamentoController {
	
	@Autowired
	private CupomRepository cupomRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@InitBinder("dadosComprador")
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(new CupomExistenteENaoExpiradoValidator(cupomRepository));
	}

	@PostMapping(value = "/api/carrinho/finaliza")
	@Transactional
	public String processa(@Valid DadosCompradorRequest dadosComprador, @CookieValue("carrinho") String jsonCarrinho) {
		
		Carrinho carrinho = Carrinho.cria(Optional.of(jsonCarrinho));
		System.out.println("carrinho"+ carrinho);
		
		Set<ItemCompra> items = carrinho.geraItemsCompra(livroRepository);
		System.out.println(items);
		
		Compra novaCompra = dadosComprador.novaCompra(items, cupomRepository);
		
		compraRepository.save(novaCompra);
		
		/**
		 * gerar os items da compra(associar com livro original)
		 * 
		 * uma abstração que representa a compra(dadosComprador + itemsCompra)
		 */
		return novaCompra.toString();
	}

}
