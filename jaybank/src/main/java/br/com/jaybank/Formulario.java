package br.com.jaybank;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import br.com.jaybank.enums.PerfilInvestidor;
import br.com.jaybank.excecoes.ContaJaExisteException;
import br.com.jaybank.excecoes.PessoaDuplicadaException;
import br.com.jaybank.modelos.Agencia;
import br.com.jaybank.modelos.Cadastro;
import br.com.jaybank.modelos.conta.ContaCorrente;
import br.com.jaybank.modelos.conta.ContaInvestimento;
import br.com.jaybank.modelos.pessoa.Pessoa;
import br.com.jaybank.modelos.pessoa.PessoaFisica;
import br.com.jaybank.modelos.pessoa.PessoaJuridica;
import br.com.jaybank.telas.Tela;
import br.com.jaybank.util.Console;

public abstract class Formulario {

	public static Pessoa entrar() {
		System.out.println("Informe seu documento: ");
		int documento = -1;
		documento = Console.lerInt(0, Integer.MAX_VALUE);
		return Tela.getBanco().selecionada().getCliente(documento);
	}

	public static void meusDados(Pessoa cliente) {
		Console.limparConsole();
		if (cliente instanceof PessoaFisica)
			meusDados((PessoaFisica) cliente);
		if (cliente instanceof PessoaJuridica)
			meusDados((PessoaJuridica) cliente);
		Cadastro cadastro = Tela.getBanco().selecionada().getCadastro(cliente);
		System.out.println("Conta corrente número " + cadastro.getContaCorrente().getNumero());
		if (cadastro.getContaInvestimento() != null)
			System.out.println("Conta investimentos número " + cadastro.getContaInvestimento().getNumero());
		if (cadastro.getContaPoupanca() != null)
			System.out.println("Conta poupança número " + cadastro.getContaPoupanca().getNumero());
		System.out.println("\nAperte ENTER para continuar...");
		Tela.sc.nextLine();
	}

	public static void meusDados(PessoaFisica cliente) {
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("CPF: " + cliente.getDocumento());
		System.out.println("Endereco: " + cliente.getEndereco());
		System.out.println("Telefone: " + cliente.getTelefone());
		System.out.println("Idade: " + cliente.getIdade());
	}

	public static void meusDados(PessoaJuridica cliente) {
		System.out.println("Razão social: " + cliente.getNome());
		System.out.println("CNPJ: " + cliente.getDocumento());
		System.out.println("Endereco: " + cliente.getEndereco());
		System.out.println("Telefone: " + cliente.getTelefone());
		System.out.println("Responsável: " + cliente.getResponsavel().getNome());
	}

	public static Pessoa cadastrarCliente() throws PessoaDuplicadaException {
		while (true) {
			Console.limparConsole();
			System.out.println();
			System.out.println("Pessoa física (PF) ou pessoa jurídica (PJ) ?");
			System.out.println();
			String escolha = Tela.sc.nextLine();
			Pessoa novoCliente = null;
			if (escolha.toLowerCase().equals("pj") ||
					escolha.toLowerCase().equals("pessoa juridica") ||
					escolha.toLowerCase().equals("juridica"))
				novoCliente = cadastrarClientePj();
			if (escolha.toLowerCase().equals("pf") ||
					escolha.toLowerCase().equals("fisica") ||
					escolha.toLowerCase().equals("pessoa fisica"))
				novoCliente = cadastrarClientePf();
			if (novoCliente == null)
				continue;
			try {
				Tela.getBanco().selecionada().abrirConta(novoCliente, ContaCorrente.class);
			} catch (ContaJaExisteException e) {
				System.out.println("ERRO: " + e);
			}
			return novoCliente;
		}

	}

