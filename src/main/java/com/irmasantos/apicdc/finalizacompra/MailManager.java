package com.irmasantos.apicdc.finalizacompra;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailManager {

	@Async
	public CompletableFuture<String> novaCompraParaAdminCdcAsync(PagamentoPaypal novoPagamentoPaypal) {
		
		//simulate delay for test...
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Mandar um novo mail para o admin do cdc");
		System.out.println(novoPagamentoPaypal.getCompra());
		System.out.println(novoPagamentoPaypal);
		return CompletableFuture.completedFuture("corpo mail");
	}

	public void notificaCompradorComSuaNovaCompra(PagamentoPaypal novoPagamentoPaypal) {
		System.out.println("Mandar um novo mail para o comprador(a) do cdc");
		System.out.println(novoPagamentoPaypal.getCompra());
		System.out.println(novoPagamentoPaypal);	
	}

	public void notificaAdminCdcFalhaPagamento(PagamentoPaypal novoPagamentoPaypal) {
		System.out.println("Mandar um novo mail para o admin do cdc dizendo que a compra falhou");
		System.out.println(novoPagamentoPaypal.getCompra());
	}

}
