package socket;

public class Main {
    public static void main(String[] args) {

        String tipo = args[0].toLowerCase();
        Thread serverThread = new Thread(() -> {
            try {
                if (tipo.equals("pessoa")) {
                    ServidorPessoa.main(null);  
                } else if (tipo.equals("contatos")) {
                    ServidorContatos.main(null);  
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Thread para o cliente
        Thread clientThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                if (tipo.equals("pessoa")) {
                    ClientePessoa.main(null);
                } else if (tipo.equals("contatos")) {
                    ClienteContatos.main(null);
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Inicia as threads
        serverThread.start();
        clientThread.start();

        // Aguarda as threads finalizarem (opcional)
        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}