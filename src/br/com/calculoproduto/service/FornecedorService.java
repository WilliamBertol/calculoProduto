package br.com.calculoproduto.service;

import java.util.ArrayList;
import java.util.List;

import br.com.calculoproduto.eao.FornecedorEAO;
import br.com.calculoproduto.entity.Fornecedor;
import br.com.calculoproduto.tableview.FornecedorTableView;

public class FornecedorService {
	
	private FornecedorEAO eao = new FornecedorEAO();
	
	public Fornecedor findFornecedor(Long idFornecedor) {
		return eao.find(idFornecedor);
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
