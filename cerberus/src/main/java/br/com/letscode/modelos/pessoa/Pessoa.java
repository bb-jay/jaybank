package br.com.letscode.modelos.pessoa;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

import br.com.letscode.excecoes.PessoaDuplicadaException;

public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 98491616516511658L;

	private String nome;
	private String endereco;
	private String telefone;
	private int documento;
	public static final Set<Integer> documentos = new HashSet<Integer>();

	Pessoa(String nome, String endereco, String telefone, int documento) throws PessoaDuplicadaException{
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
