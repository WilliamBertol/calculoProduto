package br.com.calculoproduto.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.text.MaskFormatter;

@Entity
@Table(name="fornecedor")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idFornecedor")
	private Long idFornecedor;
	
	private Long codigo;
	
	private String nome;
	
	private String endereco;
	
	private String cidade;
	
	private String cnpj;
	
	private String inscricaoEstadual;

	
	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	@Override
	public String toString() {
		return getCnpjFormatado() + " - " + this.nome + " - " + getEnderecoFormatado();
	}
	
	private String getEnderecoFormatado() {
		return endereco != null ? endereco : "";
	}

	public String getCnpjFormatado() {
		String cnpjFormatado = "";
		
		if (getCnpj() != null) {
			MaskFormatter mask;
			try {
				mask = new MaskFormatter("###.###.###/####-##");
				mask.setValueContainsLiteralCharacters(false);
				cnpjFormatado = mask.valueToString(getCnpj());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return cnpjFormatado;
	}
}
