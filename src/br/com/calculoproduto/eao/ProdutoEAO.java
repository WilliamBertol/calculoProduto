package br.com.calculoproduto.eao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.calculoproduto.Main;
import br.com.calculoproduto.entity.Produto;

public class ProdutoEAO {

	public void saveOrUpdate(Produto produto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.saveOrUpdate(produto);
		session.getTransaction().commit();
		
		session.close();
	}
	
	public void update(Produto produto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.update(produto);
		session.getTransaction().commit();
	}
	
	public Produto find(Long idProduto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		
		Query q = session.createQuery("from Produto where idProduto = :idProduto");
		q.setLong("idProduto", idProduto);
		Produto produto = (Produto) q.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return produto;
	}
	
	public Produto findByCodigo(String codigo) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		
		Query q = session.createQuery("from Produto where codigo = :codigo");
		q.setString("codigo", codigo);
		Produto produto = (Produto) q.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		return produto;
	}
	
	public void delete(Produto produto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.delete(produto);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> buscarProdutoPaginado(int limit, int offSet, String filter) {
			
		List<Produto> produtos = new ArrayList<>();
		
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM Produto P ");
		sql.append(" 	INNER JOIN Fornecedor F ON F.IDFORNECEDOR = P.IDFORNECEDOR ");
		
		if (filter != null && !filter.isEmpty() && !filter.isBlank()) {
			sql.append(" WHERE P.descricao ILIKE '%" + filter.toString() + "%' ");
			sql.append(" 	OR F.nome ILIKE '%" + filter.toString() + "%' ");
			sql.append(" 	OR CAST(P.CODIGO AS TEXT) ILIKE '%" + filter.toString() + "%' ");
		}
		
		sql.append(" LIMIT :limit ");
		sql.append(" OFFSET(:offSet - 1) * :limit ");
		
		Query q = session.createSQLQuery(sql.toString()).addEntity(Produto.class);
		q.setLong("limit", limit);
		q.setLong("offSet", offSet); 
		q.setLong("limit", limit);
		
		produtos = q.list();
		
		session.getTransaction().commit();
		session.close();
		
		return produtos;
	}
}
