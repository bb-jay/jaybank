package br.com.jaybank.modelos.pessoa;

import br.com.jaybank.excecoes.PessoaDuplicadaException;

public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1200000L;

	private PessoaFisica responsavel;

	public PessoaJuridica(
			String nome,
			int documento,
			String endereco,
			String telefone,
			PessoaFisica responsavel) throws PessoaDuplicadaException {
		super(nome, endereco, telefone, documento);
		this.setResponsavel(responsavel);
	}

	public PessoaFisica getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(PessoaFisica responsavel) {
		if (responsavel == null)
			throw new IllegalArgumentException("Resonsável não pode ser nulo");
		this.responsavel = responsavel;
	}

	@Override
	public String toString() {
		return "PJ: {" +
				super.toString() + ", " +
				"Responsavel: " + this.getResponsavel() +
				'}';
	}
}
