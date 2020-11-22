package com.irmasantos.apicdc.finalizacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmasantos.apicdc.site.continuapagamento.Compra;

@RestController
public class FinalizaCompraController {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private CasoUsoTentativaPagamento casoUsoTentativaPagamento;
	
	@Autowired
	private ValidadorIdsTransacaoPaypalIguais validadorIdsTransacaoPaypalIguais;
	
	@InitBinder
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(new BloqueiaTransacoesIguaisPaypalValidator(validadorIdsTransacaoPaypalIguais));
	}

	@PostMapping(value = "api/pagamento/{idCompra}")
	@Transactional
	public void finaliza(@PathVariable("idCompra") Long idCompra, @Valid NovoPagamentoPaypalRequest novaPagamentoPaypalRequest) {
		
		/**
		 * - carregar compra
		 * - precisa associar uma tentativa de pagamento com a compra
		 * - salva pagamento
		 * - se deu certo, manda mail para cdc e comprador(a)
		 * - se deu errado, manda mail para cdc
		 * 
		 * nao pode processar uma compra com id de transacao x mais do que uma vez
		 * nao pode pagar com sucesso a mesma compra mais do que uma vez
		 */
		
		Compra compraExistente = manager.find(Compra.class, idCompra);
		casoUsoTentativaPagamento.executa(compraExistente, novaPagamentoPaypalRequest);
	}
}
