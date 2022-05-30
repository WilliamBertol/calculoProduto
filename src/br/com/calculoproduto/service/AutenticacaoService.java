package br.com.calculoproduto.service;

import java.security.MessageDigest;

import br.com.calculoproduto.eao.AutenticacaoEAO;
import br.com.calculoproduto.entity.Autenticacao;

public class AutenticacaoService {

	private AutenticacaoEAO eao = new AutenticacaoEAO();
	
	public boolean isSenhaCorreta(String senha) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		    byte messageDigestSenhaAdminNova[] = algorithm.digest(senha.getBytes("UTF-8"));

		    StringBuilder hexStringSenhaAdminNova = new StringBuilder();
		    for (byte b : messageDigestSenhaAdminNova) {
		    	hexStringSenhaAdminNova.append(String.format("%02X", 0xFF & b));
		    }
		    String senhaCriptografada = hexStringSenhaAdminNova.toString();
			
			Autenticacao autenticacao = eao.findBySenha(senhaCriptografada);
			
			if (autenticacao != null) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void salvarTrocaSenha(String senhaAntiga, String novaSenha, String confirmarNovaSenha) {
		
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		    byte messageDigestSenhaAdminNova[] = algorithm.digest(senhaAntiga.getBytes("UTF-8"));
	
		    StringBuilder hexStringSenhaAdminNova = new StringBuilder();
		    for (byte b : messageDigestSenhaAdminNova) {
		    	hexStringSenhaAdminNova.append(String.format("%02X", 0xFF & b));
		    }
		    String senhaCriptografada = hexStringSenhaAdminNova.toString();
			
			Autenticacao autenticacao = eao.findBySenha(senhaCriptografada);
			
			if (autenticacao != null) {
				alterarSenha(novaSenha, confirmarNovaSenha, autenticacao);
				
			} else {
				throw new RuntimeException("Senha antiga não está correta.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void alterarSenha(String novaSenha, String confirmarNovaSenha, Autenticacao autenticacao) {
		if (novaSenha.equals(confirmarNovaSenha)) {
			
            MessageDigest algorithm;
			try {
				algorithm = MessageDigest.getInstance("SHA-256");
				byte messageDigest[] = algorithm.digest(novaSenha.getBytes("UTF-8"));
				
		       StringBuilder hexString = new StringBuilder();
		       for (byte b : messageDigest) {
		         hexString.append(String.format("%02X", 0xFF & b));
		       }
		       String novaSenhaCriptografada = hexString.toString();
				
				autenticacao.setSenha(novaSenhaCriptografada);
				eao.saveOrUpdate(autenticacao);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} else {
			throw new RuntimeException("Nova Senha e Confirmar Nova Senha estão diferentes.");
		}
	}

}
