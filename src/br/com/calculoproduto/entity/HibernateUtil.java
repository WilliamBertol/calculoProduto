package br.com.calculoproduto.entity;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.calculoproduto.AmbienteSystem;

public class HibernateUtil {

	private static SessionFactory factory = null;

	private static Configuration conf;

	private static SessionFactory buildSessionFactory() {

		try {
			AmbienteSystem ambiente = new AmbienteSystem();
			conf = new Configuration();
			
			if (ambiente.isAmbienteDesenvolvimento()) {
				conf.configure("hibernate.cfg.xml");
			} else {
				conf.configure("src/hibernate.cfg.xml");
			}
			
			System.out.println("Configure");
			factory = conf.buildSessionFactory();
			System.out.println("Construi");
			return factory;
		} catch (Throwable ex) {

			ex.printStackTrace();

			throw new ExceptionInInitializerError();

		}

	}

	public static SessionFactory getSessionFactory() {

		if (factory == null)
			factory = buildSessionFactory();

		return factory;

	}

}