package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class Server {

    public void start() throws Exception {
       ServerSocket serverSocket = new ServerSocket(7777);
       BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); 
        //Ciclo infinito di ascolto dei Client
        System.out.println(" Attesa ");
        while(true){
            /*String userInput = stdIn.readLine(); 
            if (userInput.equals("QUIT")) 
                break;*/
            Socket socket = serverSocket.accept();
            System.out.println("Ricezione una chiamata di apertura da:\n" + socket);
            //avvia il processo per ogni client 
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
        }
        //stdIn.close();
        //serverSocket.close();
    }
    public static void main (String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }
}
