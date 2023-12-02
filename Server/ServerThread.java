package Server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread{

    private Socket socket;

    public ServerThread (Socket socket) {
        this.socket = socket;
        System.out.println("  Stato    Tipo Richiesta  Porta Server  Porta Client  Indirizzo Client\n");
    }

    public void run() {
        try {
            int number = (int) (Math.random() * 100);
            System.out.println("Il numero da indovinare e' " + number);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while(true) {
                String userInput = in.readLine();
                if (userInput == null || userInput.equals("QUIT"))
                    break;
                int guess = Integer.parseInt(userInput);
                if(guess == number){
                    out.writeBytes("CORRECT\n");
                    System.out.println("CORRECT");
                    break;
                } else if(guess < number){
                    out.writeBytes("LOW\n");
                    System.out.println("LOW");
                } else {
                    out.writeBytes("HIGH\n");
                    System.out.println("HIGH");
                }
                System.out.println("Il Client "+ socket.getInetAddress() +" "
                + socket.getPort() +" "
                + socket.getLocalPort() +" ha scritto: " + userInput);
            }
            out.close();
            in.close();
            System.out.println("Ricezione una chiamata di chiusura da:\n" + socket + "\n");
            socket.close();
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
