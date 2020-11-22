package com.irmasantos.apicdc.finalizacompra;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BloqueiaTransacoesIguaisPaypalValidator implements Validator {

	private ValidadorIdsTransacaoPaypalIguais validadorIdsTransacaoPaypalIguais; 

	public BloqueiaTransacoesIguaisPaypalValidator(ValidadorIdsTransacaoPaypalIguais validadorIdsTransacaoPaypalIguais) {
		this.validadorIdsTransacaoPaypalIguais = validadorIdsTransacaoPaypalIguais;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoPagamentoPaypalRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovoPagamentoPaypalRequest novoPagamentoPaypalRequest = (NovoPagamentoPaypalRequest) target;

		if (validadorIdsTransacaoPaypalIguais.existeIdTransacao(novoPagamentoPaypalRequest.getIdTransacao())) {
			errors.rejectValue("idTransacao", null, "Esse pagamento já foi processado");
		}
	}
}
