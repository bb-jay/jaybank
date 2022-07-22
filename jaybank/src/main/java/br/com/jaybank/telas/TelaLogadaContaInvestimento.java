package br.com.jaybank.telas;

import br.com.jaybank.Formulario;
import br.com.jaybank.excecoes.SairDaTelaException;
import br.com.jaybank.excecoes.SaldoInsuficienteException;
import br.com.jaybank.modelos.Cadastro;
import br.com.jaybank.modelos.conta.ContaCorrente;
import br.com.jaybank.modelos.conta.ContaInvestimento;
import br.com.jaybank.modelos.pessoa.Pessoa;
import br.com.jaybank.util.Console;

public class TelaLogadaContaInvestimento extends Tela {

	private ContaInvestimento conta;
	private ContaCorrente contaCorrente;
	private Pessoa cliente;

	public TelaLogadaContaInvestimento(Cadastro cadastro) {
		super(
				"Conta Investimento",
				new String[] {
						"Meus dados", 		// 1 ok
						"Investir", 			// 2 ok
						"Resgatar", 			// 3 ok
						"Mudar perfil", 	// 4 ok
						"Voltar" }, 			// 5 ok
				false);
		this.conta = cadastro.getContaInvestimento();
		this.contaCorrente = cadastro.getContaCorrente();
		this.cliente = conta.getTitular();
	}

	@Override
	protected void opcao1() {
		Formulario.meusDados(cliente);
	}

	@Override
	protected void opcao2() {
		System.out.print("Informe valor do investimento: ");
		long investimento = Console.lerMoeda();
		try {
			contaCorrente.transferir(investimento, conta);
		} catch (SaldoInsuficienteException e) {
			super.setMensagem(String.format(
					"Saldo insuficiente... %s na conta corrente",
					contaCorrente.getSaldoFormatado()));
			return;
		}
		super.setMensagem("Investimento realizado com sucesso!");
	}

	@Override
	protected void opcao3() {
		System.out.print("Informe valor do resgate: ");
		long resgate = Console.lerMoeda();
		try {
			conta.transferir(resgate, contaCorrente);
		} catch (SaldoInsuficienteException e) {
			super.setMensagem("Saldo insuficiente...");
			return;
		}
		super.setMensagem("Resgate realizado com sucesso!");
	}

	@Override
	protected void opcao4() {
		try {
			Formulario.mudarPerfil(conta);
		} catch (IllegalArgumentException e) {
			Console.limparConsole();
			System.out.println(e);
			System.out.println("Ocorreu um erro... Aperte ENTER para continuar... ");
			Tela.sc.nextLine();
		}
	}

	@Override
	protected void opcao5() throws SairDaTelaException {
		throw new SairDaTelaException();
	}

	@Override
	protected void opcao6() {
	}

	@Override
	protected void opcao7() {
	}

	@Override
	protected void opcao8() {
	}

	@Override
	protected void mostraInfo() {
		Console.printaCentro(cliente.getNome());
		Console.printaCentro("CONTA INVESTIMENTO: " + conta.getNumero());
		Console.printaCentro(String.format("SALDO: %s\t\tPERFIL: %s",
				conta.getSaldoFormatado(),
				conta.getPerfil()));
	};

}
