package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import socket.data.Pessoa;
import socket.data.Classes;
import socket.data.Classes.PessoaProto;

class ServidorPessoa {
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		ServerSocket listenSocket;
			listenSocket = new ServerSocket(6789);
			while (true) {
				System.out.println("SERVIDOR OBJETO:");
				System.out.println("Recebido");
				//Serializado
				Socket connectionSocket = listenSocket.accept();
				ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
				Pessoa p = (Pessoa) inFromClient.readObject();
				System.err.println(p);
				connectionSocket.close();

				InputStreamReader in;
				int data;

				//Objeto Otimizado
				System.out.println("SERVIDOR OBJETO OTIMIZADO:");
				System.out.println("Recebido");
				connectionSocket = listenSocket.accept();
				inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
				p = new Pessoa();
				p.readObject(inFromClient);
				System.out.println(p);
				connectionSocket.close();


				//Json
				System.out.println("\nSERVIDOR JSON:");
				System.out.println("Recebido");
				connectionSocket = listenSocket.accept();
				in = new InputStreamReader(connectionSocket.getInputStream());
				data = in.read();

				while (data != -1) {
					System.out.print((char)data);
					data = in.read();
				}
				connectionSocket.close();

				//XML
				connectionSocket = listenSocket.accept();
				in = new InputStreamReader(connectionSocket.getInputStream());
				data = in.read();
				System.out.println("\nSERVIDOR XML:");
				System.out.println("Recebido");
				while (data != -1) {
					System.out.print((char)data);
					data = in.read();
				}
				connectionSocket.close();

				//Protobuffer
				connectionSocket = listenSocket.accept();
				InputStream inputStream = connectionSocket.getInputStream();
				
				PessoaProto pessoa = PessoaProto.parseFrom(inputStream);
				System.out.println("\nSERVIDOR PROTOBUFFER:");
				System.out.println("Recebido");
				System.out.println(pessoa.toString());
				connectionSocket.close();


				

				break;
			}
			listenSocket.close();

	}
}