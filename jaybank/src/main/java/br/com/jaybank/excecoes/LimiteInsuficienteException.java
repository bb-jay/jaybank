package br.com.jaybank.excecoes;

public class LimiteInsuficienteException extends BancoJayException{
	public LimiteInsuficienteException(){super();}
	public LimiteInsuficienteException(String s){super(s);}
}
