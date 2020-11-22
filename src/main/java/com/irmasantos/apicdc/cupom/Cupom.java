package com.irmasantos.apicdc.cupom;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

@Entity
public class Cupom {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private @NotBlank String codigo;
	private @Future @NotNull LocalDateTime dataExpiracao;
	private @Positive @DecimalMax("0.25") BigDecimal desconto;

	@Deprecated
	public Cupom() {}

	public Cupom(@NotBlank String codigo, @Future @NotNull LocalDateTime dataExpiracao, @Positive @DecimalMax("0.25") BigDecimal desconto) {
		Assert.isTrue(desconto.compareTo(new BigDecimal("0.25")) <= 0, "Desconto não pode ser maior do que 0.25");
		this.codigo = codigo;
		this.dataExpiracao = dataExpiracao;
		this.desconto = desconto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isValido() {
		return dataExpiracao.compareTo(LocalDateTime.now()) >= 0;
	}
	
}
