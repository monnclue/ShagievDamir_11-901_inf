package ru.itis.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import ru.itis.application.MainApplication;
import ru.itis.sockets.ReceiveMessageTask;
import ru.itis.sockets.SocketClient;
import ru.itis.view.View;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 26.11.2020
 * 06. JavaFx
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainController implements Initializable {

    private SocketClient client;
    private final MainApplication application = View.getApplication();

    @FXML
    private AnchorPane pane;

    @FXML
    private Button helloButton;
    @FXML
    private Button startGameButton;

    @FXML
    public Label helloLabel;


    @FXML
    private TextField messagesTextField;

    public MainController() throws Exception {
    }


    public void enableStartGameButton(Boolean b) {
        startGameButton.setDisable(!b);
    }

    public Button getStartGameButton(){
        return startGameButton;
    }


    private void loadGameScene(ActionEvent event) {
        String fxmlFile = "/fxml/Main.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableStartGameButton(false);
        client = new SocketClient("localhost", 5555);
        // запускаем слушателя сообщений
        ReceiveMessageTask receiveMessageTask = new ReceiveMessageTask(
                client.getFromServer(), this);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(receiveMessageTask);

        helloButton.setOnAction(event -> {
            String nickname = messagesTextField.getText().equals("")
                    ? "Anonymous" : messagesTextField.getText();
            client.sendMessage(nickname);

            // кидаем таск в мут
            receiveMessageTask.cancel();
        });

    }


    public SocketClient getClient() {
        return client;
    }

}
