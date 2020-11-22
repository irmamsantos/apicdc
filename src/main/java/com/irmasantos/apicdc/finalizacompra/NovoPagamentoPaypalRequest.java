package com.irmasantos.apicdc.finalizacompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.irmasantos.apicdc.site.continuapagamento.Compra;

public class NovoPagamentoPaypalRequest {

	@NotBlank
	private String idTransacao;
	@NotNull
	private PaypalStatus status;
	
	public String getIdTransacao() {
		return idTransacao;
	}
	
	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}
	
	public PaypalStatus getStatus() {
		return status;
	}
	
	public void setStatus(PaypalStatus status) {
		this.status = status;
	}
	public PagamentoPaypal novoPagamento(Compra compraExistente) {
		return new PagamentoPaypal(idTransacao, status, compraExistente);
	}
}
