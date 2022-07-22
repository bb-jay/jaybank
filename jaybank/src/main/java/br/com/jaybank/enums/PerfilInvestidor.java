package br.com.jaybank.enums;

import java.util.HashMap;
import java.util.Random;

public enum PerfilInvestidor {
	CONSERVADOR(
			0,
			0.1,
			'C'),
	MODERADO(
			-0.4,
			0.7,
			'M'),
	AGRESSIVO(
			-1.0,
			1.6,
			'A');

	private static final class MapaInterno {
		private static final HashMap<Character, PerfilInvestidor> map = new HashMap<Character, PerfilInvestidor>();
	}

	private static final long serialVersionUID = 0300000L;
	private static final Random random = new Random();
	private final double min;
	private final double max;

	PerfilInvestidor(double min, double max, char perfil) {
		this.min = min / 100;
		this.max = max / 100;
		MapaInterno.map.put(perfil, this);
	}

	public static PerfilInvestidor deChar(char perfil) {
		return MapaInterno.map.get(perfil);
	}

	public double getVariacao() {
		return min + PerfilInvestidor.random.nextDouble() * (max - min);
	}

}
