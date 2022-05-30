package br.com.calculoproduto;
	
import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.Session;

import br.com.calculoproduto.entity.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	private static Stage primaryStage;
	private static ScreensSystem screensSystem = new ScreensSystem();
	
	@Override
	public void start(Stage primaryStagee) {
		primaryStage = primaryStagee;
		primaryStage.setTitle("Calculo de Produtos");
		
		initMainStage();
	}
	
	private void initMainStage() {
		try {
			AmbienteSystem ambienteSystem = new AmbienteSystem();
			
			FXMLLoader fxmlLoaderListagemProduto = new FXMLLoader();
			fxmlLoaderListagemProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemproduto/listagemProdutos.fxml"));
			screensSystem.setListagemProduto(new Scene((AnchorPane) fxmlLoaderListagemProduto.load()));
			
			FXMLLoader fxmlLoaderCadastroProduto = new FXMLLoader();
			fxmlLoaderCadastroProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "cadastroproduto/cadastroProduto.fxml"));
			screensSystem.setCadastroProduto(new Scene((BorderPane) fxmlLoaderCadastroProduto.load()));
			
			FXMLLoader fxmlLoaderSeletorFornecedor = new FXMLLoader();
			fxmlLoaderSeletorFornecedor.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "fornecedor/seletorFornecedor.fxml"));
			screensSystem.setSeletorFornecedor(new Scene((AnchorPane) fxmlLoaderSeletorFornecedor.load()));
			
			FXMLLoader fxmlLoaderAutenticacao = new FXMLLoader();
			fxmlLoaderAutenticacao.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "autenticacao/autenticacao.fxml"));
			screensSystem.setAutenticacao(new Scene((AnchorPane) fxmlLoaderAutenticacao.load()));
			
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(ambienteSystem.getAmbiente() + "img/logo.jpg")));
			primaryStage.setScene(screensSystem.getAutenticacao());
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void changeScreen(Scene scene) {
		primaryStage.setScene(scene);
	}
	
	public static void changeScreen(Scene scene, String nomeTela, String nomeParametro) {
		primaryStage.setScene(scene);
		notifyAllListeners(scene.getUserData(), nomeTela, nomeParametro);
	}
	
	public static interface OnChangeScreen {
		void onScreenChanged(Object userData, String nomeTela, String nomeParametro);
	}
	
	private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
	
	public static void addOnChengeScreenListener(OnChangeScreen newListener) {
		listeners.add(newListener);
	}
	
	private static void notifyAllListeners(Object userData, String nomeTela, String nomeParametro) {
		for (OnChangeScreen listener : listeners) {
			listener.onScreenChanged(userData, nomeTela, nomeParametro);
		}
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public static ScreensSystem getScreensSystem() {
		return screensSystem;
	}

	public static void setScreensSystem(ScreensSystem screensSystem) {
		Main.screensSystem = screensSystem;
	}

}
