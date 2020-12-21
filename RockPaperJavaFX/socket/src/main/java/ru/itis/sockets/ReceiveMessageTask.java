package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.MainController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
// слушатель сообщений от сервера
public class ReceiveMessageTask extends Task<Void> {
    // читаем сообщения с сервера
    private BufferedReader fromServer;

    private MainController controller;


    public ReceiveMessageTask(BufferedReader fromServer, MainController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                // скип для работы другого таска
                if(!isCancelled()) {
                    String messageFromServer = fromServer.readLine();
                    System.out.println(messageFromServer);
                    if (messageFromServer != null) {
                        Platform.runLater(() -> controller.helloLabel.setText("Добро пожаловать, " +
                                messageFromServer.split(": ")[1].split("/")[0] + "!")
                        );
                        Platform.runLater(() -> controller.enableStartGameButton(true));
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
