package com.irmasantos.apicdc.finalizacompra;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoPaypalRepository extends CrudRepository<PagamentoPaypal, Long> {

	Optional<PagamentoPaypal> findByIdTransacao(String idTransacao);
	
}
