package com.project2.pomodoro;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;

import java.util.TimerTask;

public class PomodoroTask extends TimerTask {

    private static int tour = 1;
    private static int countWorkSession = 0, countBreakSession = 0;
    private static int workSessionDelay = 25, breakSessionDelay = 5;

    private static int secondes = 0, minutes = 0;

    private static String path = "";

    private Label workLabel, breakLabel, countWork, countBreak;

    private MediaPlayer mediaPlayer;

    public PomodoroTask(Label workLabel, Label breakLabel, Label countWork, Label countBreak) {
        this.workLabel = workLabel;
        this.breakLabel = breakLabel;
        this.countWork = countWork;
        this.countBreak = countBreak;
    }


    public static void setWorkSessionDelay(int workSessionDelay) {
        workSessionDelay = workSessionDelay;
    }

    public static void reset() {
        countBreakSession = 0;
        countWorkSession = 0;
        minutes = 0;
        secondes = 0;
        tour = 1;
        workSessionDelay = 25;
        breakSessionDelay = 5;
    }

    public static void setCountWorkSessionDelay(int countWorkSession) {
        countWorkSession = countWorkSession;
    }

    public static void setPath(String p) {
        path = p;
    }

    public static void setBreakSessionDelay(int breakSessionDelay) {
        breakSessionDelay = breakSessionDelay;
    }


    public static int getWorkSessionDelay() {
        return workSessionDelay;
    }

    public static int getBreakSessionDelay() {
        return breakSessionDelay;
    }

    public static int getSecondes() {
        return secondes;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static String getTime() {
        String time = "";
        if(minutes < 10)
            time += "0" + minutes;
        else
            time += minutes;

        if(secondes < 10)
            time += ": 0" + secondes;
        else
            time += ": " + secondes;
        return time;
    }


    @Override
    public void run() {
        Platform.runLater(() -> {
            //Chaque session qui est le multiple de 4 on modifie le délai.
            if(countBreakSession % 4 == 0 && countBreakSession != 0) {
                breakSessionDelay = 10;
            }
            if(tour == 1) {
                if(minutes >= workSessionDelay) {
                    //Si la minutes est supérieure à 25 par défaut alors...
                    minutes = 0;
                    secondes = 0;
                    countWorkSession++;
                    tour = 2;
                } else {
                    if(secondes < 59) {
                        secondes++;
                    } else {
                        secondes = 0;
                        minutes++;
                    }
                }
                System.out.println(getTime());
                this.workLabel.setText(getTime());
                this.countWork.setText(String.valueOf(countWorkSession));
            } else {
                if(minutes >= breakSessionDelay) {
                    //Si la minutes est supérieure à 25 par défaut alors...
                    minutes = 0;
                    secondes = 0;
                    countBreakSession++;
                    tour = 1;
                    breakSessionDelay = 5;
                } else {
                    if(secondes < 59) {
                        secondes++;
                    } else {
                        secondes = 0;
                        minutes++;
                    }
                }
                System.out.println(getTime());
                this.breakLabel.setText(getTime());
                this.countBreak.setText(String.valueOf(countBreakSession));
            }
        });
    }


    /*private void play() {
        System.out.println(path);
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
