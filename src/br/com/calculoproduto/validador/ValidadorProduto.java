package br.com.calculoproduto.validador;

import br.com.calculoproduto.entity.Produto;

public class ValidadorProduto {

	public void validarCamposObrigatorios(Produto produto) {
		if (produto == null) {
			throw new RuntimeException("N�o foi informado Produto.");
		}
		
		if (produto.getFornecedor() == null) {
			throw new RuntimeException("N�o foi informado Fornecedor.");
		}
	}
}
