package br.com.calculoproduto.eao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.calculoproduto.Main;
import br.com.calculoproduto.entity.ImagemProduto;

public class ImagemProdutoEAO {

	public void saveOrUpdate(ImagemProduto imagemProduto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.saveOrUpdate(imagemProduto);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(ImagemProduto imagemProduto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.delete(imagemProduto);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(Set<ImagemProduto> imagensProduto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		session.delete(imagensProduto);
		session.getTransaction().commit();
		session.close();
	}
	
	public ImagemProduto find(Long idImagemProduto) {
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		ImagemProduto imagemProduto = (ImagemProduto) session.get(ImagemProduto.class, idImagemProduto);
		session.getTransaction().commit();
		session.close();
		
		return imagemProduto;
	}
	
	@SuppressWarnings("unchecked")
	public List<ImagemProduto> buscarImagesByProduto(Long idProduto) {
			
		List<ImagemProduto> imagens = new ArrayList<>();
		
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM ImagemProduto i ");
		sql.append(" 	WHERE i.idproduto = :idProduto ");
		
		Query q = session.createSQLQuery(sql.toString()).addEntity(ImagemProduto.class);
		q.setLong("idProduto", idProduto);
		
		imagens = q.list();
		
		session.getTransaction().commit();
		session.close();
		
		return imagens;
	}
	
	@SuppressWarnings("unchecked")
	public List<ImagemProduto> buscarImagemPaginado(Long idProduto, int limit, int offSet) {
			
		List<ImagemProduto> imagens = new ArrayList<>();
		
		Main main = new Main();
		Session session = main.getSession();
		
		session.beginTransaction();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM ImagemProduto i ");
		sql.append(" 	WHERE i.idproduto = :idProduto ");
		sql.append(" LIMIT :limit ");
		sql.append(" OFFSET(:offSet - 1) * :limit ");
		
		Query q = session.createSQLQuery(sql.toString()).addEntity(ImagemProduto.class);
		q.setLong("idProduto", idProduto);
		q.setLong("limit", limit);
		q.setLong("offSet", offSet); 
		q.setLong("limit", limit);
		
		imagens = q.list();
		
		session.getTransaction().commit();
		session.close();
		
		return imagens;
	}
}
