package br.com.calculoproduto.views.fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.calculoproduto.Main;
import br.com.calculoproduto.eao.FornecedorEAO;
import br.com.calculoproduto.entity.Fornecedor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SeletorFornecedorController implements Initializable  {

	@FXML 
	ListView<Fornecedor> listSeletorFornecedor;
	@FXML
	private Button confirmarFornecedor;
	@FXML
	private Button btnPageEsquerda;
	@FXML
	private Button btnPageDireita;
	@FXML
	private TextField pageQuantidadePorPagina; 
	@FXML
	private TextField pageNumeroPagina;
	@FXML 
	private TextField txtFiltroFornecedor;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pageNumeroPagina.setText(String.valueOf(1));
		pageQuantidadePorPagina.setText(String.valueOf(10));
		buscarFornecedorPaginado();
	}
	
	@FXML
	public void confirmarFornecedor() {
		Fornecedor fornecedor = listSeletorFornecedor.getSelectionModel().getSelectedItem();
		Scene cadastroProduto = Main.getScreensSystem().getCadastroProduto();
		cadastroProduto.setUserData(fornecedor);
		
		Main.changeScreen(cadastroProduto, "cadastroProduto", "Fornecedor");
	}
	
	@FXML
	public void buscarFornecedorAvancarPaginacao() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			int numeroPagina = Integer.parseInt(pageNumeroPagina.getText()) + 1;
			pageNumeroPagina.setText(String.valueOf(numeroPagina));
			
			buscarFornecedorPaginado();
		}
	}
	
	@FXML
	public void buscarFornecedorRetrocederPaginacao() {
		 
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			int quantidadePorPagina = Integer.parseInt(pageNumeroPagina.getText());
			
			if (quantidadePorPagina > 1) {
				quantidadePorPagina = quantidadePorPagina - 1;
				pageNumeroPagina.setText(String.valueOf(quantidadePorPagina));
			}
			
			buscarFornecedorPaginado();
		}
	}
	
	@FXML
	public void buscarFornecedorPaginado() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			
			String filtro = buscarFiltro();
			
			FornecedorEAO eao = new FornecedorEAO();
			List<Fornecedor> fornecedores = eao.buscarFornecedoresPaginado(Integer.parseInt(pageQuantidadePorPagina.getText()), Integer.parseInt(pageNumeroPagina.getText()), filtro);
			
			listSeletorFornecedor.setItems(FXCollections.observableArrayList(fornecedores));
		}
	}
	
	private String buscarFiltro() {
		if (txtFiltroFornecedor != null && !txtFiltroFornecedor.getText().isEmpty() && !txtFiltroFornecedor.getText().isBlank()) {
			return txtFiltroFornecedor.getText();
		}
		
		return null;
	}
	
	@FXML
	public void selecionarFornecedorDuploClique(MouseEvent event) throws IOException {
		if (listSeletorFornecedor != null && listSeletorFornecedor.getSelectionModel() != null 
				&& listSeletorFornecedor.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
			Fornecedor fornecedorSelecionado = listSeletorFornecedor.getSelectionModel().getSelectedItem();
			Scene cadastroProduto = Main.getScreensSystem().getCadastroProduto();
			cadastroProduto.setUserData(fornecedorSelecionado);
			
			Main.changeScreen(cadastroProduto, "cadastroProduto", "Fornecedor");
		}
	}
}
