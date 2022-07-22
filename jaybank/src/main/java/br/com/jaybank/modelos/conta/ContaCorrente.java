package br.com.jaybank.modelos.conta;

import br.com.jaybank.excecoes.LimiteInsuficienteException;
import br.com.jaybank.excecoes.PagamentoExcessivoException;
import br.com.jaybank.excecoes.SaldoInsuficienteException;
import br.com.jaybank.modelos.pessoa.Pessoa;
import br.com.jaybank.util.ConverteValor;

public class ContaCorrente extends Conta {

	private static final long serialVersionUID = 2100000L;

	protected long divida;
	private long limite;

	public ContaCorrente(int numero, Pessoa titular) {
		super(numero, titular);
		this.limite = 0;
		this.divida = 0;
	}

	public void fazerEmprestimo(long emprestimo) throws LimiteInsuficienteException {
		validaValor(emprestimo);
		if (emprestimo + divida > this.limite)
			throw new LimiteInsuficienteException();

		super.saldo += emprestimo;
		this.divida += emprestimo;
	}

	@Override
	public void passarMes() {
		long juros = Math.round(this.divida * super.taxa.getJuros());
		this.divida += juros;
	}

	public void pagarDivida(long pagamento) throws PagamentoExcessivoException, SaldoInsuficienteException{
		super.validaValor(pagamento);
		if (pagamento > this.divida)
			throw new PagamentoExcessivoException();

		this.divida -= super.tirar(pagamento, 0);
	}

	public long getLimite() {
		return limite;
	}

	public String getLimiteFormatado() {
		return ConverteValor.comCifrao(this.limite);
	}

	public long getDivida() {
		return this.divida;
	}

	public String getDividaFormatada() {
		return ConverteValor.comCifrao(this.divida);
	}

	public void setLimite(long limite) {
		super.validaValor(limite);
		this.limite = limite;
	}

	@Override
	public String toString() {
		return "CC: {" + super.toString() + 
				"Limite: " + ConverteValor.semCifrao(this.limite) + ", " +
				"Divida: " + ConverteValor.semCifrao(this.divida) +
				'}';
	}

}
