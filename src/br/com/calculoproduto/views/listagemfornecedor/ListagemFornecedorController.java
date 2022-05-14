package br.com.calculoproduto.views.listagemfornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.calculoproduto.AmbienteSystem;
import br.com.calculoproduto.Main;
import br.com.calculoproduto.ScreensSystem;
import br.com.calculoproduto.service.FornecedorService;
import br.com.calculoproduto.tableview.FornecedorTableView;
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

public class ListagemFornecedorController implements Initializable {

	@FXML 
	Button btnNovo;
	@FXML 
	Button btnEditar;
	@FXML 
	Button btnVoltar;
	@FXML 
	Button btnRemover;
	@FXML 
	TableView<FornecedorTableView> listFornecedor = new TableView<>();
	@FXML 
	TableColumn<FornecedorTableView, SimpleLongProperty> clmCodigo;
	@FXML 
	TableColumn<FornecedorTableView, SimpleStringProperty> clmNome;
	@FXML 
	TableColumn<FornecedorTableView, SimpleStringProperty> clmEndereco;
	@FXML 
	TableColumn<FornecedorTableView, SimpleStringProperty> clmCidade;
	@FXML 
	TableColumn<FornecedorTableView, SimpleStringProperty> clmCnpj;
	@FXML 
	TableColumn<FornecedorTableView, SimpleStringProperty> clmInscricaoEstadual;
	@FXML 
	Button btnPageEsquerda;
	@FXML 
	Button btnPageDireita;
	@FXML 
	TextField pageQuantidadePorPagina;
	@FXML 
	TextField pageNumeroPagina;
	@FXML 
	TextField filtroFornecedor;
	
	private FornecedorService service = new FornecedorService();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iniciarListagem();
		pageNumeroPagina.setText(String.valueOf(1));
		pageQuantidadePorPagina.setText(String.valueOf(20));
		buscarFornecedorPaginado();
	}
	
	@FXML 
	public void novoRegistro(ActionEvent aa) throws IOException {
		ScreensSystem screensSystem = Main.getScreensSystem();
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		
		FXMLLoader fxmlLoaderCadastroFornecedor = new FXMLLoader();
		fxmlLoaderCadastroFornecedor.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "fornecedor/cadastroFornecedor.fxml"));
		screensSystem.setCadastroFornecedor(new Scene((AnchorPane) fxmlLoaderCadastroFornecedor.load()));
		
		Main.changeScreen(screensSystem.getCadastroFornecedor());
	}
	
	@FXML 
	public void editarFornecedor() throws IOException {
		if (listFornecedor != null && listFornecedor.getSelectionModel() != null && listFornecedor.getSelectionModel().getSelectedItem() != null) {
			FornecedorTableView fornecedorTableView = listFornecedor.getSelectionModel().getSelectedItem();
			ScreensSystem screensSystem = Main.getScreensSystem();
			AmbienteSystem ambienteSystem = new AmbienteSystem();
			
			FXMLLoader fxmlLoaderCadastroFornecedor = new FXMLLoader();
			fxmlLoaderCadastroFornecedor.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "fornecedor/cadastroFornecedor.fxml"));
			screensSystem.setCadastroFornecedor(new Scene((AnchorPane) fxmlLoaderCadastroFornecedor.load()));

			Scene cadastroFornecedor = Main.getScreensSystem().getCadastroFornecedor();
			cadastroFornecedor.setUserData(fornecedorTableView.getIdFornecedor());
			
			Main.changeScreen(cadastroFornecedor, "cadastroFornecedor", "idFornecedor");
		}
	}
	
	@FXML 
	public void editarFornecedorCliqueDuplo(MouseEvent event) throws IOException {
		if (listFornecedor != null && listFornecedor.getSelectionModel() != null 
				&& listFornecedor.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
			
			FornecedorTableView fornecedorTableView = listFornecedor.getSelectionModel().getSelectedItem();
			ScreensSystem screensSystem = Main.getScreensSystem();
			AmbienteSystem ambienteSystem = new AmbienteSystem();
			
			FXMLLoader fxmlLoaderCadastroFornecedor = new FXMLLoader();
			fxmlLoaderCadastroFornecedor.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "fornecedor/cadastroFornecedor.fxml"));
			screensSystem.setCadastroFornecedor(new Scene((AnchorPane) fxmlLoaderCadastroFornecedor.load()));

			Scene cadastroFornecedor = Main.getScreensSystem().getCadastroFornecedor();
			cadastroFornecedor.setUserData(fornecedorTableView.getIdFornecedor());
			
			Main.changeScreen(cadastroFornecedor, "cadastroFornecedor", "idFornecedor");
		}
	}
	
	@FXML 
	public void btnVoltar() throws IOException {
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		ScreensSystem screensSystem = Main.getScreensSystem();
		FXMLLoader fxmlLoaderListagemProduto = new FXMLLoader();
		fxmlLoaderListagemProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemproduto/listagemProdutos.fxml"));
		screensSystem.setListagemProduto(new Scene((AnchorPane) fxmlLoaderListagemProduto.load()));
		Main.changeScreen(screensSystem.getListagemProduto());
	}
	
	@FXML 
	public void removerFornecedor() {
		FornecedorTableView selectedItem = listFornecedor.getSelectionModel().getSelectedItem();
		
		if (selectedItem != null && selectedItem.getIdFornecedor() != null) {
			String nome = selectedItem.getNome();
			
			service.removerFornecedor(selectedItem.getIdFornecedor().longValue());
			buscarFornecedorPaginado();
			
			JOptionPane.showMessageDialog(null, "O Fornecedor: " + nome + "Foi removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro: nenhum Fornecedor foi selecionado.");
		}
	}
	
	@FXML public void buscarFornecedorRetrocederPaginacao() {
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
	public void buscarFornecedorAvancarPaginacao() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			int numeroPagina = Integer.parseInt(pageNumeroPagina.getText()) + 1;
			pageNumeroPagina.setText(String.valueOf(numeroPagina));
			
			buscarFornecedorPaginado();
		}
	}
	
	@FXML 
	public void buscarFornecedorPaginado() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			String filtroFornecedor = buscarFiltroProduto();

			List<FornecedorTableView> produtosTableView = service.buscarFornecedorPaginado(Integer.parseInt(pageQuantidadePorPagina.getText()), Integer.parseInt(pageNumeroPagina.getText()), filtroFornecedor);
			listFornecedor.setItems(FXCollections.observableArrayList(produtosTableView));
		}
	}
	
	private String buscarFiltroProduto() {
		if (filtroFornecedor != null && !filtroFornecedor.getText().isEmpty() && !filtroFornecedor.getText().isBlank()) {
			return filtroFornecedor.getText();
		}
		
		return null;
	}

	private void iniciarListagem() {
		clmCodigo.setCellValueFactory(new PropertyValueFactory<FornecedorTableView, SimpleLongProperty>("codigo"));
		clmNome.setCellValueFactory(new PropertyValueFactory<FornecedorTableView, SimpleStringProperty>("nome"));
		clmEndereco.setCellValueFactory(new PropertyValueFactory<FornecedorTableView, SimpleStringProperty>("endereco"));
		clmCidade.setCellValueFactory(new PropertyValueFactory<FornecedorTableView, SimpleStringProperty>("cidade"));
		clmCnpj.setCellValueFactory(new PropertyValueFactory<FornecedorTableView, SimpleStringProperty>("cnpj"));
		clmInscricaoEstadual.setCellValueFactory(new PropertyValueFactory<FornecedorTableView, SimpleStringProperty>("inscricaoEstadual"));
	} 
}
