package br.com.letscode.telas;

import br.com.letscode.Formulario;
import br.com.letscode.excecoes.BancoJayException;
import br.com.letscode.excecoes.PessoaDuplicadaException;
import br.com.letscode.excecoes.SairDaTelaException;
import br.com.letscode.modelos.Banco;
import br.com.letscode.modelos.pessoa.Pessoa;
import br.com.letscode.util.Console;

public class TelaInicial extends Tela {

	public TelaInicial(Banco banco) throws BancoJayException {
		super(
			"Tela Inicial",
			new String[] {
				"Entrar", 				//1 ok
				"Cadastrar", 			//2 ok
				"Trocar Agencia", //3
				"Passar um mês", 	//4
				"Sair"},					//5
			true);
		Tela.banco = banco;
		super.iniciar();

	}

	@Override
	protected void opcao1() {
		Pessoa cliente = Formulario.entrar();
		if (cliente == null) {
			super.setMensagem("Cliente não encontrado.");
			return;
		}
		new TelaLogadaContaCorrente(cliente).iniciar();
	}
	@Override
	protected void opcao2() {
		try {
			Pessoa cliente = Formulario.cadastrarCliente();
			new TelaLogadaContaCorrente(cliente).iniciar();
		}
		catch (PessoaDuplicadaException e) {
			super.setMensagem("ERRO: " + e);
		}
	}
	@Override
	protected void opcao3() {}
	@Override
	protected void opcao4() {Tela.banco.selecionada().passarMes();}
	@Override
	protected void opcao5() throws SairDaTelaException {throw new SairDaTelaException();}
	@Override
	protected void opcao6() {}
	@Override
	protected void opcao7() {}
	@Override
	protected void opcao8() {}
	@Override
	protected void mostraInfo() {
		Console.printaCentro("AGENCIA: " + Tela.banco.selecionada().getNumero());
	};
	
}
