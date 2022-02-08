package com.project2.pomodoro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class HelloController implements Initializable {

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
    @FXML
    public MenuBar menuBar;

    private Timer timer;

    private boolean isStop = false;

    @FXML
    public void startTimer(ActionEvent actionEvent) {
        if(isStop) {
            timer = new Timer();
        } else {
            timer.purge();
        }
        timer.scheduleAtFixedRate(new PomodoroTask(sessionWorkTimer, sessionBreakTimer, countWork, countBreak), 0, 10);

    }

    @FXML
    public void stopTimer(ActionEvent actionEvent) {
        isStop = true;
        timer.cancel();
    }

    @FXML
    public void resetTimer(ActionEvent actionEvent) {
        timer.cancel();
        PomodoroTask.reset();
        sessionWorkTimer.setText("00 : 00");
        sessionBreakTimer.setText("00 : 00");
        countWork.setText("0");
        countBreak.setText("0");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timer = new Timer();
        this.sessionWorkTimer.setText("00 : 00");
        this.sessionBreakTimer.setText("00 : 00");
    }
}