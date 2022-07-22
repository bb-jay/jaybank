package br.com.jaybank.telas;

import java.util.Scanner;

import br.com.jaybank.excecoes.SairDaTelaException;
import br.com.jaybank.modelos.Banco;
import br.com.jaybank.util.Console;

// public class Tela {
public abstract class Tela {

	public static Scanner sc = new Scanner(System.in);
	private boolean cabecalho;
	private String[] opcoes;
	private String nome;
	private String mensagem = "";
	
	protected static final int opcoesPorLinha = 4;
	protected static Banco banco;
	
	public Tela(String nome, String[] opcoes, boolean cabecalho) {
		this.nome = nome;
		this.opcoes = opcoes;
		this.cabecalho = cabecalho;
	}
	
	
	// private void opcao1() {
		// 	throw new RuntimeException();
		// };
		// private void opcao2() {};
		// private void opcao3() {};
		// private void opcao4() {};
		// private void opcao5() {};
		// private void opcao6() {};
		// private void opcao7() {};
		// private void opcao8() {};
		
	protected abstract void opcao1() throws SairDaTelaException;
	protected abstract void opcao2() throws SairDaTelaException;
	protected abstract void opcao3() throws SairDaTelaException;
	protected abstract void opcao4() throws SairDaTelaException;
	protected abstract void opcao5() throws SairDaTelaException;
	protected abstract void opcao6() throws SairDaTelaException;
	protected abstract void opcao7() throws SairDaTelaException;
	protected abstract void opcao8() throws SairDaTelaException;
	protected abstract void mostraInfo();
	
	protected void iniciar() {
		while (true) {
			try {
				
				this.mostrar();
				System.out.print("Escolha uma opção... ");
				System.out.println();
				System.out.println(this.mensagem);
				System.out.println();
				this.setMensagem("");
				switch (Console.lerInt(1, this.opcoes.length)) {
					case 1:
					this.opcao1();
					break;
					case 2:
					this.opcao2();
					break;
					case 3:
					this.opcao3();
					break;
					case 4:
					this.opcao4();
					break;
					case 5:
					this.opcao5();
					break;
					case 6:
					this.opcao6();
					break;
					case 7:
					this.opcao7();
					break;
					case 8:
					this.opcao8();
					break;
				}
			} catch (SairDaTelaException s) {
				break;
			}
		}
	}
	
	protected void mostrar() {
		Console.limparConsole();
		if (this.cabecalho) this.mostraCabecalho();
		this.mostraInfo();
		System.out.println();
		this.mostraOpcoes();
		System.out.println();
	}
	
	protected void mostraOpcoes() {
		for (int i = 0; i < opcoes.length; i += opcoesPorLinha) {
			String linha = "";
			for (int j = 0; j < opcoesPorLinha; j++) {
				try {
					linha += String.format("%d: %s    ", i + j + 1, opcoes[j + i]);
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
			Console.printaCentro(linha);
			System.out.println();
		}
	}
	
	protected void mostraCabecalho() {
		Console.printaCentro("", '-');
		Console.printaCentro("Banco do Jay", '-');
		Console.printaCentro("", '-');
		Console.printaCentro("Aqui seu dinheiro é meu", '-');
		Console.printaCentro("(Não, pera.. é seu, mas eu controlo!)", '-');
		Console.printaCentro("(Não... eu cuido!)", '-');
		Console.printaCentro("", '-');
		System.out.println();
		Console.printaCentro(nome);
		System.out.println();
	}

	protected void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public static Banco getBanco() {
		return banco;
	}

}
