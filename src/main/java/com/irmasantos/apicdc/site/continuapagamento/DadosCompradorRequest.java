package com.irmasantos.apicdc.site.continuapagamento;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.util.StringUtils;

import com.irmasantos.apicdc.cupom.CupomRepository;

public class DadosCompradorRequest {

	@NotBlank
	@Email
	private String email;
	private String nome;
	
	@NotBlank
	@CpfCnpj
	private String documento;
	@NotBlank
	private String endereco;
	private String complemento;
	private String codigoCupom;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCodigoCupom() {
		return codigoCupom;
	}
	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
	}
	public Compra novaCompra(Set<ItemCompra> items, CupomRepository cupomRepository) {
		Compra compra = new Compra(getEmail(), getDocumento(), getEndereco(), items);
		
		if (StringUtils.hasText(getNome())) {
			compra.setNome(getNome());
		} 

		if (StringUtils.hasText(getComplemento())) {
			compra.setComplemento(getComplemento());
		} 
		
		if (StringUtils.hasText(getCodigoCupom())) {
			compra.setCupom(cupomRepository.findByCodigo(getCodigoCupom()).get());
		} 

		return compra;
	}
}
