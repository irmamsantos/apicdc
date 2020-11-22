package com.irmasantos.apicdc.finalizacompra;

import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irmasantos.apicdc.site.continuapagamento.Compra;

@Service
public class CasoUsoTentativaPagamento {
	
	@Autowired
	private MailManager mailManager;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ValidadorIdsTransacaoPaypalIguais validadorIdsTransacaoPaypalIguais;

	public void executa(Compra compraExistente, @Valid NovoPagamentoPaypalRequest novaPagamentoPaypalRequest) {
		
		if (validadorIdsTransacaoPaypalIguais.existeIdTransacao(novaPagamentoPaypalRequest.getIdTransacao())) {
			throw new IllegalArgumentException("o id "+ novaPagamentoPaypalRequest.getIdTransacao() + " já foi processado pelo sistema");
		}
		
		if (compraExistente.foiPagaComSucesso()) {
			throw new IllegalArgumentException("a compra com id "+ compraExistente.getId() + " já foi processado com sucesso");
		}

		PagamentoPaypal novoPagamentoPaypal = novaPagamentoPaypalRequest.novoPagamento(compraExistente);
		manager.persist(novoPagamentoPaypal);
		
		if (novoPagamentoPaypal.deuCerto()) {
			//System.out.println("Mandar mail para cdc");
			CompletableFuture<String> futuroEnvioParaAdmin = mailManager.novaCompraParaAdminCdcAsync(novoPagamentoPaypal);
			futuroEnvioParaAdmin.thenAccept(corpoMail -> {
				System.out.println(corpoMail);
			});
			
			//System.out.println("Mandar mail para comprador");
			mailManager.notificaCompradorComSuaNovaCompra(novoPagamentoPaypal);
		} else {
			//System.out.println("Mandar mail para cdc");
			mailManager.notificaAdminCdcFalhaPagamento(novoPagamentoPaypal);
		}
	}
}
