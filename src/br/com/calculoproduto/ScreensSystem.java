package br.com.calculoproduto;

import javafx.scene.Scene;

public class ScreensSystem {

	private Scene listagemProduto;
	private Scene cadastroProduto;
	private Scene seletorFornecedor;
	private Scene cadastroFornecedor;
	private Scene listagemFornecedor;
	private Scene autenticacao;
	
	public Scene getListagemProduto() {
		return listagemProduto;
	}
	public void setListagemProduto(Scene listagemProduto) {
		this.listagemProduto = listagemProduto;
	}
	public Scene getCadastroProduto() {
		return cadastroProduto;
	}
	public void setCadastroProduto(Scene cadastroProduto) {
		this.cadastroProduto = cadastroProduto;
	}
	public Scene getSeletorFornecedor() {
		return seletorFornecedor;
	}
	public void setSeletorFornecedor(Scene seletorFornecedor) {
		this.seletorFornecedor = seletorFornecedor;
	}
	public Scene getCadastroFornecedor() {
		return cadastroFornecedor;
	}
	public void setCadastroFornecedor(Scene cadastroFornecedor) {
		this.cadastroFornecedor = cadastroFornecedor;
	}
	public Scene getListagemFornecedor() {
		return listagemFornecedor;
	}
	public void setListagemFornecedor(Scene listagemFornecedor) {
		this.listagemFornecedor = listagemFornecedor;
	}
	public Scene getAutenticacao() {
		return autenticacao;
	}
	public void setAutenticacao(Scene autenticacao) {
		this.autenticacao = autenticacao;
	}
}
