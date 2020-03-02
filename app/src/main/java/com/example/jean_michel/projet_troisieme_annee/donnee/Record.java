package com.example.jean_michel.projet_troisieme_annee.donnee;

import java.sql.Date;

public class Record {
    private int id;
    // DD/MM/YYYY hh:mm:ss
    private Date date;
    private int engineRPM;
    private int speed;
    private int distance;

    // BDD recuperation
    public Record(int id, Date date, int engineRPM, int speed, int distance) {
        this.id = id;
        this.date = date;
        this.engineRPM = engineRPM;
        this.speed = speed;
        this.distance = distance;
    }

    // New one
    public Record(Date date, int engineRPM, int speed, int distance) {
        this.date = date;
        this.engineRPM = engineRPM;
        this.speed = speed;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public int getEngineRPM() {
        return engineRPM;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDistance() {
        return distance;
    }
}
