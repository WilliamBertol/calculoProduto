package br.com.calculoproduto.views.listagemproduto;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.calculoproduto.AmbienteSystem;
import br.com.calculoproduto.Main;
import br.com.calculoproduto.ScreensSystem;
import br.com.calculoproduto.service.ListagemProdutoService;
import br.com.calculoproduto.tableview.ProdutoTableView;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ListagemProdutoController implements Initializable {

	@FXML 
	private Button btnNovo = new Button();
	@FXML 
	private Button btnEditar = new Button();
	@FXML
	private Button btnFornecedor;
	@FXML 
	TableView<ProdutoTableView> listProduto = new TableView<>();
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
	private TextField filtroProduto;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleLongProperty> clmCodigo;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleStringProperty> clmDescricao;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleStringProperty> clmFornecedor;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleDoubleProperty> clmCustoMetal;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleDoubleProperty> clmValorBruto;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleDoubleProperty> clmValorMetal;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleDoubleProperty> clmTotalCusto;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleDoubleProperty> clmCustoGrama;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleDoubleProperty> clmFat;
	@FXML 
	private TableColumn<ProdutoTableView, SimpleDoubleProperty> clmPorcentagem;
	@FXML 
	private Button btnRemover;
	
	private ListagemProdutoService service = new ListagemProdutoService();
			
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		iniciarListagem();
		pageNumeroPagina.setText(String.valueOf(1));
		pageQuantidadePorPagina.setText(String.valueOf(20));
		buscarProdutoPaginado();
	}
	
	@FXML
	public void novoRegistro(ActionEvent aa) throws IOException {
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		ScreensSystem screensSystem = Main.getScreensSystem();
		
		FXMLLoader fxmlLoaderCadastroProduto = new FXMLLoader();
		fxmlLoaderCadastroProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "cadastroproduto/cadastroProduto.fxml"));
		screensSystem.setCadastroProduto(new Scene((BorderPane) fxmlLoaderCadastroProduto.load()));
		
		Main.changeScreen(screensSystem.getCadastroProduto());
	}
	
	@FXML
	public void buscarProdutosAvancarPaginacao() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			int numeroPagina = Integer.parseInt(pageNumeroPagina.getText()) + 1;
			pageNumeroPagina.setText(String.valueOf(numeroPagina));
			
			buscarProdutoPaginado();
		}
	}
	
	@FXML
	public void buscarProdutosRetrocederPaginacao() {
		 
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			int quantidadePorPagina = Integer.parseInt(pageNumeroPagina.getText());
			
			if (quantidadePorPagina > 1) {
				quantidadePorPagina = quantidadePorPagina - 1;
				pageNumeroPagina.setText(String.valueOf(quantidadePorPagina));
			}
			
			buscarProdutoPaginado();
		}
	}
	
	@FXML
	public void buscarProdutoPaginado() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			String filtroProduto = buscarFiltroProduto();

			List<ProdutoTableView> produtosTableView = service.buscarProdutoPaginado(Integer.parseInt(pageQuantidadePorPagina.getText()), Integer.parseInt(pageNumeroPagina.getText()), filtroProduto);
			listProduto.setItems(FXCollections.observableArrayList(produtosTableView));
		}
	}

	private String buscarFiltroProduto() {
		if (filtroProduto != null && !filtroProduto.getText().isEmpty() && !filtroProduto.getText().isBlank()) {
			return filtroProduto.getText();
		}
		
		return null;
	}

	@FXML
	public void editarProduto() throws IOException {
		if (listProduto != null && listProduto.getSelectionModel() != null && listProduto.getSelectionModel().getSelectedItem() != null) {
			ProdutoTableView produtoTableView = listProduto.getSelectionModel().getSelectedItem();
			ScreensSystem screensSystem = Main.getScreensSystem();
			AmbienteSystem ambienteSystem = new AmbienteSystem();
			
			FXMLLoader fxmlLoaderCadastroProduto = new FXMLLoader();
			fxmlLoaderCadastroProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "cadastroproduto/cadastroProduto.fxml"));
			screensSystem.setCadastroProduto(new Scene((BorderPane) fxmlLoaderCadastroProduto.load()));

			Scene cadastroProduto = Main.getScreensSystem().getCadastroProduto();
			cadastroProduto.setUserData(produtoTableView.getIdProduto());
			
			Main.changeScreen(cadastroProduto, "cadastroProduto", "idProduto");
		}
	}

	@FXML
	public void editarProdutoCliqueDuplo(MouseEvent event) throws IOException {
		if (listProduto != null && listProduto.getSelectionModel() != null 
				&& listProduto.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
			ProdutoTableView produtoTableView = listProduto.getSelectionModel().getSelectedItem();
			ScreensSystem screensSystem = Main.getScreensSystem();
			AmbienteSystem ambienteSystem = new AmbienteSystem();
			
			FXMLLoader fxmlLoaderCadastroProduto = new FXMLLoader();
			fxmlLoaderCadastroProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "cadastroproduto/cadastroProduto.fxml"));
			screensSystem.setCadastroProduto(new Scene((BorderPane) fxmlLoaderCadastroProduto.load()));

			Scene cadastroProduto = Main.getScreensSystem().getCadastroProduto();
			cadastroProduto.setUserData(produtoTableView.getIdProduto());
			
			Main.changeScreen(cadastroProduto, "cadastroProduto", "idProduto");
		}
	}
	
	@FXML 
	public void novoFornecedor(ActionEvent aa) throws IOException {
		ScreensSystem screensSystem = Main.getScreensSystem();
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		
		FXMLLoader fxmlLoaderListagemFornecedor = new FXMLLoader();
		fxmlLoaderListagemFornecedor.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemfornecedor/listagemFornecedor.fxml"));
		screensSystem.setListagemFornecedor(new Scene((AnchorPane) fxmlLoaderListagemFornecedor.load()));
		
		Main.changeScreen(screensSystem.getListagemFornecedor());
	}

	@FXML 
	public void removerProduto() {
		ProdutoTableView selectedItem = listProduto.getSelectionModel().getSelectedItem();
		
		if (selectedItem != null && selectedItem.getIdProduto() != null) {
			String descricao = selectedItem.getDescricao();
			
			service.removerProduto(selectedItem.getIdProduto().longValue());
			buscarProdutoPaginado();
			
			JOptionPane.showMessageDialog(null, "O produto: " + descricao + "Foi removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro: nenhum produto foi selecionado.");
		}
	}

	private void iniciarListagem() {
		clmCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleLongProperty>("codigo"));
		clmDescricao.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleStringProperty>("descricao"));
		clmFornecedor.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleStringProperty>("nomeFornecedor"));
		clmCustoMetal.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleDoubleProperty>("custoMetal"));
		clmValorBruto.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleDoubleProperty>("bruto"));
		clmValorMetal.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleDoubleProperty>("valorMetal"));
		clmTotalCusto.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleDoubleProperty>("totalCusto"));
		clmCustoGrama.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleDoubleProperty>("custoGrama"));
		clmFat.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleDoubleProperty>("fat"));
		clmPorcentagem.setCellValueFactory(new PropertyValueFactory<ProdutoTableView, SimpleDoubleProperty>("porcentagem"));
	} 
}





















