package br.com.calculoproduto.views.alterarsenha;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.calculoproduto.AmbienteSystem;
import br.com.calculoproduto.Main;
import br.com.calculoproduto.ScreensSystem;
import br.com.calculoproduto.service.AutenticacaoService;
import br.com.calculoproduto.util.ObjectUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class AlterarSenhaController implements Initializable {

	@FXML 
	private PasswordField txtConfirmarNovaSenha;
	@FXML 
	private PasswordField txtNovaSenha;
	@FXML 
	private PasswordField txtSenhaAntiga;
	@FXML 
	private Label labelMensagem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML 
	public void salvar() {
		AutenticacaoService service = new AutenticacaoService();
		
		if (txtSenhaAntiga != null && ObjectUtil.isStringPreenchida(txtSenhaAntiga.getText())
				&& txtNovaSenha != null && ObjectUtil.isStringPreenchida(txtNovaSenha.getText())
				&& txtConfirmarNovaSenha != null && ObjectUtil.isStringPreenchida(txtConfirmarNovaSenha.getText())) {

			try {
				service.salvarTrocaSenha(txtSenhaAntiga.getText(), txtNovaSenha.getText(), txtConfirmarNovaSenha.getText());
				
				labelMensagem.setText("Senha alterada com sucesso!");
				labelMensagem.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(5.0), new Insets(-5.0))));
				labelMensagem.setAlignment(Pos.CENTER);
			} catch (Exception e) {
				labelMensagem.setText(e.getMessage());
				labelMensagem.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5.0), new Insets(-5.0))));
				labelMensagem.setAlignment(Pos.CENTER);
			}
		} else {
			labelMensagem.setText("Não foram informado todos os campos.");
			labelMensagem.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5.0), new Insets(-5.0))));
			labelMensagem.setAlignment(Pos.CENTER);
		}
	}

	@FXML 
	public void redirecionarListagemProduto() {
		try {
			AmbienteSystem ambienteSystem = new AmbienteSystem();
			ScreensSystem screensSystem = Main.getScreensSystem();
			FXMLLoader fxmlLoaderListagemProduto = new FXMLLoader();
			fxmlLoaderListagemProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemproduto/listagemProdutos.fxml"));
			screensSystem.setListagemProduto(new Scene((AnchorPane) fxmlLoaderListagemProduto.load()));
			Main.changeScreen(screensSystem.getListagemProduto());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
