package com.pomodoro.models;

import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

public class PomodoroTaskObserver implements Observer {

    Label labelStatus, modeLabel, countWorkLabel, countBreakLabel;

    AudioClip audio;

    public PomodoroTaskObserver(Label label, Label mode, Label countWork, Label countBreak, AudioClip clip) {
        this.labelStatus = label;
        this.modeLabel = mode;
        this.countWorkLabel = countWork;
        this.countBreakLabel = countBreak;
        this.audio = clip;
    }


    @Override
    public void updateLabel(String label) {
        labelStatus.setText(label);
    }

    @Override
    public void updateMode(String mode) {
        modeLabel.setText(mode);
    }

    @Override
    public void updateCountBreak(String countBreak) {
        countBreakLabel.setText(countBreak);
    }

    @Override
    public void updateCountWork(String countWork) {
        countWorkLabel.setText(countWork);
    }

    @Override
    public void playMusic(String path) {
        playSound();
    }

    @Override
    public void reset(String value) {
        labelStatus.setText(value);
        modeLabel.setText("Travail");
        countWorkLabel.setText("0");
        countBreakLabel.setText("0");
    }

    private void playSound() {
        audio.play();
    }
}
