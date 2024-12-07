package socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.xml.bind.JAXBException;

import socket.data.Classes.PessoaProto;
import socket.data.Pessoa;

import socket.EnviarDados.*;

class ClientePessoa {
	public static void main(String[] args)  throws IOException, ClassNotFoundException, InterruptedException, JAXBException {

		Pessoa p1 = new Pessoa("Joao da Silva", "Quixada", 999881122, 2012);
		
		
		//Objeto Serializado
		System.out.println("CLIENTE OBJETO SERIALIZADO");
		EnviarDados.enviarSerializado("localhost",6789,p1);

		//Objeto Otimizado
		Thread.sleep(2000);
		System.out.println("CLIENTE OBJETO OTIMIZADO:");
		EnviarDados.enviarSerializadoOtimizado("localhost",6789,p1);

		//Json
		Thread.sleep(2000);
		System.out.println("CLIENTE JSON:");
		EnviarDados.enviarString("localhost",6789,p1.toJson());

		//XML
		Thread.sleep(2000);
		System.out.println("CLIENTE XML:");
		EnviarDados.enviarString("localhost",6789,p1.toXml());

		//Protobuffer
		PessoaProto pessoa = PessoaProto.newBuilder()
			.setNome(p1.getNome())
			.setCidade(p1.getCidade())
			.setTelefone(p1.getTelefone())
			.setAno(p1.getAno())
			.build();
		Thread.sleep(2000);
		System.out.println("CLIENTE PROTOBUFFER:");
		EnviarDados.enviarBytes("localhost",6789,pessoa.toByteArray());
		System.out.println(pessoa.toString());


	}

	

}