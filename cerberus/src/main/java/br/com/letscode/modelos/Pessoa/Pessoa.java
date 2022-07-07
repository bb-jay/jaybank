package br.com.letscode.modelos.Pessoa;

import java.util.HashSet;
import java.util.Set;

import br.com.letscode.excecoes.PessoaDuplicadaException;

public abstract class Pessoa {
	private String nome;
	private String endereco;
	private String telefone;
	private int documento;
	private static final Set<Integer> documentos = new HashSet<Integer>();

	Pessoa(String nome, String endereco, String telefone, int documento) {
		if (Pessoa.documentos.contains(documento))
			throw new PessoaDuplicadaException();
		this.documento = documento;
		Pessoa.documentos.add(documento);
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return
				this.getNome() + ", " +
				this.getDocumento() + ", " +
				this.getEndereco() + ", " +
				this.getTelefone();
	}
}
