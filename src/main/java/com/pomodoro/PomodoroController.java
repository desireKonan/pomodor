package com.pomodoro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroController implements Initializable {
    @FXML
    public Button reset;
    @FXML
    public Button stop;
    @FXML
    public Button start;
    @FXML
    public Label labelWork;
    @FXML
    public Label label;
    @FXML
    public Label countWork;
    @FXML
    public Label countBreak;

    private PomodoroTask task;

    AudioClip audio;

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
        //Met à jour l'interface.
        updateView();
    }

    /**
     * <p> Met à jour une tâche L'interface utilisateur. </p>
     */
    private void updateView() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //Mise à jour des écrans.
                Platform.runLater(() -> {
                    if(task.getClock().isTimingUp()) {
                        playSound(getClass().getResource("media/bip-fin-session.mp3").toString());
                    }
                    if(task.getClock().getMode().equals(PomodoroMode.WORK)) {
                        label.setText(task.getClock().getMode().toString());
                    } else {
                        label.setText(task.getClock().getMode().toString());
                    }
                    labelWork.setText(task.getClock().currentTime());
                    countWork.setText(task.getClock().getCountWork());
                    countBreak.setText(task.getClock().getCountBreak());
                });
            }
        }, 0, 10);
    }

    private void playSound(String filename) {
        if(audio == null) {
            audio = new AudioClip(filename);
        }
        audio.play();
    }


    public void exitApp(ActionEvent actionEvent) {
        System.exit(0);
    }
}