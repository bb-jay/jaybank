package br.com.jaybank.modelos;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.jaybank.excecoes.ContaJaExisteException;
import br.com.jaybank.modelos.conta.*;
import br.com.jaybank.modelos.pessoa.*;
import br.com.jaybank.util.DigitoVerificador;

public class Agencia implements Serializable {

	private static final long serialVersionUID = 3000000L;

	private final int numero;
	private final Set<Pessoa> clientes = new HashSet<>();
	private final Set<Integer> numerosDeConta = new HashSet<>();
	private final Map<Integer, Conta> contas = new HashMap<>();
	private final Map<Pessoa, Cadastro> mapPessoasContas = new HashMap<>();// [0] = CC, [1] = CI, [2] = CP
	

	public Agencia(int numero) {
		this.numero = numero;
	}

	public boolean cadastrarCliente(Pessoa cliente) {
		if (cliente instanceof PessoaFisica)
			return this.cadastrarCliente((PessoaFisica) cliente);
		else if (cliente instanceof PessoaJuridica)
			return this.cadastrarCliente((PessoaJuridica) cliente);
		else
			throw new ClassCastException("Tipo de cliente inválido");
	}

	private boolean cadastrarCliente(PessoaFisica pessoa) {
		if (clientes.contains(pessoa))
			return false;
		clientes.add(pessoa);
		mapPessoasContas.put(pessoa, new Cadastro());
		return true;
	}

	private boolean cadastrarCliente(PessoaJuridica empresa) {
		if (clientes.contains(empresa))
			return false;
		clientes.add(empresa);
		mapPessoasContas.put(empresa, new Cadastro());

		PessoaFisica dono = empresa.getResponsavel();
		if (!clientes.contains(dono))
			clientes.add(dono);
		return true;
	}

	public int abrirConta(Pessoa cliente, Class<? extends Conta> tipoDeConta) throws ContaJaExisteException {
		if (cliente == null)
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		verificaSeTemConta(cliente, tipoDeConta);
		this.cadastrarCliente(cliente);

		int numero = DigitoVerificador.geraNumeroConta(tipoDeConta,
				Collections.unmodifiableSet(numerosDeConta));
		numerosDeConta.add(numero);
		Cadastro cadastro = mapPessoasContas.get(cliente);
		Conta novaConta;

		if (tipoDeConta == ContaCorrente.class)
			novaConta = new ContaCorrente(numero, cliente);
		else if (tipoDeConta == ContaInvestimento.class)
			novaConta = new ContaInvestimento(numero, cliente);
		else if (tipoDeConta == ContaPoupanca.class)
			novaConta = new ContaPoupanca(numero, (PessoaFisica) cliente);
		else
			throw new IllegalArgumentException("tipoDeConta inválido");

		cadastro.setContaDoTipo(tipoDeConta, novaConta);
		contas.put(novaConta.getNumero(), novaConta);
		return numero;
	}

	private void verificaSeTemConta(Pessoa cliente, Class<? extends Conta> tipoDeConta) throws ContaJaExisteException {
		Conta existente;

		if ((existente = this.getContaDoTipo(cliente, tipoDeConta)) != null) {
			throw new ContaJaExisteException(
					String.format("Cliente já tem %s com número %d", tipoDeConta.getSimpleName(), existente.getNumero()));
		}
	}

	public int getNumero() {
		return this.numero;
	}

	public Conta getConta(int numero) {
		return contas.get(numero);
	}

	public Cadastro getCadastro(Pessoa cliente) {
		return mapPessoasContas.get(cliente);
	}

	public <C extends Conta> C getContaDoTipo(Pessoa cliente, Class<C> tipoDeConta) {
		if (!mapPessoasContas.containsKey(cliente))
			return null;
		return mapPessoasContas.get(cliente).getContaDoTipo(tipoDeConta);
	}

	public Pessoa getCliente(int documento) {
		for (Pessoa cliente : clientes) {
			if (cliente.getDocumento() == documento)
				return cliente;
		}
		return null;
	}

	@Override
	public int hashCode() {
		return this.numero;
	}

	public void passarMes() {
		contas.forEach((numero, conta) -> {
			conta.passarMes();
		});
	}
}
