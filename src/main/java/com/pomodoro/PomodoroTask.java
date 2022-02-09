package com.pomodoro;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTask {

    private PomodoroClock clock;
    private Timer timer;

    private boolean isRunning;

    public PomodoroTask(PomodoroClock clock, Timer timer, boolean isRunning) {
        this.clock = clock;
        this.timer = timer;
        isRunning = false;
    }

    public PomodoroTask() {
        this.clock = new PomodoroClock();
        this.timer = new Timer();
        isRunning = false;
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

    public void start() {
        runOrResume();
    }

    public void stop() {
        isRunning = false;
        this.timer.cancel();
        this.timer.purge();
    }

    public void reset() {
        isRunning = false;
        timer.cancel();
        this.getClock().reset();
    }

    private void runOrResume() {
        if (!isRunning) {
            this.timer = new Timer();
        }
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                clock.startTime();
            }
        }, 0, 1000);
        isRunning = true;
    }
}
