package ru.itis.application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ru.itis.controllers.GameController;
import ru.itis.controllers.MainController;
import ru.itis.sockets.SocketClient;
import ru.itis.view.View;


import java.io.IOException;

/**
 * 26.11.2020
 * 06. JavaFx
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private  String fxmlFile;
    private  FXMLLoader fxmlLoader;
    private  Parent root;

    private SocketClient socketClient;

    @Override
    public void start(Stage stage) throws Exception {

        View.setApplication(this);

        fxmlFile = "/fxml/Main.fxml";
        fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlFile));
        root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("rock, paper, scissors");
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> System.exit(0));

        //Scene scene = stage.getScene();
        MainController controller = fxmlLoader.getController();
        //scene.setOnMouseClicked(controller.mouseEventEventHandler);
        controller.getStartGameButton().setOnMouseClicked(event ->  {
                    //сохраняем socketClient
                    socketClient = controller.getClient();
                    fxmlFile = "/fxml/Game.fxml";
                    fxmlLoader = new FXMLLoader(this.getClass().getResource(fxmlFile));

                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene gameScene = new Scene(root);
                    stage.setScene(gameScene);
                }

        );

        stage.show();
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }
}
