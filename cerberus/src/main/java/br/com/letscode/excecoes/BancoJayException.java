package br.com.letscode.excecoes;

public abstract class BancoJayException extends Exception {
	BancoJayException() {super();}
	BancoJayException(String s) {super(s);}
}
