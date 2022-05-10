package br.com.calculoproduto.views.cadastroproduto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.calculoproduto.AmbienteSystem;
import br.com.calculoproduto.Main;
import br.com.calculoproduto.Main.OnChangeScreen;
import br.com.calculoproduto.ScreensSystem;
import br.com.calculoproduto.bean.CalculoProdutoBean;
import br.com.calculoproduto.entity.Fornecedor;
import br.com.calculoproduto.entity.ImagemProduto;
import br.com.calculoproduto.entity.Produto;
import br.com.calculoproduto.service.CalculoProdutoService;
import br.com.calculoproduto.service.ImagemProdutoService;
import br.com.calculoproduto.service.ProdutoService;
import br.com.calculoproduto.util.ObjectUtil;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CadastroProdutoController implements Initializable {

	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnListagem;
	@FXML
	private Button btnSelecionarFornecedor;
	@FXML
	private Button btnAdicionarImagem;
	@FXML 
	private Button btnLimpar;
	@FXML
	private TextField codigo;
	@FXML
	private TextField descricao;
	@FXML
	private TextField teor;
	@FXML
	private TextField kilo;
	@FXML
	private TextField custoBruto;
	@FXML
	private TextField pesoMetal;
	@FXML
	private TextField peso;
	@FXML
	private TextField acessorios;
	@FXML
	private TextField solta;
	@FXML
	private TextField banho;
	@FXML
	private TextField custoJA;
	@FXML
	private TextField txtCustoMetal;
	@FXML
	private TextField txtBruto;
	@FXML
	private TextField txtMetal;
	@FXML 
	private TextField txtTotalCusto;
	@FXML 
	private TextField txtCustoGrama;
	@FXML 
	private TextField txtFat;
	@FXML 
	private TextField txtPorcentagem;
	@FXML
	private Label labelFornecedor;
	@FXML
	private Label labelMensagem;
	@FXML
	private Button btnPageEsquerda;
	@FXML
	private Button btnPageDireita;
	@FXML
	private TextField pageQuantidadePorPagina; 
	@FXML
	private TextField pageNumeroPagina;
	@FXML 
	private ImageView img1;
	@FXML 
	private ImageView img2;
	@FXML 
	private ImageView img3;
	@FXML 
	private ImageView img4;
	@FXML 
	private ImageView img5;
	@FXML 
	private ImageView img6;
	
	private Fornecedor fornecedor;
	private List<File> imagens = new ArrayList<File>();
	private Produto produto = new Produto();
	private ProdutoService service = new ProdutoService();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Main.addOnChengeScreenListener(new OnChangeScreen() {
			
			@Override
			public void onScreenChanged(Object userData, String nomeTela, String nomeParametro) {
				preencherFornecedor(userData, nomeTela, nomeParametro);
				popularTelaEditar(userData, nomeTela, nomeParametro);
			}
		});
	}
	
	@FXML 
	public void salvarProduto(ActionEvent aa) {
		Produto produto = popularProduto();
		
		if (produto == null) {
			labelMensagem.setText("Ocorreu um erro ao gravar o produto!");
			labelMensagem.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5.0), new Insets(-5.0))));
		}
		
		String messageErro = service.salvarProduto(produto, imagens);
		
		if (!ObjectUtil.isStringPreenchida(messageErro)) {
			calculoProduto();
			buscarImagemPaginado();
			btnSalvar.setDisable(true);
			
			labelMensagem.setText("Produto gravado com sucesso!");
			labelMensagem.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(5.0), new Insets(-5.0))));
		} else {
			labelMensagem.setText(messageErro);
			labelMensagem.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5.0), new Insets(-5.0))));
		}
	}

	@FXML
	public void selecionarFornecedor(ActionEvent aa) throws IOException {
		if (fornecedor == null) {
			AmbienteSystem ambienteSystem = new AmbienteSystem();
			ScreensSystem screensSystem = Main.getScreensSystem();
			FXMLLoader fxmlLoaderSeletorFornecedor = new FXMLLoader();
			
			fxmlLoaderSeletorFornecedor.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "fornecedor/seletorFornecedor.fxml"));
			screensSystem.setSeletorFornecedor(new Scene((AnchorPane) fxmlLoaderSeletorFornecedor.load()));
			Main.changeScreen(screensSystem.getSeletorFornecedor());
		} else {
			Scene cadastroFornecedor = Main.getScreensSystem().getSeletorFornecedor();
			Main.changeScreen(cadastroFornecedor);
		}
	}
	
	@FXML
	public void redirecionarListagemProduto(ActionEvent aa) throws IOException {
		AmbienteSystem ambienteSystem = new AmbienteSystem();
		ScreensSystem screensSystem = Main.getScreensSystem();
		FXMLLoader fxmlLoaderListagemProduto = new FXMLLoader();
		fxmlLoaderListagemProduto.setLocation(Main.class.getResource(ambienteSystem.getAmbiente() + "listagemproduto/listagemProdutos.fxml"));
		screensSystem.setListagemProduto(new Scene((AnchorPane) fxmlLoaderListagemProduto.load()));
		Main.changeScreen(screensSystem.getListagemProduto());
	}

	@FXML
	public void adicionarImagem(ActionEvent aa) {
		FileChooser file = new FileChooser();
		
		file.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png"));
		imagens = file.showOpenMultipleDialog(new Stage());
		
		labelMensagem.setText("Imagens adicionadas com sucesso!");
		labelMensagem.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(5.0), new Insets(-5.0))));
	}
	
	private Produto popularProduto() {
		Produto produto = this.produto;
		
		if (produto == null) {
			return null;
		}
		
		if (ObjectUtil.isStringPreenchida(codigo.getText())) {
			produto.setCodigo(Long.parseLong(codigo.getText()));
		}
		
		if (ObjectUtil.isStringPreenchida(descricao.getText())) {
			produto.setDescricao(descricao.getText());
		}
		
		if (ObjectUtil.isStringPreenchida(teor.getText())) {
			produto.setTeor(new BigDecimal(teor.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(kilo.getText())) {
			produto.setKilo(new BigDecimal(kilo.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(custoBruto.getText())) {
			produto.setCustoBruto(new BigDecimal(custoBruto.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(pesoMetal.getText())) {
			produto.setPesoMetal(new BigDecimal(pesoMetal.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(peso.getText())) {
			produto.setPeso(new BigDecimal(peso.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(acessorios.getText())) {
			produto.setAcessorio(new BigDecimal(acessorios.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(solta.getText())) {
			produto.setSolta(new BigDecimal(solta.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(banho.getText())) {
			produto.setBanho(new BigDecimal(banho.getText().replaceAll(",", ".")));
		}
		
		if (ObjectUtil.isStringPreenchida(custoJA.getText())) {
			produto.setCustoJa(new BigDecimal(custoJA.getText().replaceAll(",", ".")));
		}
		
		if (fornecedor != null) {
			produto.setFornecedor(fornecedor);
		}
		
		return produto;
	}
	
	@FXML
	public void limparTela() {
		this.codigo.setText("");
		this.descricao.setText("");
		this.teor.setText("");
		this.kilo.setText("");
		this.custoBruto.setText("");
		this.pesoMetal.setText("");
		this.peso.setText("");
		this.acessorios.setText("");
		this.solta.setText("");
		this.banho.setText("");
		this.custoJA.setText("");
		this.fornecedor = new Fornecedor();
		this.labelFornecedor.setText("");
		this.imagens = new ArrayList<File>();
		this.produto = new Produto();
		this.btnSalvar.setDisable(Boolean.FALSE);
	}
	
	private void popularTelaEditar(Object userData, String nomeTela, String nomeParametro) {
		if ("cadastroProduto".equals(nomeTela) 
				&& userData != null 
				&& "idProduto".equals(nomeParametro)) {
			
			SimpleLongProperty idProduto = (SimpleLongProperty) userData;
			this.produto = service.findProduto(idProduto.longValue());
			
			popularTelaEditarProduto(produto);
			buscarImagemPaginado();
			calculoProduto();
		}
	}

	@FXML
	public void calculoProduto() {
		CalculoProdutoService CalculoProdutoService = new CalculoProdutoService();
		Produto produto = popularProduto();
		
		if (produto == null) {
			return;
		}
		
		limparCalculo();
		CalculoProdutoBean calculoProduto = CalculoProdutoService.calculoProduto(produto);
		
		if (calculoProduto.getCustoMetal() != null) {
			this.txtCustoMetal.setText(calculoProduto.getCustoMetal().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ","));
		}
		
		if (calculoProduto.getBruto() != null) {
			this.txtBruto.setText(calculoProduto.getBruto().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ","));
		}
		
		if (calculoProduto.getValorMetal() != null) {
			this.txtMetal.setText(calculoProduto.getValorMetal().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ","));
		}
		
		if (calculoProduto.getTotalCusto() != null) {
			this.txtTotalCusto.setText(calculoProduto.getTotalCusto().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ","));
		}

		if (calculoProduto.getCustoGrama() != null) {
			this.txtCustoGrama.setText(calculoProduto.getCustoGrama().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ","));
		}

		if (calculoProduto.getFat() != null) {
			this.txtFat.setText(calculoProduto.getFat().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ","));
		}

		if (calculoProduto.getPorcentagem() != null) {
			this.txtPorcentagem.setText(calculoProduto.getPorcentagem().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ",") + " %");
		}
	}

	private void limparCalculo() {
		this.txtCustoMetal.setText("");
		this.txtBruto.setText("");
		this.txtMetal.setText("");
		this.txtTotalCusto.setText("");
		this.txtCustoGrama.setText("");
		this.txtFat.setText("");
		this.txtPorcentagem.setText("");
	}

	private void popularTelaEditarProduto(Produto produto) {
		this.codigo.setText(produto.getCodigo().toString());
		this.descricao.setText(produto.getDescricao().toString());
		this.teor.setText(produto.getTeor().toString());
		this.kilo.setText(produto.getKilo().toString());
		this.custoBruto.setText(produto.getCustoBruto().toString());
		this.pesoMetal.setText(produto.getPesoMetal().toString());
		this.peso.setText(produto.getPeso().toString());
		this.acessorios.setText(produto.getAcessorio().toString());
		this.solta.setText(produto.getSolta().toString());
		this.banho.setText(produto.getBanho().toString());
		this.custoJA.setText(produto.getCustoJa().toString());
		
		this.fornecedor = produto.getFornecedor();
		labelFornecedor.setText(fornecedor.getNome());
	}

	@FXML
	public void buscarImagemAvancarPaginacao() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			int numeroPagina = Integer.parseInt(pageNumeroPagina.getText()) + 1;
			pageNumeroPagina.setText(String.valueOf(numeroPagina));
			
			buscarImagemPaginado();
		}
	}
	
	@FXML
	public void buscarImagemRetrocederPaginacao() {
		 
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			int quantidadePorPagina = Integer.parseInt(pageNumeroPagina.getText());
			
			if (quantidadePorPagina > 1) {
				quantidadePorPagina = quantidadePorPagina - 1;
				pageNumeroPagina.setText(String.valueOf(quantidadePorPagina));
			}
			
			buscarImagemPaginado();
		}
	}
	
	@FXML
	public void buscarImagemPaginado() {
		if (pageNumeroPagina != null || pageQuantidadePorPagina != null) {
			ImagemProdutoService imagemProdutoService = new ImagemProdutoService();
			
			List<ImagemProduto> imagens = imagemProdutoService.buscarImagemPaginado(produto.getIdProduto(), Integer.parseInt(pageQuantidadePorPagina.getText()), Integer.parseInt(pageNumeroPagina.getText()));
			
			for (int i = 0; i < imagens.size(); i++) {
				if (i == 0) {
					
					ImagemProduto imagemProduto = imagens.get(i);
					Image image = buscarImagem(imagemProduto);
					img1.setImage(image);
					
				} else if (i == 1) {
					
					ImagemProduto imagemProduto = imagens.get(i);
					Image image = buscarImagem(imagemProduto);
					img2.setImage(image);
					
				} else if (i == 2) {

					ImagemProduto imagemProduto = imagens.get(i);
					Image image = buscarImagem(imagemProduto);
					img3.setImage(image);
					
				} else if (i == 3) {

					ImagemProduto imagemProduto = imagens.get(i);
					Image image = buscarImagem(imagemProduto);
					img4.setImage(image);
					
				} else if (i == 4) {

					ImagemProduto imagemProduto = imagens.get(i);
					Image image = buscarImagem(imagemProduto);
					img5.setImage(image);
					
				} else if (i == 5) {

					ImagemProduto imagemProduto = imagens.get(i);
					Image image = buscarImagem(imagemProduto);
					img6.setImage(image);
					
				}
			}
 		}
	}

	private Image buscarImagem(ImagemProduto imagemProduto) {
		byte[] img = imagemProduto.getImagem();
		InputStream input = new ByteArrayInputStream(img);
		Image image = new Image(input);
		
		return image;
	}
	
	private void preencherFornecedor(Object userData, String nomeTela, String nomeParametro) {
		if ("cadastroProduto".equals(nomeTela) 
				&& userData != null 
				&& "Fornecedor".equals(nomeParametro)) {
			
			fornecedor = (Fornecedor) userData;
			labelFornecedor.setText(fornecedor.getNome());
		}
	}
}








