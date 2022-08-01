package br.com.jaybank.modelos.conta;

import br.com.jaybank.modelos.pessoa.PessoaFisica;

public class ContaPoupanca extends Conta {

	private static final long serialVersionUID = 2300000L;
	
	public ContaPoupanca(int numero, PessoaFisica titular) {
		super(numero, titular);
	}

	@Override
	public void passarMes() {
		long rendimento = Math.round(super.getSaldo() * super.getTaxa().getRendimento());
		super.depositar(rendimento);
	}

	@Override
	public String toString() {
		return "CP: {" + super.toString() + 
				'}';
	}
}
