package br.com.letscode.modelos.contaa;

import br.com.letscode.enums.PerfilInvestidor;
import br.com.letscode.modelos.pessoaa.Pessoa;

public class ContaInvestimento extends Conta {

	protected PerfilInvestidor perfil;

	public ContaInvestimento(int numero, Pessoa titular) {
		super(numero, titular);
		this.setPerfil('C');
	}

	public PerfilInvestidor getPerfil() {
		return perfil;
	}

	public void setPerfil(char perfil) {
		PerfilInvestidor p = PerfilInvestidor.deChar(perfil);
		if (p == null)
			throw new IllegalArgumentException("Perfil " + perfil + "inválido");
		this.perfil = p;
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
