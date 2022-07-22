package br.com.jaybank.modelos.conta;

import br.com.jaybank.enums.PerfilInvestidor;
import br.com.jaybank.modelos.pessoa.Pessoa;

public class ContaInvestimento extends Conta {

	private static final long serialVersionUID = 2200000L;

	protected PerfilInvestidor perfil;

	public ContaInvestimento(int numero, Pessoa titular) {
		super(numero, titular);
		this.setPerfil(PerfilInvestidor.CONSERVADOR);
	}

	public PerfilInvestidor getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilInvestidor perfil) {
		if (perfil == null)
			throw new IllegalArgumentException("Perfil " + perfil + "inválido");
		this.perfil = perfil;
	}

	@Override
	public void passarMes() {
		double taxaRendimento = this.perfil.getVariacao() + super.taxa.getRendimento();
		long rendimento = Math.round(super.saldo * taxaRendimento);
		this.saldo += rendimento;
	}

	@Override
	public String toString() {
		return "CI: {" + super.toString() +
				'}';
	}

}
