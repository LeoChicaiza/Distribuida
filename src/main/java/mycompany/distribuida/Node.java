
package mycompany.distribuida;

import java.net.*;
import java.io.*;

public class Node {
    public static void main(String[] args) throws IOException {
        // Cada nodo es servidor y cliente
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(1234);
                System.out.println("Node waiting for connections...");
                
                while (true) {
                    Socket otherNode = serverSocket.accept();
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(otherNode.getInputStream()));
                    
                    String message = in.readLine();
                    System.out.println("Received: " + message);
                    
                    otherNode.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Intenta conectarse a otro nodo si se proporciona dirección
        if (args.length > 0) {
            String[] parts = args[0].split(":");
            Socket otherNode = new Socket(parts[0], Integer.parseInt(parts[1]));
            PrintWriter out = new PrintWriter(otherNode.getOutputStream(), true);
            
            out.println("Hello from node!");
            System.out.println("Sent message to other node");
            
            otherNode.close();
        }
    }
}
