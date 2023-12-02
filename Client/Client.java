package Client;

import java.io.*;
import java.net.*;

public class Client { 

    public void start()throws IOException { 
        //Connessione della Socket con il Server 
        Socket socket = new Socket("localhost", 7777); 

        //Stream di byte da passare al Socket 
        DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.print("Per disconnettersi dal Server scrivere: QUIT\n"); 

        //Ciclo infinito per inserimento testo del Client 
        while (true) 
        { 
            System.out.print("Inserisci: "); 
            String userInput = stdIn.readLine(); 
            if (userInput.equals("QUIT")) 
                break; 
            out.writeBytes(userInput + '\n');

            String input = in.readLine();
            if (input.equals("CORRECT")){
                System.out.println("Hai indovinato!");
                break;
            } else if (input.equals("LOW")){
                System.out.println("Troppo basso!");
            } else if (input.equals("HIGH")){
                System.out.println("Troppo alto!");
            } else {
                System.out.println("Errore!");
            }
        } 

        //Chiusura dello Stream e del Socket 
        out.close(); 
        in.close(); 
        socket.close(); 
    } 
    public static void main (String[] args) throws Exception { 
        Client tcpClient = new Client(); 
        tcpClient.start(); 
    } 
} 