package com.example.jean_michel.projet_troisieme_annee.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jean_michel.projet_troisieme_annee.SQLiteHelper.TripHelper;
import com.example.jean_michel.projet_troisieme_annee.donnee.Record;
import com.example.jean_michel.projet_troisieme_annee.donnee.RoadType;
import com.example.jean_michel.projet_troisieme_annee.donnee.Trip;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;
import com.example.jean_michel.projet_troisieme_annee.donnee.Weather;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {

    private SQLiteDatabase database;
    private TripHelper dbHelper;
    private String[] allColumns = {TripHelper.COLUMN_ID,
            TripHelper.COLUMN_STARTED_DATE, TripHelper.COLUMN_FINISHED_DATE,
            TripHelper.COLUMN_WEATHER, TripHelper.COLUMN_ROAD_TYPE,
            TripHelper.COLUMN_USER_ID};

    private Context context;

    public TripDAO(Context context) {
        dbHelper = new TripHelper(context);
        this.context = context;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Trip createTrip(Trip trip) throws NullPointerException {
        ContentValues values = new ContentValues();
        values.put(TripHelper.COLUMN_STARTED_DATE, Trip.DATE_FORMAT.format(trip.getStartedDate()));
        values.put(TripHelper.COLUMN_FINISHED_DATE, Trip.DATE_FORMAT.format(trip.getFinishedDate()));
        values.put(TripHelper.COLUMN_WEATHER, trip.getWeather().toString());
        values.put(TripHelper.COLUMN_ROAD_TYPE, trip.getRoadType().toString());
        values.put(TripHelper.COLUMN_USER_ID, trip.getUser().getId());
        long insertId = database.insert(TripHelper.TABLE_TRIP, null,
                values);
        trip.setId((int) insertId);
        return trip;
    }

    public void deleteTrip(Trip user) {
        long id = user.getId();
        Log.d(TripDAO.class.getName(), "Trip deleted with id: " + id);
        database.delete(TripHelper.TABLE_TRIP, TripHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Trip> findAll() {
        List<Trip> trips = new ArrayList<>();
        Cursor cursor = database.query(TripHelper.TABLE_TRIP,
                allColumns, null, null, null, null, null);
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast())
            {
                Trip trip = cursorToTrip(cursor);
                trips.add(trip);
                // database.delete(TripHelper.TABLE_TRIP, TripHelper.COLUMN_ID
                //         + " = " + cursor.getInt(0), null);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return trips;
    }

    public List<Trip> findTripsByUserId(User user) {
        List<Trip> trips = new ArrayList<>();
        Cursor cursor = database.query(TripHelper.TABLE_TRIP,
                allColumns, TripHelper.COLUMN_USER_ID + "=" + user.getId(), null, null, null, null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Trip trip = cursorToTrip(cursor);
                trips.add(trip);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return trips;
    }

    private Trip cursorToTrip(Cursor cursor) {
        java.util.Date startedDateParsed = new Date(System.currentTimeMillis());
        java.util.Date finishedDateParsed = new Date(System.currentTimeMillis());
        try {
            startedDateParsed = Trip.DATE_FORMAT.parse(cursor.getString(1));
            finishedDateParsed = Trip.DATE_FORMAT.parse(cursor.getString(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Weather weather = Weather.valueOf(cursor.getString(3));
        RoadType roadType = RoadType.valueOf(cursor.getString(4));
        // RecordDAO recordDAO = new RecordDAO(context);
        List<Record> records = new ArrayList<>(); // recordDAO.getRecordsByTripId();
        UserDAO userDAO = new UserDAO(context);
        userDAO.open();
        User user = null;
        List<User> users = userDAO.findUserById(cursor.getInt(5));
        if(!users.isEmpty()){
            user = users.get(0);
        }
        userDAO.close();
        return new Trip(cursor.getInt(0), startedDateParsed, finishedDateParsed, weather, roadType, records, user);
    }

    public List<Trip> findTripById(int id){
        List<Trip> trips = new ArrayList<>();
        Cursor cursor = database.query(TripHelper.TABLE_TRIP,
                allColumns, TripHelper.COLUMN_ID + "=" + id, null, null, null, null);
        if(cursor.moveToFirst()) {
            Trip trip = cursorToTrip(cursor);
            trips.add(trip);
        }
        cursor.close();
        return trips;
    }
}
