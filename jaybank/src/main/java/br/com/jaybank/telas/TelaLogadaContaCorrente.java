package br.com.jaybank.telas;

import br.com.jaybank.Formulario;
import br.com.jaybank.excecoes.SairDaTelaException;
import br.com.jaybank.excecoes.SaldoInsuficienteException;
import br.com.jaybank.modelos.Cadastro;
import br.com.jaybank.modelos.conta.Conta;
import br.com.jaybank.modelos.conta.ContaCorrente;
import br.com.jaybank.modelos.pessoa.Pessoa;
import br.com.jaybank.util.Console;

public class TelaLogadaContaCorrente extends Tela {

	private ContaCorrente conta;
	private Cadastro cadastro;
	private Pessoa cliente;

	public TelaLogadaContaCorrente(Pessoa cliente) {
		super(
				"Conta corrente",
				new String[] {
						"Meus dados",			//1 ok
						"Depósito",				//2 ok
						"Saque",					//3 ok
						"Transferir",			//4 ok
						"Empréstimos",		//5
						"Pupança",				//6
						"Investimentos",	//7 ok
						"Voltar" },				//8 ok
						false);
		this.cliente = cliente;
		this.cadastro = Tela.getBanco().selecionada().getCadastro(cliente);
		this.conta = cadastro.getContaCorrente();
	}
	
	@Override
	protected void opcao1() {
		Formulario.meusDados(cliente);
	}

	@Override
	protected void opcao2() {
		System.out.print("Informe valor do depósito: ");
		long deposito = Console.lerMoeda();
		conta.depositar(deposito);
		super.setMensagem("Depositado com sucesso!");
	}
	
	@Override
	protected void opcao3() {
		System.out.print("Informe valor do saque: ");
		long saque = Console.lerMoeda();
		try {conta.sacar(saque);}
		catch (SaldoInsuficienteException e) {
			super.setMensagem("Saldo insuficiente...");
			return;
		}
		super.setMensagem("Saque realizado com sucesso!");
	}
	
	@Override
	protected void opcao4() {
		System.out.print("Informe valor da transferência: ");
		long transferencia = Console.lerMoeda();
		System.out.print("Informe numero da conta para transferencia");
		
		int numeroDestino = Console.lerInt(0, Integer.MAX_VALUE);
		Conta destino = Tela.getBanco().selecionada().getConta(numeroDestino);
		if (destino == null){
			super.setMensagem("Conta de destino não encontrada");
			return;
		}

		try {conta.transferir(transferencia, destino);}
		catch (SaldoInsuficienteException e) {
			super.setMensagem("Saldo insuficiente...");
			return;
		}
	}

	@Override
	protected void opcao5() {
	}

	@Override
	protected void opcao6() {
	}
	
	@Override
	protected void opcao7() {
		if (cadastro.getContaInvestimento() == null) {
			if (!Formulario.fazerContaInvestimento(cliente, cadastro, Tela.banco.selecionada())) {
				return;
			}
		}
		new TelaLogadaContaInvestimento(cadastro).iniciar();
	}
	
	@Override
	protected void opcao8() throws SairDaTelaException{
		throw new SairDaTelaException();
	}

	@Override
	protected void mostraInfo() {
		Console.printaCentro(cliente.getNome());
		Console.printaCentro("CONTA CORRENTE: " + conta.getNumero());
		System.out.println();
		Console.printaCentro(String.format(
				"SALDO: %s\tDÍVIDA: %s\tLIMITE: %s",
				conta.getSaldoFormatado(),
				conta.getDividaFormatada(),
				conta.getLimiteFormatado()));
	};

}
