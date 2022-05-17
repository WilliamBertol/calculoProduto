package br.com.calculoproduto.validador;

import br.com.calculoproduto.eao.ProdutoEAO;
import br.com.calculoproduto.entity.Produto;

public class ValidadorProduto {

	public void validarCamposObrigatorios(Produto produto) {
		if (produto == null) {
			throw new RuntimeException("Não foi informado Produto.");
		}
		
		if (produto.getFornecedor() == null) {
			throw new RuntimeException("Não foi informado Fornecedor.");
		}
	}
	
	public void validarDuplicidadeCodigo(Produto produto) {
		if (produto != null && produto.getCodigo() != null && produto.getIdProduto() == null) {
			ProdutoEAO eao = new ProdutoEAO();
			Produto produtoBanco = eao.findByCodigo(produto.getCodigo());
			
			if (produtoBanco != null) {
				throw new RuntimeException("O código já foi cadastrado!");
			}
		}
	}
}
