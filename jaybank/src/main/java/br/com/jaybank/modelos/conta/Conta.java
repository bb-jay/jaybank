package br.com.jaybank.modelos.conta;

import java.io.Serializable;

import br.com.jaybank.enums.Taxa;
import br.com.jaybank.excecoes.SaldoInsuficienteException;
import br.com.jaybank.modelos.pessoa.Pessoa;
import br.com.jaybank.util.ConverteValor;

public abstract class Conta implements Serializable {

	private static final long serialVersionUID = 2000000L;

	private final int numero;
	private Pessoa titular;
	private long saldo;
	private final Taxa taxa;

	public abstract void passarMes();

	Conta(int numero, Pessoa titular) {
		this.numero = numero;
		this.titular = titular;
		this.saldo = 0;
		this.taxa = Taxa.determinaTaxa(titular);
	}

	public void depositar(long deposito) {
		validaValor(deposito);
		this.saldo += deposito;
	}

	protected long tirar(long retirada, double taxa)
			throws SaldoInsuficienteException {
		validaValor(retirada);
		long tarifa = Math.round(retirada * taxa);
		if (retirada + tarifa < this.saldo) {
			this.saldo -= retirada + tarifa;
			return retirada;
		}
		throw new SaldoInsuficienteException(String.format(
				"Requer: %.2f, Tem: %.2f", retirada + tarifa, saldo));
	}

	public long sacar(long saque) throws SaldoInsuficienteException {
		validaValor(saque);
		return tirar(saque, this.taxa.getSaque());
	}

	public void transferir(long transferencia, Conta destino)
			throws SaldoInsuficienteException {
		destino.depositar(
				this.tirar(
						transferencia,
						this.getTitular() == destino.getTitular() ? 0 : this.taxa.getTransferencia()));
	}

	public long getSaldo() {
		return saldo;
	}

	public String getSaldoFormatado() {
		return ConverteValor.comCifrao(this.saldo);
	}

	public Pessoa getTitular() {
		return titular;
	}

	public void setTitular(Pessoa titular) {
		this.titular = titular;
	}

	public int getNumero() {
		return numero;
	}

	protected Taxa getTaxa() {
		return taxa;
	}

	protected void validaValor(long valor) {
		if (valor < 0)
			throw new IllegalArgumentException("Valor deve ser positivo");
	}

	@Override
	public int hashCode() {
		return this.numero;
	}

	@Override
	public String toString() {
		return this.getNumero() + ", " +
				this.getTitular().getNome() + ", " +
				"Saldo: " + ConverteValor.semCifrao(this.saldo) + ", ";
	}

}
