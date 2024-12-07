package socket;

import socket.data.Classes.ContatosProto;
import socket.data.Classes.PessoaProto;
import socket.data.ListaDeContatos;
import socket.data.Pessoa;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServidorContatos {
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		ServerSocket listenSocket;
			listenSocket = new ServerSocket(6789);
			while (true) {
				System.out.println("SERVIDOR OBJETO:");
				System.out.println("Recebido");
				//Serializado
				Socket connectionSocket = listenSocket.accept();
				ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
				ListaDeContatos c = (ListaDeContatos) inFromClient.readObject();
				System.err.println(c);
				connectionSocket.close();

				InputStreamReader in;
				int data;

				//Objeto Otimizado
				System.out.println("SERVIDOR OBJETO OTIMIZADO:");
				System.out.println("Recebido");
				connectionSocket = listenSocket.accept();
				inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
				c = new ListaDeContatos();
				c.readObject(inFromClient);
				System.out.println(c);
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
				
				ContatosProto contatos = ContatosProto.parseFrom(inputStream);
				System.out.println("\nSERVIDOR PROTOBUFFER:");
				System.out.println("Recebido");
				System.out.println(contatos.toString());
				connectionSocket.close();

				break;
			}
			listenSocket.close();

	}
}