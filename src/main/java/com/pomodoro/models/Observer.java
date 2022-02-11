package com.pomodoro.models;

public interface Observer {
    public void updateLabel(String label);
    public void updateMode(String mode);
    public void updateCountBreak(String countBreak);
    public void updateCountWork(String countWork);
    public void playMusic(String path);
    public void reset(String value);
}
