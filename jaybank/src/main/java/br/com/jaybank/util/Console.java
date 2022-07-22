package br.com.jaybank.util;

import br.com.jaybank.telas.Tela;

public abstract class Console {
	
	public static int lerInt(int min, int max) {
		int valor = Integer.MIN_VALUE;
		while (true) {
			try {
				valor = Integer.parseInt(Tela.sc.nextLine());
			} catch (Exception e) {
			}
			if (valor >= min && valor <= max)
				break;
			System.out.print("Tente novamente... ");
		}
		return valor;
	}

	public static long lerMoeda() {
		double valorDouble = 0;
		while (true) {
			try {
				valorDouble = Double.parseDouble(Tela.sc.nextLine());
			} catch (Exception e) {
				System.out.print("Tente novamente... ");
				continue;
			}
			if (valorDouble < 0.01) {
				System.out.println("Valor deve ser positivo... ");
			}
			break;
		}
		long valor = (long) (valorDouble * 100);
		return valor;
	}

	
	public static void printaCentro(String s) {
		printaCentro(s, ' ');
	}

	public static void printaCentro(String s, char c) {
		System.out.println(centraliza(s, c));
	}

	public static String centraliza(String s) {
		return centraliza(s, ' ');
	}

	public static String centraliza(String s, char c) {
		int comeco = (80 - s.length()) / 2;
		if (comeco < 0) {
			s = s.substring(0, 80);
			comeco = 0;
		}
		// Protegendo os espaços
		s = s.replace(' ', '~');
		// Botando c na frente e atrás
		String saida = new String(new char[comeco]);
		saida = saida.replace((char) 0, c);
		saida += s;
		saida = String.format("%-80s", saida).replace(' ', c);
		saida = saida.replace('~', ' ');
		return saida;

	}

	public static void limparConsole() {
		try {
			String operatingSystem = System.getProperty("os.name"); // Check the current operating system
			if (operatingSystem.contains("Windows")) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