	public static PessoaFisica cadastrarClientePf() throws PessoaDuplicadaException {
		Console.limparConsole();
		System.out.println();
		Console.printaCentro("Informe os dados do cliente");
		// Console.printaCentro("(escreva CANCELAR para sair)");
		System.out.println();
		System.out.printf("%-30s", "Nome :");
		String nome = Tela.sc.nextLine();
		System.out.printf("%-30s", "CPF :");
		int documento = -1;
		while (true) {
			documento = Console.lerInt(0, Integer.MAX_VALUE);
			// TODO verificar se o banco tem pessoa, não na classe pessoa
			// if (Pessoa.documentos.contains(documento)) {
			// 	System.out.println("CPF já cadastrado...");
			// 	continue;
			// }
			break;
		}
		System.out.printf("%-30s", "Endereço :");
		String endereco = Tela.sc.nextLine();
		System.out.printf("%-30s", "Telefone :");
		String telefone = Tela.sc.nextLine();
		System.out.printf("%-30s", "Nascimento (AAAA-MM-DD):");
		String nascimento = "";
		while (true) {
			nascimento = Tela.sc.nextLine();
			try {
				LocalDate.parse(nascimento);
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Data inválida...");
			}
		}

		PessoaFisica novoCliente = new PessoaFisica(nome, documento, endereco, telefone, nascimento);
		Tela.getBanco().selecionada().cadastrarCliente(novoCliente);
		return novoCliente;
	}

	public static PessoaJuridica cadastrarClientePj() throws PessoaDuplicadaException {
		Console.limparConsole();
		System.out.println();
		Console.printaCentro("Informe os dados da empresa");
		// Console.printaCentro("(escreva CANCELAR para sair)");
		System.out.println();
		System.out.printf("%-30s", "Razão social :");
		String nome = Tela.sc.nextLine();
		System.out.printf("%-30s", "CNPJ :");
		int documento = -1;
		while (true) {
			documento = Console.lerInt(0, Integer.MAX_VALUE);
			// TODO verificar se o banco tem pessoa, não na classe pessoa
			// if (Pessoa.documentos.contains(documento)) {
			// 	System.out.println("CNPJ já cadastrado...");
			// 	continue;
			// }
			break;
		}
		System.out.printf("%-30s", "Endereço :");
		String endereco = Tela.sc.nextLine();
		System.out.printf("%-30s", "Telefone :");
		String telefone = Tela.sc.nextLine();
		PessoaFisica dono = null;
		while (true) {
			System.out.printf("%-30s", "CPF do dono:");
			int cpfDono = Console.lerInt(0, Integer.MAX_VALUE);
			try {
				dono = (PessoaFisica) Tela.getBanco().selecionada().getCliente(cpfDono);
				if (dono == null)
					throw new ClassCastException();
				break;
			} catch (ClassCastException e) {
				System.out.println("Dono não encontrado. Cadastrar dono (s/n)?");
				if (Tela.sc.nextLine().toLowerCase().equals("s")) {
					dono = cadastrarClientePf();
					try {
						Tela.getBanco().selecionada().abrirConta(dono, ContaCorrente.class);
					} catch (ContaJaExisteException err) {
						System.out.println("ERRO: " + err);
					}
					break;
				}
			}
		}

		PessoaJuridica novoCliente = new PessoaJuridica(nome, documento, endereco, telefone, dono);
		Tela.getBanco().selecionada().cadastrarCliente(novoCliente);
		return novoCliente;
	}

	public static void mudarPerfil(ContaInvestimento conta) {
		Console.limparConsole();
		Console.printaCentro("Escolha o perfil de investidor: ");
		System.out.println();
		Console.printaCentro("1:Conservador    2:Moderado    3:Agressivo");
		PerfilInvestidor novoPerfil;
		switch (Console.lerInt(1, 3)) {
			case 1:
				novoPerfil = PerfilInvestidor.CONSERVADOR;
				break;
			case 2:
				novoPerfil = PerfilInvestidor.MODERADO;
				break;
			case 3:
				novoPerfil = PerfilInvestidor.AGRESSIVO;
				break;
			default:
				throw new IllegalArgumentException("Opção inválida");
		}
		conta.setPerfil(novoPerfil);
	}

	public static boolean fazerContaInvestimento(Pessoa cliente, Cadastro cadastro, Agencia agencia) {
		System.out.println("Você não tem conta investimento. Criar uma? (s/n)");
		String escolha;
		
		do {
			escolha = Tela.sc.nextLine();
		}	while (!escolha.equals("s") && !escolha.equals("n"));
		
		if (escolha.equals("s")) {
			try {
				agencia.abrirConta(cliente, ContaInvestimento.class);
				return true;
			} catch (ContaJaExisteException e) {
				System.out.println("A conta já existe. É a número " + cadastro.getContaInvestimento().getNumero());
				return false;
			}
		} else {
			return false;
		}
	}
}