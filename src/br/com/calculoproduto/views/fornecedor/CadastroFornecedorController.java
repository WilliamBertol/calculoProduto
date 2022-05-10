package br.com.calculoproduto.views.fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.calculoproduto.AmbienteSystem;
import br.com.calculoproduto.Main;
import br.com.calculoproduto.ScreensSystem;
import br.com.calculoproduto.eao.FornecedorEAO;
import br.com.calculoproduto.entity.Fornecedor;
import br.com.calculoproduto.util.ObjectUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class CadastroFornecedorController implements Initializable {

	@FXML 
	private Button btnListagem;
	@FXML 
	private TextField txtNome;
	@FXML 
	private TextField txtEndereco;
	@FXML 
	private TextField txtCodigo;
	@FXML
	private TextField txtCidade;
	@FXML 
	private TextField txtCnpj;
	@FXML 
	private TextField txtInscricaoEstadual;
	@FXML 
	private Button btnSalvar;
	@FXML 
	private Button btnLimpar;
	@FXML 
	private Label labelMensagem;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML 
	public void redirecionarListagemProduto() throws IOException {
		ScreensSystem screensSystem = Main.getScreensSystem();
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		
		FXMLLoader fxmlLoaderListagemProduto = new FXMLLoader();
		fxmlLoaderListagemProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemproduto/listagemProdutos.fxml"));
		screensSystem.setListagemProduto(new Scene((AnchorPane) fxmlLoaderListagemProduto.load()));
		Main.changeScreen(screensSystem.getListagemProduto());
	}

	@FXML 
	public void SalvarFornecedor() {
		Fornecedor fornecedor = popularFornecedor();
		FornecedorEAO eao = new FornecedorEAO();
		
		eao.saveOrUpdate(fornecedor);
		labelMensagem.setText("Fornecedor gravado com sucesso!");
		labelMensagem.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(5.0), new Insets(-5.0))));
	}

	@FXML 
	public void limparTela() {
		this.txtEndereco.setText("");
		this.txtNome.setText("");
	}
	
	private Fornecedor popularFornecedor() {
		Fornecedor fornecedor = new Fornecedor();
		
		if (txtCodigo != null && ObjectUtil.isStringPreenchida(txtCodigo.getText())) {
			fornecedor.setCodigo(Long.parseLong(txtCodigo.getText()));
		}
		
		if (txtNome != null && ObjectUtil.isStringPreenchida(txtNome.getText())) {
			fornecedor.setNome(txtNome.getText());
		}
		
		if (txtEndereco != null && ObjectUtil.isStringPreenchida(txtEndereco.getText())) {
			fornecedor.setEndereco(txtEndereco.getText());
		}
		
		if (txtCidade != null && ObjectUtil.isStringPreenchida(txtCidade.getText())) {
			fornecedor.setCidade(txtCidade.getText());
		}
		
		if (txtCnpj != null && ObjectUtil.isStringPreenchida(txtCnpj.getText())) {
			fornecedor.setCnpj(txtCnpj.getText());
		}
		
		if (txtInscricaoEstadual!= null && ObjectUtil.isStringPreenchida(txtInscricaoEstadual.getText())) {
			fornecedor.setInscricaoEstadual(txtInscricaoEstadual.getText());
		}
		
		return fornecedor;
	}

}
















