package br.com.calculoproduto.views.fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.calculoproduto.AmbienteSystem;
import br.com.calculoproduto.Main;
import br.com.calculoproduto.ScreensSystem;
import br.com.calculoproduto.Main.OnChangeScreen;
import br.com.calculoproduto.eao.FornecedorEAO;
import br.com.calculoproduto.entity.Fornecedor;
import br.com.calculoproduto.service.FornecedorService;
import br.com.calculoproduto.util.ObjectUtil;
import javafx.beans.property.SimpleLongProperty;
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

	private Fornecedor fornecedor = new Fornecedor();
	private FornecedorService service = new FornecedorService();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Main.addOnChengeScreenListener(new OnChangeScreen() {
			
			@Override
			public void onScreenChanged(Object userData, String nomeTela, String nomeParametro) {
				popularTelaEditar(userData, nomeTela, nomeParametro);
			}
		});
	}
	
	private void popularTelaEditar(Object userData, String nomeTela, String nomeParametro) {
		if ("cadastroFornecedor".equals(nomeTela) && userData != null && "idFornecedor".equals(nomeParametro)) {
			
			SimpleLongProperty idFornecedor = (SimpleLongProperty) userData;
			this.fornecedor = service.findFornecedor(idFornecedor.getValue());
			
			popularTelaEditarProduto(fornecedor);
		}
	}
	
	private void popularTelaEditarProduto(Fornecedor fornecedor) {
		if (fornecedor.getCodigo() != null) {
			this.txtCodigo.setText(fornecedor.getCodigo().toString());
		}
		
		if (fornecedor.getNome() != null) {
			this.txtNome.setText(fornecedor.getNome().toString());
		}
		
		if (fornecedor.getEndereco() != null) {
			this.txtEndereco.setText(fornecedor.getEndereco().toString());
		}
		
		if (fornecedor.getCidade() != null) {
			this.txtCidade.setText(fornecedor.getCidade().toString());
		}
		
		if (fornecedor.getCnpj() != null) {
			this.txtCnpj.setText(fornecedor.getCnpj().toString());
		}	
		
		if (fornecedor.getInscricaoEstadual() != null) {
			this.txtInscricaoEstadual.setText(fornecedor.getInscricaoEstadual().toString());
		}
	}

	@FXML 
	public void redirecionarListagem() throws IOException {
		ScreensSystem screensSystem = Main.getScreensSystem();
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		
		FXMLLoader fxmlLoaderListagemFornecedor = new FXMLLoader();
		fxmlLoaderListagemFornecedor.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemfornecedor/listagemFornecedor.fxml"));
		screensSystem.setListagemFornecedor(new Scene((AnchorPane) fxmlLoaderListagemFornecedor.load()));
		
		Main.changeScreen(screensSystem.getListagemFornecedor());
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
















