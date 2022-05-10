package br.com.calculoproduto.service;

import java.util.ArrayList;
import java.util.List;

import br.com.calculoproduto.bean.CalculoProdutoBean;
import br.com.calculoproduto.eao.ImagemProdutoEAO;
import br.com.calculoproduto.eao.ProdutoEAO;
import br.com.calculoproduto.entity.ImagemProduto;
import br.com.calculoproduto.entity.Produto;
import br.com.calculoproduto.tableview.ProdutoTableView;

public class ListagemProdutoService {

	private ProdutoEAO eao = new ProdutoEAO();
	
	public List<ProdutoTableView> buscarProdutoPaginado(int limit, int offSet, String filter) {
		CalculoProdutoService calculoProdutoService = new CalculoProdutoService();
		List<Produto> produtos = eao.buscarProdutoPaginado(limit, offSet, filter);
		
		List<ProdutoTableView> produtosTableView = new ArrayList<ProdutoTableView>();
		
		produtos.forEach(produto -> {
			CalculoProdutoBean calculoProduto = calculoProdutoService.calculoProduto(produto);
			produtosTableView.add(new ProdutoTableView(produto, calculoProduto));
		});
		
		return produtosTableView;
	}
	
	public void removerProduto(Long idProduto) {
		ProdutoEAO eao = new ProdutoEAO();
		ImagemProdutoEAO imagemEao = new ImagemProdutoEAO();

		removerImagemProduto(imagemEao, idProduto);
		
		Produto produto = eao.find(idProduto);
		eao.delete(produto);
	}
	
	private void removerImagemProduto(ImagemProdutoEAO imagemEao, Long idProduto) {
		List<ImagemProduto> imagensProduto = imagemEao.buscarImagesByProduto(idProduto);
		imagensProduto.forEach(imagemProduto -> {
			imagemEao.delete(imagemProduto);
		});
	}
}
