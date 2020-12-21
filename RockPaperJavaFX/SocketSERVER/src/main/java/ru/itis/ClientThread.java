package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private Long clientId;
    private Long otherClientId;
    private String otherClientName;


    public ClientThread(Socket clientSocket, Long clientId) {
        this.clientSocket = clientSocket;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        ServerSocket server;

        try {
            // читаем сообщения от клиента
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            String messageFromClient = fromClient.readLine();
            EchoServerSocket echoServerSocket = new EchoServerSocket();

            while (messageFromClient != null) {
                 echoServerSocket = new EchoServerSocket();
                if (messageFromClient.contains("EnterRoom")) {
                    echoServerSocket.readyToGame(clientId, true);
                    otherClientId = echoServerSocket.getNotBusyClient(clientId);
                    if (otherClientId == -1L) {
                        toClient.println("Message from server: " + messageFromClient + "/" + clientId +
                                "?otherClientName=" + "none");
                    } else {
                        otherClientName = echoServerSocket.getName(otherClientId);
                        toClient.println("Message from server: " + messageFromClient + "/" + clientId +
                                "?otherClientName=" + otherClientName);
                    }
                } else if(messageFromClient.contains("act=")) {
                    System.out.println(messageFromClient + "/" + clientId + "/" + otherClientId);
                    echoServerSocket.putAction(clientId, messageFromClient
                            .split("/")[0].split("=")[1]);

                } else if(messageFromClient.contains("checkAnyEvent")) {

                    otherClientId = echoServerSocket.getNotBusyClient(clientId);
                    System.out.println("checkAnyEvent" + otherClientId);

                    if (otherClientId != -1L) {
                        otherClientName = echoServerSocket.getName(otherClientId);
                        toClient.println("Message from server: " + messageFromClient + "/" + clientId +
                                "?otherClientName=" + otherClientName);
                    }

                    if (echoServerSocket.containsActionOf(otherClientId)){
                        String action = echoServerSocket.getActionOf(otherClientId);
                        toClient.println("Message from server: " + "?action=" + action);
                    }


                } else {
                    echoServerSocket.setName(clientId, messageFromClient);
                    System.out.println("Message from client: " + messageFromClient + "/" + clientId);
                    toClient.println("Message from server: " + messageFromClient + "/" + clientId);
                }
                messageFromClient = fromClient.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
