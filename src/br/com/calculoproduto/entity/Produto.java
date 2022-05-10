package br.com.calculoproduto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idProduto")
	private Long idProduto;
	
	private Long codigo;
	
	private String descricao;
	
	private BigDecimal teor;
	
	private BigDecimal kilo;
	
	private BigDecimal custoBruto;
	
	private BigDecimal pesoMetal;
	
	private BigDecimal peso;
	
	private BigDecimal acessorio;
	
	private BigDecimal solta;
	
	private BigDecimal banho;
	
	private BigDecimal custoJa;
	
	@ManyToOne(targetEntity=Fornecedor.class)
	@JoinColumn(name="idFornecedor")
	private Fornecedor fornecedor;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	private Set<ImagemProduto> imagensProduto = new HashSet<>();
	
	public Produto() {}
	
	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getTeor() {
		return teor;
	}

	public void setTeor(BigDecimal teor) {
		this.teor = teor;
	}

	public BigDecimal getKilo() {
		return kilo;
	}

	public void setKilo(BigDecimal kilo) {
		this.kilo = kilo;
	}

	public BigDecimal getCustoBruto() {
		return custoBruto;
	}

	public void setCustoBruto(BigDecimal custoBruto) {
		this.custoBruto = custoBruto;
	}

	public BigDecimal getPesoMetal() {
		return pesoMetal;
	}

	public void setPesoMetal(BigDecimal pesoMetal) {
		this.pesoMetal = pesoMetal;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(BigDecimal acessorio) {
		this.acessorio = acessorio;
	}

	public BigDecimal getSolta() {
		return solta;
	}

	public void setSolta(BigDecimal solta) {
		this.solta = solta;
	}

	public BigDecimal getBanho() {
		return banho;
	}

	public void setBanho(BigDecimal banho) {
		this.banho = banho;
	}

	public BigDecimal getCustoJa() {
		return custoJa;
	}

	public void setCustoJa(BigDecimal custoJa) {
		this.custoJa = custoJa;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Set<ImagemProduto> getImagensProduto() {
		return imagensProduto;
	}

	public void setImagensProduto(Set<ImagemProduto> imagensProduto) {
		this.imagensProduto = imagensProduto;
	}
}
