package com.example.jean_michel.projet_troisieme_annee.donnee;

import android.content.Context;
import android.view.contentcapture.ContentCaptureCondition;

import com.example.jean_michel.projet_troisieme_annee.DAO.RecordDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Trip {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private int id;
    private Date startedDate;
    private Date finishedDate;
    private Weather weather;
    private RoadType roadType;

    private List<Record> records;

    private User user;

    // BDD recuperation
    public Trip(int id, Date startedDate, Date finishedDate, Weather weather, RoadType roadType, List<Record> records, User user) {
        this.id = id;
        this.startedDate = startedDate;
        this.finishedDate = finishedDate;
        this.weather = weather;
        this.roadType = roadType;
        this.records = records;
        this.user = user;
    }

    // New one
    public Trip(Date startedDate, User user) {
        this.startedDate = startedDate;
        this.user = user;
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

    public List<Record> getRecords(Context context) {
        RecordDAO recordDAO = new RecordDAO(context);
        recordDAO.open();
        List<Record> records1 = recordDAO.findRecordsByTripId(this);
        recordDAO.close();
        records = records1;
        return records;
    }

    // Create record in BDD
    public Record addRecord(Record record, Context context) {
        RecordDAO recordDAO = new RecordDAO(context);
        recordDAO.open();
        Record record1 = recordDAO.createRecord(record);
        recordDAO.close();
        this.records.add(record1);
        return record1;
    }

    @Override
    public String toString(){
        String str = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        str += format.format(this.startedDate) + " ";
        long ll = finishedDate.getTime() - startedDate.getTime();
        int l = (int) ll;
        str += l/3600000;
        str += "h";
        l = l%3600000;
        str += l/60000;
        str += "m";
        l = l%60000;
        str += l/1000;
        str += "s  avgSpeed:";
        int avgSpeed = 0;
        int avgRPM = 0;
        for(Record record : records){
            avgSpeed += record.getSpeed();
            avgRPM += record.getEngineRPM();
        }
        avgSpeed /= records.size();
        avgRPM /= records.size();
        str += avgSpeed + "km/h avgEngineRPM:" + avgRPM + "RPM";
        return str;
    }

    public User getUser() {
        return user;
    }
}
