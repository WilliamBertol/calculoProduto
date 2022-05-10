package br.com.calculoproduto.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagemProduto")
public class ImagemProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idImagemProduto")
	private Long idImagemProduto;
	
	private byte[] imagem;
	
	@ManyToOne(targetEntity=Produto.class)
	@JoinColumn(name="idProduto")
	private Produto produto;

	public Long getIdImagemProduto() {
		return idImagemProduto;
	}

	public void setIdImagemProduto(Long idImagemProduto) {
		this.idImagemProduto = idImagemProduto;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
