package com.pomodoro;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTask {

    private PomodoroClock clock;
    private Timer timer;

    private boolean isRunning = false;

    public PomodoroTask(PomodoroClock clock, Timer timer) {
        this.clock = clock;
        this.timer = timer;
    }

    public PomodoroTask() {
        this.clock = new PomodoroClock();
        this.timer = new Timer();
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
                //Modèle qui édite le chronomètre (montre pomodoro).
                clock.startTime();
            }
        }, 0, 10);
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
    }
}
