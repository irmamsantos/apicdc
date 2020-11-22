package com.irmasantos.apicdc.finalizacompra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.irmasantos.apicdc.site.continuapagamento.Compra;

@Entity
public class PagamentoPaypal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private @NotBlank String idTransacao;
	private @NotNull PaypalStatus status;
	@OneToOne
	@NotNull
	private Compra compra;
	
	@Deprecated
	public PagamentoPaypal() {}

	public PagamentoPaypal(@NotBlank String idTransacao, @NotNull PaypalStatus status, @NotNull Compra novaCompra) {
		this.idTransacao = idTransacao;
		this.status = status;
		this.compra = novaCompra;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public boolean deuCerto() {
		return PaypalStatus.SUCESSO.equals(status);
	}

	@Override
	public String toString() {
		return "PagamentoPaypal [id=" + id + ", idTransacao=" + idTransacao + ", status=" + status + ", compra="
				+ compra + "]";
	}

}
