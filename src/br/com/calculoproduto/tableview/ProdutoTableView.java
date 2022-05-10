package br.com.calculoproduto.tableview;

import java.math.RoundingMode;

import br.com.calculoproduto.bean.CalculoProdutoBean;
import br.com.calculoproduto.entity.Produto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProdutoTableView {

	private SimpleLongProperty idProduto;
	
	private SimpleLongProperty codigo;
	
	private SimpleStringProperty descricao;
	
	private SimpleDoubleProperty teor;
	
	private SimpleDoubleProperty kilo;
	
	private SimpleDoubleProperty custoBruto;
	
	private SimpleDoubleProperty pesoMetal;
	
	private SimpleDoubleProperty peso;
	
	private SimpleDoubleProperty acessorio;
	
	private SimpleDoubleProperty solta;
	
	private SimpleDoubleProperty banho;
	
	private SimpleDoubleProperty custoJa;
	
	private SimpleStringProperty nomeFornecedor;
	
	private SimpleDoubleProperty custoMetal;
	
	private SimpleDoubleProperty bruto;

	private SimpleDoubleProperty valorMetal;
	
	private SimpleDoubleProperty totalCusto;
	
	private SimpleDoubleProperty custoGrama;
	
	private SimpleDoubleProperty fat;
	
	private SimpleDoubleProperty porcentagem;
	
	public ProdutoTableView(Produto produto) {
		this.idProduto = new SimpleLongProperty(produto.getIdProduto());
		this.codigo = new SimpleLongProperty(produto.getCodigo());
		this.descricao = new SimpleStringProperty(produto.getDescricao());
		this.teor = new SimpleDoubleProperty(produto.getTeor().doubleValue());
		this.kilo = new SimpleDoubleProperty(produto.getKilo().doubleValue());
		this.custoBruto = new SimpleDoubleProperty(produto.getCustoBruto().doubleValue());
		this.pesoMetal = new SimpleDoubleProperty(produto.getPesoMetal().doubleValue());
		this.peso = new SimpleDoubleProperty(produto.getPeso().doubleValue());
		this.acessorio = new SimpleDoubleProperty(produto.getAcessorio().doubleValue());
		this.solta = new SimpleDoubleProperty(produto.getSolta().doubleValue());
		this.banho = new SimpleDoubleProperty(produto.getBanho().doubleValue());
		this.custoJa = new SimpleDoubleProperty(produto.getCustoJa().doubleValue());
		this.nomeFornecedor = new SimpleStringProperty(produto.getFornecedor().getNome());
	}
	
	public ProdutoTableView(Produto produto, CalculoProdutoBean bean) {
		this.idProduto = new SimpleLongProperty(produto.getIdProduto());
		this.codigo = new SimpleLongProperty(produto.getCodigo());
		this.descricao = new SimpleStringProperty(produto.getDescricao());
		this.teor = new SimpleDoubleProperty(produto.getTeor().doubleValue());
		this.kilo = new SimpleDoubleProperty(produto.getKilo().doubleValue());
		this.custoBruto = new SimpleDoubleProperty(produto.getCustoBruto().doubleValue());
		this.pesoMetal = new SimpleDoubleProperty(produto.getPesoMetal().doubleValue());
		this.peso = new SimpleDoubleProperty(produto.getPeso().doubleValue());
		this.acessorio = new SimpleDoubleProperty(produto.getAcessorio().doubleValue());
		this.solta = new SimpleDoubleProperty(produto.getSolta().doubleValue());
		this.banho = new SimpleDoubleProperty(produto.getBanho().doubleValue());
		this.custoJa = new SimpleDoubleProperty(produto.getCustoJa().doubleValue());
		this.nomeFornecedor = new SimpleStringProperty(produto.getFornecedor().getNome());
		
		if (bean.getCustoMetal() != null) {
			this.custoMetal = new SimpleDoubleProperty(bean.getCustoMetal().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}
		
		if (bean.getBruto() != null) {
			this.bruto = new SimpleDoubleProperty(bean.getBruto().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}
		
		if (bean.getValorMetal() != null) {
			this.valorMetal = new SimpleDoubleProperty(bean.getValorMetal().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}
		
		if (bean.getTotalCusto() != null) {
			this.totalCusto = new SimpleDoubleProperty(bean.getTotalCusto().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}

		if (bean.getCustoGrama() != null) {
			this.custoGrama = new SimpleDoubleProperty(bean.getCustoGrama().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}

		if (bean.getFat() != null) {
			this.fat = new SimpleDoubleProperty(bean.getFat().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}

		if (bean.getPorcentagem() != null) {
			this.porcentagem = new SimpleDoubleProperty(bean.getPorcentagem().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}
	}

	public SimpleLongProperty getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(SimpleLongProperty idProduto) {
		this.idProduto = idProduto;
	}

	public Long getCodigo() {
		return codigo.getValue();
	}

	public void setCodigo(SimpleLongProperty codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao.getValue();
	}

	public void setDescricao(SimpleStringProperty descricao) {
		this.descricao = descricao;
	}

	public SimpleDoubleProperty getTeor() {
		return teor;
	}

	public void setTeor(SimpleDoubleProperty teor) {
		this.teor = teor;
	}

	public SimpleDoubleProperty getKilo() {
		return kilo;
	}

	public void setKilo(SimpleDoubleProperty kilo) {
		this.kilo = kilo;
	}

	public SimpleDoubleProperty getCustoBruto() {
		return custoBruto;
	}

	public void setCustoBruto(SimpleDoubleProperty custoBruto) {
		this.custoBruto = custoBruto;
	}

	public SimpleDoubleProperty getPesoMetal() {
		return pesoMetal;
	}

	public void setPesoMetal(SimpleDoubleProperty pesoMetal) {
		this.pesoMetal = pesoMetal;
	}

	public SimpleDoubleProperty getPeso() {
		return peso;
	}

	public void setPeso(SimpleDoubleProperty peso) {
		this.peso = peso;
	}

	public SimpleDoubleProperty getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(SimpleDoubleProperty acessorio) {
		this.acessorio = acessorio;
	}

	public SimpleDoubleProperty getSolta() {
		return solta;
	}

	public void setSolta(SimpleDoubleProperty solta) {
		this.solta = solta;
	}

	public SimpleDoubleProperty getBanho() {
		return banho;
	}

	public void setBanho(SimpleDoubleProperty banho) {
		this.banho = banho;
	}

	public SimpleDoubleProperty getCustoJa() {
		return custoJa;
	}

	public void setCustoJa(SimpleDoubleProperty custoJa) {
		this.custoJa = custoJa;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor.getValue();
	}

	public void setNomeFornecedor(SimpleStringProperty nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public double getCustoMetal() {
		return custoMetal.get();
	}

	public void setCustoMetal(SimpleDoubleProperty custoMetal) {
		this.custoMetal = custoMetal;
	}

	public double getBruto() {
		return bruto.get();
	}

	public void setBruto(SimpleDoubleProperty bruto) {
		this.bruto = bruto;
	}

	public double getValorMetal() {
		return valorMetal.get();
	}

	public void setValorMetal(SimpleDoubleProperty valorMetal) {
		this.valorMetal = valorMetal;
	}

	public double getTotalCusto() {
		return totalCusto.get();
	}

	public void setTotalCusto(SimpleDoubleProperty totalCusto) {
		this.totalCusto = totalCusto;
	}

	public double getCustoGrama() {
		return custoGrama.get();
	}

	public void setCustoGrama(SimpleDoubleProperty custoGrama) {
		this.custoGrama = custoGrama;
	}

	public double getFat() {
		return fat.get();
	}

	public void setFat(SimpleDoubleProperty fat) {
		this.fat = fat;
	}

	public double getPorcentagem() {
		return porcentagem.get();
	}

	public void setPorcentagem(SimpleDoubleProperty porcentagem) {
		this.porcentagem = porcentagem;
	}
}