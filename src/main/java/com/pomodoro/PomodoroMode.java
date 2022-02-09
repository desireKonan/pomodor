package com.pomodoro;

public enum PomodoroMode {
    WORK(25),
    SHORT_BREAK(5),
    LONG_BREAK(10);

    //Temps en secondes.
    private int minutes;

    PomodoroMode(int minutes) {
        this.minutes = minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSecondes() {
        return minutes * 60;
    }
}
