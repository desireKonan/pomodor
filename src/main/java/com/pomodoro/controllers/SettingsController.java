package com.pomodoro.controllers;

import com.pomodoro.models.PomodoroMode;
import com.pomodoro.models.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    public Spinner work;
    @FXML
    public Spinner shortBreak;
    @FXML
    public Spinner longBreak;
    @FXML
    public Spinner period;
    @FXML
    public StackPane stack;

    Settings settings;

    @FXML
    public void onBack(ActionEvent actionEvent) throws IOException {
        Parent settings = FXMLLoader.load(Paths.get("src/main/resources/com/pomodoro/pomodoro-app.fxml").toUri().toURL());
        stack.getChildren().removeAll();
        stack.getChildren().setAll(settings);
    }



    @FXML
    public void saveSettings(ActionEvent actionEvent) {
        System.out.println(settings.getPeriod());
        settings.setPeriod(Integer.parseInt(period.getEditor().getText()));
        PomodoroMode.WORK.setMinutes(Integer.parseInt(work.getEditor().getText()));
        PomodoroMode.SHORT_BREAK.setMinutes(Integer.parseInt(shortBreak.getEditor().getText()));
        PomodoroMode.LONG_BREAK.setMinutes(Integer.parseInt(longBreak.getEditor().getText()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Paramètres changés avec succès ! ");

        System.out.println(PomodoroMode.WORK.getMinutes());
        alert.show();
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settings = Settings.getInstance();
        work.getEditor().setText(String.valueOf(PomodoroMode.WORK.getMinutes()));
        shortBreak.getEditor().setText(String.valueOf(PomodoroMode.SHORT_BREAK.getMinutes()));
        longBreak.getEditor().setText(String.valueOf(PomodoroMode.LONG_BREAK.getMinutes()));
        period.getEditor().setText(String.valueOf(settings.getPeriod()));
    }
}
