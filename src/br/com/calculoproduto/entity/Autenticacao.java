package br.com.calculoproduto.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autenticacao implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idAutenticacao")
	private Long idAutenticacao;
	
	private String senha;

	public Long getIdAutenticacao() {
		return idAutenticacao;
	}

	public void setIdAutenticacao(Long idAutenticacao) {
		this.idAutenticacao = idAutenticacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
