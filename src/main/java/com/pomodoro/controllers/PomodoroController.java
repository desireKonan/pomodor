package com.pomodoro.controllers;

import com.pomodoro.models.PomodoroTask;
import com.pomodoro.models.PomodoroTaskObserver;
import com.pomodoro.models.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

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

    @FXML
    public StackPane stack;

    AudioClip audio;

    private PomodoroTask task;

    private PomodoroTaskObserver observer;

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
        observer = new PomodoroTaskObserver(labelWork, label, countWork, countBreak, new AudioClip(Paths.get("src/main/resources/com/pomodoro/media/bip-fin-session.mp3").toUri().toString()));
        task = new PomodoroTask(observer, Settings.getInstance());
    }

    public void onSettings(ActionEvent actionEvent) throws IOException {
       Parent settings = FXMLLoader.load(Paths.get("src/main/resources/com/pomodoro/settings.fxml").toUri().toURL());
       stack.getChildren().removeAll();
       stack.getChildren().setAll(settings);
    }
}