package com.chronostore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;
    private final CommandHandler handler;

    public Server(int port, Store store){
        this.port=port;
        this.handler=new CommandHandler(store);
    }

    public void start() throws IOException{
        ServerSocket serverSocket=new ServerSocket(port);
        System.out.print("ChronoStore listening on port" + port);

        while(true){
            Socket clientSocket = serverSocket.accept();//blocking call - wait here until someone connects
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            handleClient(clientSocket);
        }
    }
     private void handleClient(Socket socket){
         try (
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//gets the bytes from network and converts to characters,
                 // reading on character at a time is inefficient so the bufferreader stores chunks in memory
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
         ) {
             String line;
             while ((line = in.readLine()) != null) {
                 System.out.println(">> " + line);
                 String response = handler.handle(line);
                 out.println(response);
             }
         } catch (IOException e) {
             System.out.println("Client disconnected");
         }
     }

}
