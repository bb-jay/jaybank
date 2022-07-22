package br.com.jaybank.modelos.pessoa;

import java.time.LocalDate;
import java.time.Period;

import br.com.jaybank.enums.EstadoCivil;
import br.com.jaybank.enums.Genero;
import br.com.jaybank.excecoes.PessoaDuplicadaException;

public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1100000L;

	private final LocalDate nascimento;
	private EstadoCivil estadoCivil;
	private Genero genero;
	private String profissao;

	public PessoaFisica(
			String nome,
			int documento,
			String endereco,
			String telefone,
			String nascimento) throws PessoaDuplicadaException {
		super(nome, endereco, telefone, documento);
		this.nascimento = LocalDate.parse(nascimento);
	}

	public PessoaFisica(
			String nome,
			int documento,
			String endereco,
			String telefone,
			String nascimento,
			char genero,
			char estadoCivil,
			String profissao) throws PessoaDuplicadaException {
		this(nome, documento, endereco, telefone, nascimento);
		this.setGenero(genero);
		this.setEstadoCivil(estadoCivil);
		this.setProfissao(profissao);
	}

	public int getIdade() {
		return Period.between(nascimento, LocalDate.now()).getYears();
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(char estado) {
		EstadoCivil e = EstadoCivil.deChar(estado);
		if (e == null)
			throw new IllegalArgumentException("char " + estadoCivil + " não é um estado civil");
		this.estadoCivil = e;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(char genero) {
		Genero g = Genero.deChar(genero);
		if (g == null)
			throw new IllegalArgumentException("char " + genero + " não é um gênero");
		this.genero = g;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Override
	public String toString() {
		return "PF:[" +
				super.toString() + ", " +
				this.getIdade() + " anos, " +
				(this.getProfissao() != null ? this.getProfissao() + ", " : "") +
				(this.getEstadoCivil() != null ? this.getEstadoCivil().toString(
						this.getGenero()) + ", " : "")
				+
				(this.getGenero() != null ? this.getGenero() + ", " : "") +
				']';
	}
}
