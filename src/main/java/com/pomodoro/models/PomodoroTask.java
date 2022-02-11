package com.pomodoro.models;

import javafx.application.Platform;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTask {

    private PomodoroClock clock;
    private Timer timer;

    //Observer de la tâche actuelle.
    private PomodoroTaskObserver observer;

    private Settings setting;

    private boolean isRunning = false;

    public PomodoroTask(PomodoroClock clock, Timer timer, PomodoroTaskObserver observer) {
        this.clock = clock;
        this.timer = timer;
        this.observer = observer;
    }

    public PomodoroTask(PomodoroTaskObserver observer, Settings setting) {
        this.clock = new PomodoroClock();
        this.timer = new Timer();
        this.observer = Objects.requireNonNull(observer);
        this.setting = setting;
        //Réinitialise la position de départ.
        observer.reset(clock.currentTime());
    }


    public PomodoroClock getClock() {
        return clock;
    }

    public void setClock(PomodoroClock clock) {
        this.clock = clock;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     *  <p> Lance une tâche Pomodoro (Timer) </p>
     */
    public void start() {
        if (!isRunning) {
            this.timer = new Timer();
            isRunning = true;
        }
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    //Modèle qui édite le chronomètre (montre pomodoro).
                    if(clock.isTimingUp()) {
                        //Si le temps est 0, on réinitialise la montre.
                        clock.setTurn();
                        clock.setTime(clock.getMode().getSecondes());
                        if (clock.mode.equals(PomodoroMode.WORK)) {
                            PomodoroClock.addWorkCount();
                            observer.updateCountWork(clock.getCountWork());
                        } else {
                            PomodoroClock.addBreakCount();
                            observer.updateCountBreak(clock.getCountBreak());
                        }
                        observer.playMusic("media/bip-fin-session.mp3");
                    } else {
                        clock.start();
                        System.out.println(clock.currentTime());
                        observer.updateMode(clock.getMode().toString());
                        observer.updateLabel(clock.currentTime());
                    }
                });
            }
        }, 0, this.setting.getPeriod());
    }

    /**
     *  <p> Stop une tâche Pomodoro (Timer) </p>
     */
    public void stop() {
        isRunning = false;
        this.timer.cancel();
    }

    /**
     *  <p> Réinitialise une tâche Pomodoro (Timer) </p>
     */
    public void reset() {
        isRunning = false;
        this.timer.cancel();
        this.getClock().reset();
        observer.reset(clock.currentTime());
    }
}
