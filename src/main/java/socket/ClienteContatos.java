package socket;

import socket.data.Classes.ContatosProto;
import socket.data.Classes.PessoaProto;
import socket.data.ListaDeContatos;
import socket.data.Pessoa;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import socket.EnviarDados.*;

class ClienteContatos {
	public static void main(String[] args)  throws IOException, ClassNotFoundException, InterruptedException, JAXBException {

		Pessoa p1 = new Pessoa("Joao da Silva", "Quixada", 999881122L, 2012);
		Pessoa p2 = new Pessoa("Maria Oliveira", "Fortaleza", 998776655L, 1995);
		Pessoa p3 = new Pessoa("Carlos Pereira", "Sobral", 987654321L, 2000);
		Pessoa p4 = new Pessoa("Ana Costa", "Juazeiro", 996644332L, 1988);


		ListaDeContatos c1 = new ListaDeContatos(p1);
		c1.adicionarContato(p2);
		c1.adicionarContato(p3);
		c1.adicionarContato(p4);
		//Objeto Serializado
		System.out.println("CLIENTE OBJETO SERIALIZADO");
		EnviarDados.enviarSerializado("localhost",6789,c1);

		//Objeto Otimizado
		Thread.sleep(2000);
		System.out.println("CLIENTE OBJETO OTIMIZADO:");
		EnviarDados.enviarString("localhost",6789,c1.toString());

		//Json
		Thread.sleep(2000);
		System.out.println("CLIENTE JSON:");
		EnviarDados.enviarString("localhost",6789,c1.toJson());

		//XML
		Thread.sleep(2000);
		System.out.println("CLIENTE XML:");
		EnviarDados.enviarString("localhost",6789,c1.toXml());

		//Protobuffer
		PessoaProto pessoa1 = PessoaProto.newBuilder()
			.setNome(p1.getNome())
			.setCidade(p1.getCidade())
			.setTelefone(p1.getTelefone())
			.setAno(p1.getAno())
			.build();
		PessoaProto pessoa2 = PessoaProto.newBuilder()
			.setNome(p2.getNome())
			.setCidade(p2.getCidade())
			.setTelefone(p2.getTelefone())
			.setAno(p2.getAno())
			.build();
		PessoaProto pessoa3 = PessoaProto.newBuilder()
			.setNome(p3.getNome())
			.setCidade(p3.getCidade())
			.setTelefone(p3.getTelefone())
			.setAno(p3.getAno())
			.build();
		PessoaProto pessoa4 = PessoaProto.newBuilder()
			.setNome(p4.getNome())
			.setCidade(p4.getCidade())
			.setTelefone(p4.getTelefone())
			.setAno(p4.getAno())
			.build();

		ContatosProto contatos = ContatosProto.newBuilder()
			.setDono(pessoa1)
			.addListaDeContatos(pessoa2)
			.addListaDeContatos(pessoa3)
			.addListaDeContatos(pessoa4)
			.build();

		Thread.sleep(2000);
		System.out.println("CLIENTE PROTOBUFFER:");
		EnviarDados.enviarBytes("localhost",6789,contatos.toByteArray());
		System.out.println(contatos.toString());

	}

	
	public static void enviarString(String host, int port, String msg) throws IOException, ClassNotFoundException{
		Socket clientSocket;
		clientSocket = new Socket(host, port);
		OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
		System.out.println(msg);
		out.write(msg);
		out.flush();
		clientSocket.close();

	}
	

}