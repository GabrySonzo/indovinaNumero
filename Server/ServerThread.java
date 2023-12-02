package Server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread{

    private Socket socket;

    public ServerThread (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            int number = (int) (Math.random() * 100);
            int i = 0;
            boolean indovinato = false;
            System.out.println("Il numero da indovinare per " + socket.getPort() + "/" + socket.getLocalPort() + " e' " + number);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while(true) {
                String userInput = in.readLine();
                if (userInput == null || userInput.equals("QUIT")){
                    indovinato = true;
                    break;
                }
                System.out.print("Il Client " + socket.getPort() + "/" + socket.getLocalPort() +" ha scritto: " + userInput + " ");
                i++;
                int guess = Integer.parseInt(userInput);
                if(guess == number){
                    out.writeBytes("CORRECT\n");
                    System.out.println("(CORRECT)");
                    indovinato = true;
                    break;
                } else if(guess < number){
                    out.writeBytes("LOW\n");
                    System.out.println("(LOW)");
                } else {
                    out.writeBytes("HIGH\n");
                    System.out.println("(HIGH)");
                }
            }
            if(!indovinato){
                out.writeBytes(number + "\n");
                System.out.println("Il Client " + socket.getPort() + "/" + socket.getLocalPort() + " ha esaurito i tentativi");
            }
            out.close();
            in.close();
            System.out.print(" -- RICEZIONE DI UNA CHIAMATA DI CHIUSURA DA " + socket.getPort() + "/" + socket.getLocalPort() + " -- \n");
            socket.close();
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
