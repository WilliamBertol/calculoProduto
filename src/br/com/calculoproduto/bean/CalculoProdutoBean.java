package br.com.calculoproduto.bean;

import java.math.BigDecimal;

public class CalculoProdutoBean {

	private BigDecimal custoMetal;
	
	private BigDecimal bruto;
	
	private BigDecimal valorMetal;
	
	private BigDecimal banho;
	
	private BigDecimal totalCusto;
	
	private BigDecimal custoGrama;
	
	private BigDecimal fat;
	
	private BigDecimal porcentagem;

	public BigDecimal getCustoMetal() {
		return custoMetal;
	}

	public void setCustoMetal(BigDecimal custoMetal) {
		this.custoMetal = custoMetal;
	}

	public BigDecimal getBruto() {
		return bruto;
	}

	public void setBruto(BigDecimal bruto) {
		this.bruto = bruto;
	}

	public BigDecimal getValorMetal() {
		return valorMetal;
	}

	public void setValorMetal(BigDecimal valorMetal) {
		this.valorMetal = valorMetal;
	}

	public BigDecimal getBanho() {
		return banho;
	}

	public void setBanho(BigDecimal banho) {
		this.banho = banho;
	}

	public BigDecimal getTotalCusto() {
		return totalCusto;
	}

	public void setTotalCusto(BigDecimal totalCusto) {
		this.totalCusto = totalCusto;
	}

	public BigDecimal getCustoGrama() {
		return custoGrama;
	}

	public void setCustoGrama(BigDecimal custoGrama) {
		this.custoGrama = custoGrama;
	}

	public BigDecimal getFat() {
		return fat;
	}

	public void setFat(BigDecimal fat) {
		this.fat = fat;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	
}
