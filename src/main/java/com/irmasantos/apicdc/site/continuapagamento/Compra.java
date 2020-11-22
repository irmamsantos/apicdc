package com.irmasantos.apicdc.site.continuapagamento;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.irmasantos.apicdc.cupom.Cupom;
import com.irmasantos.apicdc.finalizacompra.PagamentoPaypal;

//entidade do modelo de dados
@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private @NotBlank @Email String email;
	@CpfCnpj
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private String complemento;
	private String nome;
	
	//@OneToMany
	@Size(min=1)
	@ElementCollection
	private Set<ItemCompra> items  = new HashSet<>();
	
	@PastOrPresent
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne
	private Cupom cupom;
	
	@OneToOne(mappedBy="compra")
	private PagamentoPaypal pagamentoPaypal;

	@Deprecated
	public Compra() {}
	
	public Compra(@NotBlank @Email String email, @NotBlank String documento, @NotBlank String endereco, @Size(min=1) Set<ItemCompra> items) {
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.items.addAll(items);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<ItemCompra> getItems() {
		return items;
	}

	public void setItems(Set<ItemCompra> items) {
		this.items = items;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Compra [email=" + email + ", documento=" + documento + ", endereco=" + endereco + ", items=" + items
				+ ", createdAt=" + createdAt + "]";
	}

	public void setCupom(Cupom cupom) {
		Assert.isTrue(cupom.isValido(), "Cupom inválido");
		this.cupom = cupom;
	}
	
	public Cupom getCupom() {
		return cupom;
	}
	
	public PagamentoPaypal getPagamentoPaypal() {
		return pagamentoPaypal;
	}
	
	public void setPagamentoPaypal(PagamentoPaypal pagamentoPaypal) {
		this.pagamentoPaypal = pagamentoPaypal;
	}

	public boolean foiPagaComSucesso() {
		
		if (pagamentoPaypal == null) {
			return false;
		}
		
		//Assert.state(pagamentoPaypal != null, "Não pode executar este método se a compra ainda não foi paga");
		return pagamentoPaypal.deuCerto();
	}
}
