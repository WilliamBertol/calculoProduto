package br.com.calculoproduto.validador;

import br.com.calculoproduto.eao.FornecedorEAO;
import br.com.calculoproduto.entity.Fornecedor;
import br.com.calculoproduto.util.ObjectUtil;

public class ValidadorFornecedor {

	public void validarCnpj(Fornecedor fornecedor) {
		String cnpj = fornecedor.getCnpj();
		
		if (ObjectUtil.isStringPreenchida(cnpj) && cnpj.length() != 14) {
			throw new RuntimeException("Cnpj deve ter 14 números. Números digitados: " + cnpj.length());
		}
	}
	
	public void validarDuplicidadeCodigo(Fornecedor fornecedor) {
		if (fornecedor != null && fornecedor.getCodigo() != null && fornecedor.getIdFornecedor() == null) {
			FornecedorEAO eao = new FornecedorEAO();
			Fornecedor fornecedorBanco = eao.findByCodigo(fornecedor.getCodigo());
			
			if (fornecedorBanco != null) {
				throw new RuntimeException("O código já foi cadastrado!");
			}
		}
	}
}
