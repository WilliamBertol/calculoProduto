package br.com.calculoproduto.service;

import java.util.List;

import br.com.calculoproduto.eao.ImagemProdutoEAO;
import br.com.calculoproduto.entity.ImagemProduto;

public class ImagemProdutoService {

	private ImagemProdutoEAO eao = new ImagemProdutoEAO();
	
	public List<ImagemProduto> buscarImagemPaginado(Long idProduto, int limit, int offSet) {
		return eao.buscarImagemPaginado(idProduto, limit, offSet);
	}
}
