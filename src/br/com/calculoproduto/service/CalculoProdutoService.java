package br.com.calculoproduto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.calculoproduto.bean.CalculoProdutoBean;
import br.com.calculoproduto.entity.Produto;

public class CalculoProdutoService {

	public CalculoProdutoBean calculoProduto(Produto produto) {
		
		CalculoProdutoBean bean = new CalculoProdutoBean();
		
		if (produto.getPesoMetal() != null && produto.getTeor() != null) {
			
			BigDecimal divide = produto.getPesoMetal().divide(new BigDecimal(1000));
			BigDecimal custoMetal = divide.multiply(produto.getTeor());
			
			bean.setCustoMetal(custoMetal);
		}
		
		if (produto.getKilo() != null && produto.getPeso() != null) {
			
			BigDecimal divide = produto.getKilo().divide(new BigDecimal(1000));
			BigDecimal bruto = divide.multiply(produto.getPeso());
			
			bean.setBruto(bruto);
		}
		
		if (bean.getCustoMetal() != null && produto.getPeso() != null) {
			
			BigDecimal metal = bean.getCustoMetal().multiply(produto.getPeso());
			
			bean.setValorMetal(metal);
		}
		
		if (bean.getBruto() != null && produto.getAcessorio() != null
				&& produto.getSolta() != null && bean.getValorMetal() != null && produto.getBanho() != null) {
			
			BigDecimal addBrutoAcessorio = bean.getBruto().add(produto.getAcessorio());
			BigDecimal addSolta = addBrutoAcessorio.add(produto.getSolta());
			BigDecimal addMetal = addSolta.add(bean.getValorMetal());
			BigDecimal totalCusto = addMetal.add(produto.getBanho());
			
			bean.setTotalCusto(totalCusto);
		}
		
		if (bean.getTotalCusto() != null && produto.getPeso() != null) {
			BigDecimal custoGrama = bean.getTotalCusto().divide(produto.getPeso(), 2, RoundingMode.HALF_UP);
			bean.setCustoGrama(custoGrama);
		}
		
		if (produto.getCustoJa() != null && produto.getPeso() != null) {
			BigDecimal multiply = produto.getCustoJa().multiply(produto.getPeso());
			BigDecimal fat = multiply.divide(new BigDecimal(1.5), 2, RoundingMode.HALF_UP);
			
			bean.setFat(fat);
		}
		
		if (produto.getCustoJa() != null && bean.getCustoGrama() != null) {
			BigDecimal subtract = produto.getCustoJa().subtract(bean.getCustoGrama());
			BigDecimal custo = subtract.divide(produto.getCustoJa(), 5, RoundingMode.HALF_UP);
			BigDecimal porcentagem = custo.multiply(new BigDecimal(100));
			
			bean.setPorcentagem(porcentagem);
		}
 		
		return bean;
	}
}






















