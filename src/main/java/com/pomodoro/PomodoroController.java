package com.pomodoro;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class  PomodoroController implements Initializable {

    @FXML
    public Label sessionWorkTimer;
    @FXML
    public Label sessionBreakTimer;
    @FXML
    public Button reset;
    @FXML
    public Button stop;
    @FXML
    public Button start;
    @FXML
    public Label countWork;
    @FXML
    public Label countBreak;

    private PomodoroTask task;

    @FXML
    public void startTimer(ActionEvent actionEvent) {
        task.start();
    }

    @FXML
    public void stopTimer(ActionEvent actionEvent) {
        task.stop();
    }

    @FXML
    public void resetTimer(ActionEvent actionEvent) {
        task.reset();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        task = new PomodoroTask();
        this.sessionWorkTimer.setText("25 : 00");
        this.sessionBreakTimer.setText("05 : 00");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //Mise à jour des écrans.
                Platform.runLater(() -> {
                    if(task.getClock().getMode().equals(PomodoroMode.WORK)) {
                        sessionWorkTimer.textProperty().bind(new SimpleStringProperty(task.getClock().currentTime()));
                    } else {
                        sessionBreakTimer.textProperty().bind(new SimpleStringProperty(task.getClock().currentTime()));
                    }
                    countWork.textProperty().bind(new SimpleStringProperty(task.getClock().getCountWork()));
                    countBreak.textProperty().bind(new SimpleStringProperty(task.getClock().getCountBreak()));
                });
            }
        }, 0, 1000);
    }
}