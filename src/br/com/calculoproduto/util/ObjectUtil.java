package br.com.calculoproduto.util;

public class ObjectUtil {

	public static boolean isStringPreenchida(String a) {
		return a != null && !a.isBlank() && !a.isEmpty();
	}
}
