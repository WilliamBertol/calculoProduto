package br.com.calculoproduto;

public class AmbienteSystem {
	
	private boolean isAmbienteDesenvolvimento = Boolean.FALSE;

	private String producao = "/src/br/com/calculoproduto/views/";
	
	private String desenvolvimento = "views/";

	public String getAmbiente() {
		if (isAmbienteDesenvolvimento) {
			return desenvolvimento;
		}
		
		return producao;
	}

	public boolean isAmbienteDesenvolvimento() {
		return isAmbienteDesenvolvimento;
	}
}
