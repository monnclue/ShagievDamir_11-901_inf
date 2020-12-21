package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import ru.itis.controllers.GameController;

import java.io.BufferedReader;
import java.io.IOException;

public class GameReceiveTask extends Task<Void> {

    private GameController controller;
    private BufferedReader fromServer;

    public GameReceiveTask(BufferedReader fromServer, GameController controller) {
        this.controller = controller;
        this.fromServer = fromServer;
    }


    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    System.out.println(messageFromServer
                    );
                    if (messageFromServer.contains("?otherClientName=")) {
                        String name = messageFromServer.split("otherClientName=")[1];
                        Platform.runLater(() -> {
                            if (name.equals("none")) {
                                controller.oppNameText
                                        .setText("Поиск игроков...");
                            } else {
                                controller.oppNameText
                                        .setText(name);
                                controller.startMatch();
                                //controller.stopWaitingServerEvents();
                            }
                        });
                    } else if (messageFromServer.contains("?action=")) {
                        Platform.runLater(() -> {
                            controller.setOpponentAction(
                                    messageFromServer.split("=")[1]);
                        });
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}


