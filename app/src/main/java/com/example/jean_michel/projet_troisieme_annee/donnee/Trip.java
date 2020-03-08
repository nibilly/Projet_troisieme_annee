package com.example.jean_michel.projet_troisieme_annee.donnee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private int id;
    private Date startedDate;
    private Date finishedDate;
    private Weather weather;
    private RoadType roadType;

    private List<Record> records;

    // BDD recuperation
    public Trip(int id, Date startedDate, Date finishedDate, Weather weather, RoadType roadType, List<Record> records) {
        this.id = id;
        this.startedDate = startedDate;
        this.finishedDate = finishedDate;
        this.weather = weather;
        this.roadType = roadType;
        this.records = records;
    }

    // New one
    public Trip(Date startedDate) {
        this.startedDate = startedDate;
        this.records = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {this.startedDate = startedDate;}

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) {
        this.records.add(record);
    }
}
