package socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import socket.data.ListaDeContatos;
import socket.data.Pessoa;

public class EnviarDados {
    public static void enviarSerializado(String host, int port, Pessoa p) throws IOException, ClassNotFoundException{
		Socket clientSocket;
		clientSocket = new Socket(host, port);
		ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
		System.out.println(p);
		outToServer.writeObject(p);
		clientSocket.close();

	}
    public static void enviarSerializado(String host, int port, ListaDeContatos p) throws IOException, ClassNotFoundException{
		Socket clientSocket;
		clientSocket = new Socket(host, port);
		ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
		System.out.println(p);
		outToServer.writeObject(p);
		clientSocket.close();

	}
	public static void enviarSerializadoOtimizado(String host, int port, Pessoa p) throws IOException{
		Socket clientSocket;
		clientSocket = new Socket(host, port);
		OutputStream outputStream = clientSocket.getOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		System.out.println(p);
		p.writeObject(out);
		out.flush();
		clientSocket.close();
	}
	public static void enviarSerializadoOtimizado(String host, int port, ListaDeContatos c) throws IOException{
		Socket clientSocket;
		clientSocket = new Socket(host, port);
		OutputStream outputStream = clientSocket.getOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		System.out.println(c);
		c.writeObject(out);
		out.flush();
		clientSocket.close();
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
	public static void enviarBytes(String host, int port, byte[] data) throws IOException, ClassNotFoundException{
		Socket clientSocket;
		clientSocket = new Socket(host, port);
        OutputStream outputStream = clientSocket.getOutputStream();
		outputStream.write(data);
		outputStream.flush();
		clientSocket.close();
    }
    
}
