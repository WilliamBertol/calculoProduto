package br.com.calculoproduto.service;

import java.util.ArrayList;
import java.util.List;

import br.com.calculoproduto.eao.FornecedorEAO;
import br.com.calculoproduto.entity.Fornecedor;
import br.com.calculoproduto.tableview.FornecedorTableView;
import br.com.calculoproduto.validador.ValidadorFornecedor;

public class FornecedorService {
	
	private FornecedorEAO eao = new FornecedorEAO();
	
	public Fornecedor findFornecedor(Long idFornecedor) {
		return eao.find(idFornecedor);
	}
	
	public void salvarFornecedor(Fornecedor fornecedor) {
		ValidadorFornecedor validador = new ValidadorFornecedor();
		validador.validarCnpj(fornecedor);
		validador.validarDuplicidadeCodigo(fornecedor);
		
		FornecedorEAO eao = new FornecedorEAO();
		eao.saveOrUpdate(fornecedor);
	}

	public void removerFornecedor(Long idFornecedor) {
		
		Fornecedor fornecedor = eao.find(idFornecedor);
		eao.delete(fornecedor);
	}

	public List<FornecedorTableView> buscarFornecedorPaginado(int limit, int offSet, String filtroFornecedor) {
		List<Fornecedor> fornecedores = eao.buscarFornecedorPaginado(limit, offSet, filtroFornecedor);
		
		List<FornecedorTableView> fornecedoresTableView = new ArrayList<FornecedorTableView>();
		
		fornecedores.forEach(fornecedor -> {
			fornecedoresTableView.add(new FornecedorTableView(fornecedor));
		});
		
		return fornecedoresTableView;
	}

}
