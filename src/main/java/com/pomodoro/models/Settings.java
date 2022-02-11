package com.pomodoro.models;

public class Settings {

    private int period;

    private static Settings settings;

    private Settings(int period) {
        this.period = period;
    }

    private Settings() {
        this.period = 1000;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    //Charger de réccupérer la configuration de l'application.
    public static Settings getInstance() {
        if(settings == null) {
            settings = new Settings();
        }
        return settings;
    }

    //Charger de réccupérer la configuration de l'application.
    public static Settings getInstance(int period) {
        if(settings == null) {
            settings = new Settings(period);
        }
        return settings;
    }
}
