package br.com.calculoproduto.eao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.calculoproduto.Main;
import br.com.calculoproduto.entity.Autenticacao;

public class AutenticacaoEAO {

	public void saveOrUpdate(Autenticacao autenticacao) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.saveOrUpdate(autenticacao);
		session.getTransaction().commit();
		session.close();
	}
	
	public Autenticacao findBySenha(String senha) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		Query q = session.createQuery("from Autenticacao where senha = :senha");
		q.setString("senha", senha);
		
		Autenticacao autenticacao = (Autenticacao) q.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		return autenticacao;
	}
}
