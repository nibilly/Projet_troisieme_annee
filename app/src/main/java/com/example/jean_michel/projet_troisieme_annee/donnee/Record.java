package com.example.jean_michel.projet_troisieme_annee.donnee;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Record {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private int id;
    // DD/MM/YYYY hh:mm:ss
    private Date date;
    private int engineRPM;
    private int speed;
    private int distance;

    private Trip trip;

    // BDD recuperation
    public Record(int id, Date date, int engineRPM, int speed, int distance, Trip trip) {
        this.id = id;
        this.date = date;
        this.engineRPM = engineRPM;
        this.speed = speed;
        this.distance = distance;
        this.trip = trip;
    }

    // New one
    public Record(Date date, int engineRPM, Trip trip) {
        this.date = date;
        this.engineRPM = engineRPM;
        this.trip = trip;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
