package br.com.jaybank.enums;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.jaybank.modelos.pessoa.Cliente;
import br.com.jaybank.modelos.pessoa.PessoaFisica;
import br.com.jaybank.modelos.pessoa.PessoaJuridica;

public class Taxa implements Serializable {

	private static final long serialVersionUID = 0400000L;
	private static final Map<Class<? extends Cliente>, Taxa> taxasPorTipo = new HashMap<>();
	static {
		taxasPorTipo.put(PessoaFisica.class, new Taxa(
			0,
			0,
			0.003,
			0.010
		));
		taxasPorTipo.put(PessoaJuridica.class, new Taxa(
			0.005,
			0.005,
			0.005,
			0.007
		));
	}

	private final double transferencia;
	private final double saque;
	private final double rendimento;
	private final double juros;

	Taxa(
			double transferencia,
			double saque,
			double rendimento,
			double juros) {
		this.transferencia = transferencia;
		this.saque = saque;
		this.rendimento = rendimento;
		this.juros = juros;
	}

	public double getJuros() {
		return this.juros;
	}

	public double getTransferencia() {
		return this.transferencia;
	}

	public double getSaque() {
		return this.saque;
	}

	public double getRendimento() {
		return this.rendimento;
	}

	public static Taxa determinaTaxa(Cliente titular) {
		Taxa resultado = taxasPorTipo.get(titular.getClass());
		if (resultado == null)
			throw new IllegalArgumentException("Tipo de taxa não pôde ser determinado.");
		return resultado;
	}

	public static <C extends Cliente> boolean adicionaTipoCliente(Class<C> tipoCliente, Taxa novaTaxa) {
		if (taxasPorTipo.containsKey(tipoCliente))
			return false;
		taxasPorTipo.put(tipoCliente, novaTaxa);
		return true;
	}
}
