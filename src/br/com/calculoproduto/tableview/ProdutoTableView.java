package br.com.calculoproduto.tableview;

import java.math.RoundingMode;

import br.com.calculoproduto.bean.CalculoProdutoBean;
import br.com.calculoproduto.entity.Produto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProdutoTableView {

	private SimpleLongProperty idProduto;
	
	private SimpleStringProperty codigo;
	
	private SimpleStringProperty descricao;
	
	private SimpleDoubleProperty teor;
	
	private SimpleDoubleProperty kilo;
	
	private SimpleDoubleProperty custoBanho;
	
	private SimpleDoubleProperty pesoMetal;
	
	private SimpleDoubleProperty peso;
	
	private SimpleDoubleProperty acessorio;
	
	private SimpleDoubleProperty solta;
	
	private SimpleDoubleProperty custoJa;
	
	private SimpleStringProperty nomeFornecedor;
	
	private SimpleDoubleProperty custoMetal;
	
	private SimpleDoubleProperty bruto;

	private SimpleDoubleProperty valorMetal;
	
	private SimpleDoubleProperty banho;
	
	private SimpleDoubleProperty totalCusto;
	
	private SimpleDoubleProperty custoGrama;
	
	private SimpleDoubleProperty fat;
	
	private SimpleDoubleProperty porcentagem;
	
	public ProdutoTableView(Produto produto) {
		this.idProduto = new SimpleLongProperty(produto.getIdProduto());
		this.codigo = new SimpleStringProperty(produto.getCodigo());
		this.descricao = new SimpleStringProperty(produto.getDescricao());
		this.teor = new SimpleDoubleProperty(produto.getTeor().doubleValue());
		this.kilo = new SimpleDoubleProperty(produto.getKilo().doubleValue());
		this.custoBanho = new SimpleDoubleProperty(produto.getCustoBanho().doubleValue());
		this.pesoMetal = new SimpleDoubleProperty(produto.getPrecoMetal().doubleValue());
		this.peso = new SimpleDoubleProperty(produto.getPeso().doubleValue());
		this.acessorio = new SimpleDoubleProperty(produto.getAcessorio().doubleValue());
		this.solta = new SimpleDoubleProperty(produto.getSolta().doubleValue());
		this.custoJa = new SimpleDoubleProperty(produto.getPrecoJa().doubleValue());
		this.nomeFornecedor = new SimpleStringProperty(produto.getFornecedor().getNome());
	}
	
	public ProdutoTableView(Produto produto, CalculoProdutoBean bean) {
		this.idProduto = new SimpleLongProperty(produto.getIdProduto());
		if (produto.getCodigo() != null) {
			this.codigo = new SimpleStringProperty(produto.getCodigo());
		}
		
		if (produto.getDescricao() != null) {
			this.descricao = new SimpleStringProperty(produto.getDescricao());
		}
		
		if (produto.getTeor() != null) {
			this.teor = new SimpleDoubleProperty(produto.getTeor().doubleValue());
		}
		
		if (produto.getKilo() != null) {
			this.kilo = new SimpleDoubleProperty(produto.getKilo().doubleValue());
		}
		
		if (produto.getCustoBanho() != null) {
			this.custoBanho = new SimpleDoubleProperty(produto.getCustoBanho().doubleValue());
		}
		
		if (produto.getPrecoMetal() != null) {
			this.pesoMetal = new SimpleDoubleProperty(produto.getPrecoMetal().doubleValue());
		}
		
		if (produto.getPeso() != null) {
			this.peso = new SimpleDoubleProperty(produto.getPeso().doubleValue());
		}
		
		if (produto.getAcessorio() != null) {
			this.acessorio = new SimpleDoubleProperty(produto.getAcessorio().doubleValue());
		}
		
		if (produto.getSolta() != null) {
			this.solta = new SimpleDoubleProperty(produto.getSolta().doubleValue());
		}
		
		if (produto.getPrecoJa() != null) {
			this.custoJa = new SimpleDoubleProperty(produto.getPrecoJa().doubleValue());
		}
		
		if (produto.getFornecedor() != null) {
			this.nomeFornecedor = new SimpleStringProperty(produto.getFornecedor().getNome());
		}
			
		if (bean.getCustoMetal() != null) {
			this.custoMetal = new SimpleDoubleProperty(bean.getCustoMetal().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}
		
		if (bean.getBruto() != null) {
			this.bruto = new SimpleDoubleProperty(bean.getBruto().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}
		
		if (bean.getValorMetal() != null) {
			this.valorMetal = new SimpleDoubleProperty(bean.getValorMetal().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		}
		
		if (bean.getBanho() != null) {
			this.banho = new SimpleDoubleProperty(bean.getBanho().setScale(2, RoundingMode.HALF_EVEN).doubleValue());
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

	public String getCodigo() {
		return codigo.getValue();
	}

	public void setCodigo(SimpleStringProperty codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		if (descricao != null) {
			return descricao.getValue();
		}
		
		return "";
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

	public SimpleDoubleProperty getCustoBanho() {
		return custoBanho;
	}

	public void setCustoBanho(SimpleDoubleProperty custoBanho) {
		this.custoBanho = custoBanho;
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
		if (custoMetal != null) {
			return custoMetal.get();
		}
		
		return Double.valueOf(0);
	}

	public void setCustoMetal(SimpleDoubleProperty custoMetal) {
		this.custoMetal = custoMetal;
	}

	public double getBruto() {
		if (bruto != null) {
			return bruto.get();
		}
		
		return Double.valueOf(0);
	}

	public void setBruto(SimpleDoubleProperty bruto) {
		this.bruto = bruto;
	}

	public double getValorMetal() {
		if (valorMetal != null) {
			return valorMetal.get();
		}
			
		return Double.valueOf(0);
	}

	public void setValorMetal(SimpleDoubleProperty valorMetal) {
		this.valorMetal = valorMetal;
	}

	public double getBanho() {
		if (banho != null) {
			return banho.get();
		}
			
		return Double.valueOf(0);
	}

	public void setBanho(SimpleDoubleProperty banho) {
		this.banho = banho;
	}

	public double getTotalCusto() {
		if (totalCusto != null) {
			return totalCusto.get();
		}

		return Double.valueOf(0); 
	}

	public void setTotalCusto(SimpleDoubleProperty totalCusto) {
		this.totalCusto = totalCusto;
	}

	public double getCustoGrama() {
		if (custoGrama != null) {
			return custoGrama.get();
		}
		
		return Double.valueOf(0);
	}

	public void setCustoGrama(SimpleDoubleProperty custoGrama) {
		this.custoGrama = custoGrama;
	}

	public double getFat() {
		if (fat != null) {
			return fat.get();
		}
		
		return Double.valueOf(0);
	}

	public void setFat(SimpleDoubleProperty fat) {
		this.fat = fat;
	}

	public double getPorcentagem() {
		if (porcentagem != null) {
			return porcentagem.get();
		}
		
		return Double.valueOf(0);
	}

	public void setPorcentagem(SimpleDoubleProperty porcentagem) {
		this.porcentagem = porcentagem;
	}
}