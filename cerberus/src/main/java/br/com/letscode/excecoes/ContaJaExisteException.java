package br.com.letscode.excecoes;

public class ContaJaExisteException extends RuntimeException{
	public ContaJaExisteException() {super();}
	public ContaJaExisteException(String s) {super(s);}
}