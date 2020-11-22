package com.irmasantos.apicdc.finalizacompra;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidadorIdsTransacaoPaypalIguais {

	@Autowired
	private PagamentoPaypalRepository pagamentoPaypalRepository;

	/**
	 * 
	 * @param idTransacao id da transacao paypal
	 * @return
	 */
	public boolean existeIdTransacao(@NotNull String idTransacao) {
		Optional<PagamentoPaypal> possivelPagamento = pagamentoPaypalRepository.findByIdTransacao(idTransacao);

		if (possivelPagamento.isPresent()) {
			return true;
		}
		
		return false;
	}
}
