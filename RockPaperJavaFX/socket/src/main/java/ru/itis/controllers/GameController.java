package ru.itis.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ru.itis.application.MainApplication;
import ru.itis.sockets.GameReceiveTask;
import ru.itis.sockets.SocketClient;
import ru.itis.view.View;

import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController implements Initializable {

    private final MainApplication application = View.getApplication();
    private SocketClient client;
    private TimerTask timerTask;
    private boolean isArmChosen;

    public Text oppNameText;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane justPane;

    @FXML
    private Text whoWon;
    @FXML
    private Text endGame;
    @FXML
    private Text myText;
    @FXML
    private Text oppText;
    @FXML
    private Text myDescription;


    @FXML
    private ImageView myPaper;
    @FXML
    private ImageView oppPaper;
    @FXML
    private ImageView myScissors;
    @FXML
    private ImageView oppScissors;
    @FXML
    private ImageView myRock;
    @FXML
    private ImageView oppRock;

    private String[] arms =  {"rock", "paper", "scissors"};
    private static int i;

    public EventHandler<MouseEvent> click = event -> {
        if (endGame.getOpacity() != 1) {
            if (event.getButton() == MouseButton.PRIMARY) {
                //System.out.println(arms[i]);
                armsOpacityControll(arms[i], myRock,
                        myPaper, myScissors);
                client.sendMessage("act=" + arms[i]);
                // rock-paper-scissors-rock-p...
                i++;
                if (i == 3) {
                    i = 0;
                }
            }
        }
    };


    public GameController() throws Exception {
    }

    private String getChosenArm(ImageView rock, ImageView paper,
                                ImageView scissors) {
        return  rock.getOpacity() == 1 ? "rock" :
                paper.getOpacity() == 1 ? "paper" : "scissors";
    }

    private String matchWon() {
        String oppArm = getChosenArm(oppRock, oppPaper, oppScissors);
        String myArm = getChosenArm(myRock, myPaper, myScissors);

        System.out.println(myArm + "my");
        System.out.println(oppArm + "opp");

        switch (oppArm) {
            case "rock":
                return  (myArm.equals("paper")) ? "Победа!" :
                        (myArm.equals("rock")) ? "Ничья" : "Поражение";
            case "paper":
                return  (myArm.equals("scissors")) ? "Победа!" :
                        (myArm.equals("paper")) ? "Ничья" : "Поражение";
            case "scissors":
                return  (myArm.equals("rock")) ? "Победа!" :
                        (myArm.equals("scissors")) ? "Ничья" : "Поражение";
        }

        return "Ничья";
    }

    public void startMatch() {

        java.util.Timer timer = new java.util.Timer();

        TimerTask timeTask = new TimerTask() {
            @Override
            public void run() {
                stopWaitingServerEvents();
                endGame.setOpacity(1);
                whoWon.setOpacity(1);
                whoWon.setText(matchWon());
            }
        };

        //Random rnd = new Random();
        timer.schedule(timeTask, 10000);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isArmChosen = false;
        try {
            findPlayers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        justPane.setOnMouseClicked(click);
        client = application.getSocketClient();
        client.sendMessage("EnterRoom");
        GameReceiveTask gameReceiveTask = new GameReceiveTask(
                client.getFromServer(), this);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(gameReceiveTask);
    }



    private void findPlayers() throws InterruptedException {

        java.util.Timer timer = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                client.sendMessage("checkAnyEvent");
            }
        };

        //чтобы не перегружать сообщениями
        timer.schedule(timerTask, 0, 1000);
    }

    public void stopWaitingServerEvents() {
        timerTask.cancel();
    }


    private void armsOpacityControll(String arm, ImageView rock,
                                     ImageView paper, ImageView scissors) {
        isArmChosen = true;
        switch (arm) {
            case "rock":
                rock.setOpacity(1.0);
                paper.setOpacity(0.0);
                scissors.setOpacity(0.0);
                break;
            case "scissors":
                rock.setOpacity(0.0);
                paper.setOpacity(0.0);
                scissors.setOpacity(1.0);
                break;
            case "paper":
                rock.setOpacity(0.0);
                paper.setOpacity(1.0);
                scissors.setOpacity(0.0);
        }
    }

    public void setOpponentAction(String action) {
        armsOpacityControll(action, oppRock, oppPaper, oppScissors);
    }
}

