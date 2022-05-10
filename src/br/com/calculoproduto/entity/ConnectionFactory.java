package br.com.calculoproduto.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("eao-generico");;
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
}
