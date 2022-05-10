package br.com.calculoproduto.connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	private String url;
	
	private String usuario;
	
	private String senha;
	
	private Connection con;
	
	public Connect() {
		url = "jdbc:postgresql://localhost:5432/calculoProduto";
		usuario = "postgres";
		senha = "123";
		
		try {
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);
			
			System.out.println("Foiii garaiiii");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
