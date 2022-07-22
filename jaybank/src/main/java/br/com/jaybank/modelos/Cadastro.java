package br.com.jaybank.modelos;

import java.util.HashMap;
import java.util.Map;

import br.com.jaybank.modelos.conta.Conta;
import br.com.jaybank.modelos.conta.ContaCorrente;
import br.com.jaybank.modelos.conta.ContaInvestimento;
import br.com.jaybank.modelos.conta.ContaPoupanca;

public class Cadastro {
	private Map<Class<? extends Conta>, Conta> contas = new HashMap<>();

	public ContaCorrente getContaCorrente() {
		return (ContaCorrente) contas.get(ContaCorrente.class);
	}
	public ContaPoupanca getContaPoupanca() {
		return (ContaPoupanca) contas.get(ContaPoupanca.class);
	}
	public ContaInvestimento getContaInvestimento() {
		return (ContaInvestimento) contas.get(ContaInvestimento.class);
	}
	
	@SuppressWarnings("unchecked")
	public <C extends Conta> C getContaDoTipo(Class<C> tipoDeConta) {
		return (C) this.contas.get(tipoDeConta);
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contas.put(ContaCorrente.class, contaCorrente);
	}
	public void setContaPoupanca(ContaPoupanca contaPoupanca) {
		this.contas.put(ContaPoupanca.class, contaPoupanca);
	}
	public void setContaInvestimento(ContaInvestimento contaInvestimento) {
		this.contas.put(ContaInvestimento.class, contaInvestimento);
	}
	public void setContaDoTipo(Class<? extends Conta> tipoDeConta, Conta conta) {
		if (!tipoDeConta.isInstance(conta))
			throw new ClassCastException("Tipo da conta não bate com a classe informada");
		this.contas.put(tipoDeConta, conta);
	}

}
