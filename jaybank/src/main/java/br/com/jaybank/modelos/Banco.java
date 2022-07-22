package br.com.jaybank.modelos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Banco implements Serializable {
	private static final long serialVersionUID = 4000000L;

	// TODO verificar se o banco tem pessoa, não na classe pessoa
	private final Map<Integer, Agencia> agencias = new HashMap<>();
	private final Set<Integer> numerosDeAgencia = new HashSet<>();
	private Agencia selecionada;

	public void criaAgencia() {
		int numero = this.agencias.size();
		Agencia novaAgencia = new Agencia(numero);
		numerosDeAgencia.add(numero);
		agencias.put(numero, novaAgencia);
		selecionada = novaAgencia;
	}

	public void selecionar(int numero) {
		if (!numerosDeAgencia.contains(numero))
			throw new IllegalArgumentException("Não existe agência com esse número");
		this.selecionada = agencias.get(numero);
	}

	public Agencia selecionada() {
		return this.selecionada;
	}



}
