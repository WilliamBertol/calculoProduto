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
	
	public void delete(Fornecedor fornecedor) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.delete(fornecedor);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> buscarFornecedorPaginado(int limit, int offSet, String filter) {
		List<Fornecedor> fornecedores = new ArrayList<>();
		
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM Fornecedor ");
		
		if (filter != null && !filter.isEmpty() && !filter.isBlank()) {
			sql.append(" WHERE NOME ILIKE '%" + filter.toString() + "%' ");
			sql.append(" 	OR CAST(CODIGO AS TEXT) ILIKE '%" + filter.toString() + "%' ");
		}
		
		sql.append(" LIMIT :limit ");
		sql.append(" OFFSET(:offSet - 1) * :limit ");
		
		Query q = session.createSQLQuery(sql.toString()).addEntity(Fornecedor.class);
		q.setLong("limit", limit);
		q.setLong("offSet", offSet); 
		q.setLong("limit", limit);
		
		fornecedores = q.list();
		
		session.getTransaction().commit();
		session.close();
		
		return fornecedores;
	}
}
