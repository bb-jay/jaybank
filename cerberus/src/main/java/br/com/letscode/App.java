package br.com.letscode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.com.letscode.excecoes.BancoJayException;
import br.com.letscode.modelos.Agencia;
import br.com.letscode.modelos.Banco;
import br.com.letscode.modelos.conta.ContaCorrente;
import br.com.letscode.modelos.pessoa.Pessoa;
import br.com.letscode.modelos.pessoa.PessoaFisica;
import br.com.letscode.telas.TelaInicial;

public class App {
    public static void main(String[] args) throws BancoJayException, IOException, ClassNotFoundException {

        Agencia ag1 = new Agencia(1);
        Pessoa ze = new PessoaFisica(
                "Zé da Silva",
                123,
                "Rua da Goiaba, 420",
                "(66) 6666-6666",
                "2000-01-01");

        ag1.cadastrarCliente(ze);
        ag1.abrirConta(ze, ContaCorrente.class);
        Banco.agencias.add(ag1);
        Banco.selecionada = ag1;
        
        // ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dados.bin"));
        // Banco.selecionada = (Agencia) ois.readObject();
        // ois.close();
        
        new TelaInicial();

        // ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dados.bin"));
        // oos.writeObject(Banco.selecionada);
        // oos.close();
    }
}
