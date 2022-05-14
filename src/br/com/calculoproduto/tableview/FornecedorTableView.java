package br.com.calculoproduto.tableview;

import javax.swing.text.MaskFormatter;

import br.com.calculoproduto.entity.Fornecedor;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class FornecedorTableView {

	private SimpleLongProperty idFornecedor;
	
	private SimpleLongProperty codigo;
	
	private SimpleStringProperty nome;
	
	private SimpleStringProperty endereco;
	
	private SimpleStringProperty cidade;
	
	private SimpleStringProperty cnpj;
	
	private SimpleStringProperty inscricaoEstadual;

	public FornecedorTableView(Fornecedor fornecedor) {
		this.idFornecedor = new SimpleLongProperty(fornecedor.getIdFornecedor());
		this.codigo = new SimpleLongProperty(fornecedor.getCodigo());
		this.nome = new SimpleStringProperty(fornecedor.getNome());
		this.endereco = new SimpleStringProperty(fornecedor.getEndereco());
		this.cidade = new SimpleStringProperty(fornecedor.getCidade());
		this.cnpj = new SimpleStringProperty(fornecedor.getCnpj());
		this.inscricaoEstadual = new SimpleStringProperty(fornecedor.getInscricaoEstadual());
	}
	
	public SimpleLongProperty getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(SimpleLongProperty idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Long getCodigo() {
		return codigo.getValue();
	}

	public void setCodigo(SimpleLongProperty codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		if (nome != null) {
			return nome.getValue();
		}
		
		return "";
	}

	public void setNome(SimpleStringProperty nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		if (endereco != null) {
			return endereco.getValue();
		}
		
		return "";
	}

	public void setEndereco(SimpleStringProperty endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		if (endereco != null) {
			return cidade.getValue();
		}
		
		return "";
	}

	public void setCidade(SimpleStringProperty cidade) {
		this.cidade = cidade;
	}

	public String getCnpj() {
		if (endereco != null) {
			MaskFormatter mask;
			try {
				mask = new MaskFormatter("###.###.###/####-##");
				mask.setValueContainsLiteralCharacters(false);
				return mask.valueToString(cnpj.getValue());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return "";
	}

	public void setCnpj(SimpleStringProperty cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		if (endereco != null) {
			return inscricaoEstadual.getValue();
		}
		
		return "";
	}

	public void setInscricaoEstadual(SimpleStringProperty inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
}
