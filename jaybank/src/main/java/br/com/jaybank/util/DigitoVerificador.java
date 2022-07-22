package br.com.jaybank.util;

import java.util.Random;
import java.util.Set;

import br.com.jaybank.modelos.conta.Conta;
import br.com.jaybank.modelos.conta.ContaCorrente;
import br.com.jaybank.modelos.conta.ContaInvestimento;

public abstract class DigitoVerificador {
	private static final int[] pesos = {1,3,5,9,5,3};
	public static final int numeroDeDigitos = 6;
	
	public static int calculaDigito(int num) {
		if (String.valueOf(num).length() != numeroDeDigitos) 
			throw new IllegalArgumentException("Número deve ter " + (numeroDeDigitos) + " dígitos");
		int[] digitos = new int[numeroDeDigitos];
		for (int i = 0; i < numeroDeDigitos; i++) {
			digitos[numeroDeDigitos-1-i] = (num / (int) Math.pow(10, i)) % 10;
		}
		int soma = 0;
		for (int i = 0; i < numeroDeDigitos; i++) {
			soma += digitos[i] * pesos[i];
		}
		return soma % 10;
	}

	public static boolean verificaDigito(int numComDigito) {
		if (String.valueOf(numComDigito).length() != numeroDeDigitos + 1) 
		throw new IllegalArgumentException("Número deve ter " + (numeroDeDigitos + 1) + " dígitos");
		
		int digito = numComDigito % 10;
		int num = numComDigito / 10;
		return calculaDigito(num) == digito;
	}

	public static int geraNumeroConta(Class<? extends Conta> tipoDeConta, Set<Integer> numerosDeConta) {
		int numero;
		Random r = new Random();
		int min = 0;
		int max = (int) Math.pow(10, DigitoVerificador.numeroDeDigitos - 1) - 1;

		//Descobrindo o prefixo
		int prefixo;
		if (tipoDeConta == ContaCorrente.class)
			prefixo = 1;
		else if (tipoDeConta == ContaInvestimento.class)
			prefixo = 2;
		else if (tipoDeConta == ContaInvestimento.class)
			prefixo = 3;
		else
			throw new IllegalArgumentException("Tipo de conta inválido");

		// Colocando o primeiro numero, que corresponde ao tipo de conta
		min = Integer.parseInt(prefixo + String.valueOf(max)) - max;
		max = Integer.parseInt(prefixo + String.valueOf(max));

		do {
			int numeroSemDigito = (int) (min + r.nextDouble() * (max - min));
			int digito = DigitoVerificador.calculaDigito(numeroSemDigito);
			numero = numeroSemDigito * 10 + digito;
		} while (numerosDeConta.contains(numero));
		return numero;
	}

}
