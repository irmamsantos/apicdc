package com.irmasantos.apicdc.site.continuapagamento;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.irmasantos.apicdc.cupom.Cupom;
import com.irmasantos.apicdc.cupom.CupomRepository;

public class CupomExistenteENaoExpiradoValidator implements Validator {
	
	private CupomRepository cupomRepository;
	
	public CupomExistenteENaoExpiradoValidator(CupomRepository cupomRepository) {
		this.cupomRepository = cupomRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return DadosCompradorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DadosCompradorRequest form = (DadosCompradorRequest) target;
		
		Optional<Cupom> possivelCupom = cupomRepository.findByCodigo(form.getCodigoCupom());
		if (!possivelCupom.isPresent()) {
			errors.reject("codigoCupom", null, "O código não existe");
			return;
		}
		
		Cupom cupom = possivelCupom.get();
		if (!cupom.isValido()) {
			errors.reject("codigoCupom", null, "O cupom expirado");
			return;
		}
	}

}
