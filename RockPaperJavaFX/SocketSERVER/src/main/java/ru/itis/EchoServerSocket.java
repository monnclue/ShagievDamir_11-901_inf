package ru.itis;

import ru.itis.Model.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class EchoServerSocket {
    private Long clientAmount = 0L;
    private static HashMap<Long,Client> clientHashMap;
    private static HashMap<Long,String> clientActions;
    // два клиента, сообщение от первого клиента должно уходить второму клиенту
    // сообщение от второго клиента должно уходить первому
    public void start(int port) {
        clientHashMap = new HashMap<>();
        clientActions = new HashMap<>();
        ServerSocket server;

        try {

            server = new ServerSocket(port);
            // уводит приложение в ожидание, пока не подключится клиент
            // как только клиент подключился, поток продолжает выполнение и помещает
            // "клиента" в client

            while (true) {
                Socket clientSocket = server.accept();
                clientAmount++;
                clientHashMap.put(clientAmount,
                        Client.builder().isBusy(true).build());
                ClientThread clientThread =
                        new ClientThread(clientSocket, clientAmount);
                new Thread(clientThread).start();
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void putAction(Long clientId, String action) {
        clientActions.put(clientId, action);
    }

    public String getActionOf(Long id) {
        return clientActions.get(id);
    }

    public Boolean containsActionOf(Long id) {
        return clientActions.containsKey(id);
    }

    public void setName(Long id, String name) {
        clientHashMap.get(id).setName(name);
    }

    public String getName(Long id) {
        return clientHashMap.get(id).getName();
    }


    public void readyToGame(Long id, Boolean isReady) {
        clientHashMap.get(id).setBusy(!isReady);
    }

    /*
    id тут нужен для того чтобы не коннектить клиента самого с собой
     */
    public Long getNotBusyClient(Long clientId) {
        try {
            return clientHashMap.entrySet().stream()
                    .filter(elem -> !elem.getValue().isBusy()
                            && !elem.getKey().equals(clientId))
                    .findAny().get().getKey();
        } catch (NoSuchElementException exception) {
            return -1L;
        }
    }

}
