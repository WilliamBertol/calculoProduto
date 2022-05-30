package br.com.calculoproduto.views.autenticacao;

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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class AutenticacaoController implements Initializable {

	@FXML
	private PasswordField txtSenha;
	
	private AutenticacaoService service = new AutenticacaoService();

	@FXML Label labelMensagem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void acessar() {
		if (txtSenha != null && ObjectUtil.isStringPreenchida(txtSenha.getText())) {
			boolean senhaCorreta = service.isSenhaCorreta(txtSenha.getText());
			
			if (senhaCorreta) {
				
				labelMensagem.setText("Sucesso! Redirecionando");
				labelMensagem.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(5.0), new Insets(-5.0))));
				labelMensagem.setAlignment(Pos.CENTER);
				
				try {
					redirecionarListagemProduto();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				labelMensagem.setText("Senha incorreta.");
				labelMensagem.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5.0), new Insets(-5.0))));
				labelMensagem.setAlignment(Pos.CENTER);
			}
		} else {
			labelMensagem.setText("Não foi informado a senha.");
			labelMensagem.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5.0), new Insets(-5.0))));
			labelMensagem.setAlignment(Pos.CENTER);
		}
	}
	
	private void redirecionarListagemProduto() throws IOException {
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		ScreensSystem screensSystem = Main.getScreensSystem();
		FXMLLoader fxmlLoaderListagemProduto = new FXMLLoader();
		fxmlLoaderListagemProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemproduto/listagemProdutos.fxml"));
		screensSystem.setListagemProduto(new Scene((AnchorPane) fxmlLoaderListagemProduto.load()));
		Main.changeScreen(screensSystem.getListagemProduto());
	}
 
}






























