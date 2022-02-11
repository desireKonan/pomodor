package com.pomodoro.models;

public class PomodoroClock {
    //Compteur des sessions de travail et de pause.
    private static int countWork = 0, countBreak = 0;

    //Temps en secondes.
    private int time = 0;

    PomodoroMode mode;

    public PomodoroClock(int time) {
        this.time = time;
    }

    public PomodoroClock() {
        this.time = PomodoroMode.WORK.getSecondes();
        this.mode = PomodoroMode.WORK;
    }

    public PomodoroMode getMode() {
        return mode;
    }

    public int getTime() {
        return time;
    }

    public String getCountBreak() {
        return String.valueOf(countBreak);
    }

    public String getCountWork() {
        return String.valueOf(countWork);
    }

    //Retourne le temps actuel.
    public String currentTime() {
        int minutes = this.time / 60;
        int secondes = this.time % 60;
        return String.format("%02d : %02d", minutes, secondes);
    }

    public void setMode(PomodoroMode mode) {
        this.mode = mode;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isTimingUp() {
        return time == 0;
    }

    public void start() {
        this.time--;
    }

    public static void addWorkCount() {
        countWork++;
    }

    public static void addBreakCount() {
        countBreak++;
    }

    public static void resetWork() {
        countWork++;
    }

    public static void resetBreak() {
        countBreak++;
    }

    //Démarre la montre pomodoro.
    public void startTime() {
        if(isTimingUp()) {
            //Si le temps est 0, on réinitialise la montre.
            this.setTurn();
            time = this.getMode().getSecondes();
            if (this.mode.equals(PomodoroMode.WORK)) {
                countWork++;
            } else {
                countBreak++;
            }
        } else {
            time--;
            System.out.println(currentTime());
        }
    }

    //Réinistialise la montre Pomodoro.
    public void reset() {
        this.setMode(PomodoroMode.WORK);
        this.time = this.getMode().getSecondes();
        countWork = 0;
        countBreak = 0;
    }


    public void setTurn() {
        if (this.mode.equals(PomodoroMode.WORK)) {
            if(countBreak % 4 == 0 && countBreak != 0) {
                this.setMode(PomodoroMode.LONG_BREAK);
            } else {
                this.setMode(PomodoroMode.SHORT_BREAK);
            }
        } else {
            this.setMode(PomodoroMode.WORK);
        }
    }
}
