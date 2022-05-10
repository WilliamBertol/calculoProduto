package br.com.calculoproduto.eao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.calculoproduto.Main;
import br.com.calculoproduto.entity.Fornecedor;

public class FornecedorEAO {

	public void saveOrUpdate(Fornecedor fornecedor) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.saveOrUpdate(fornecedor);
		session.getTransaction().commit();
		session.close();
	}
	
	public Fornecedor find(Long idFornecedor) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		Query q = session.createQuery("from Fornecedor where idFornecedor = :idFornecedor");
		q.setLong("idFornecedor", idFornecedor);
		Fornecedor fornecedor = (Fornecedor) q.uniqueResult();
		session.getTransaction().commit();
		session.close();
		
		return fornecedor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> buscarFornecedoresPaginado(int limit, int offSet) {
		
		List<Fornecedor> fornecedores = new ArrayList<>();
		
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		
		Query q = session.createSQLQuery("select * from Fornecedor LIMIT :limit OFFSET(:offSet - 1) * :limit").addEntity(Fornecedor.class);
		q.setLong("limit", limit);
		q.setLong("offSet", offSet); 
		q.setLong("limit", limit);
		fornecedores = q.list();
		
		session.getTransaction().commit();
		session.close();
		
		return fornecedores;
	}
}
