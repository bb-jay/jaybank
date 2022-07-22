package br.com.jaybank.enums;

import java.util.HashMap;

public enum EstadoCivil {
	SOLTEIRO('S', "Solteir"),
	CASADO('C', "Casad"),
	VIUVO('V', "Viúv"),
	DIVORCIADO('D', "Divorciad");

	private static final long serialVersionUID = 0100000L;
	private final char valor;
	private final String valorCheio;

	private static final class EstaticosInternos {
		private static final HashMap<Character, EstadoCivil> map = new HashMap<Character, EstadoCivil>();
	}

	EstadoCivil(char estado, String estadoCheio) {
		this.valor = estado;
		this.valorCheio = estadoCheio;
		EstaticosInternos.map.put(estado, this);
	}

	public static EstadoCivil deChar(char estado) {
		return EstaticosInternos.map.get(estado);
	}

	@Override
	public String toString() {
		return toString(null);
	}

	public String toString(Genero g) {
		switch (g) {
			case FEMININO:
				return this.valorCheio + 'a';
			case MASCULINO:
				return this.valorCheio + 'o';
			case OUTRO:
				return this.valorCheio + 'x';
			default:
				return "" + this.valor;
		}
	}
}
