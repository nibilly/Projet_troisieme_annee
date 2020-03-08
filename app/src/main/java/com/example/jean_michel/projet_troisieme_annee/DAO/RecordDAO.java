package com.example.jean_michel.projet_troisieme_annee.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jean_michel.projet_troisieme_annee.SQLiteHelper.RecordHelper;
import com.example.jean_michel.projet_troisieme_annee.donnee.Record;
import com.example.jean_michel.projet_troisieme_annee.donnee.Trip;

import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// A tester
public class RecordDAO {

    private Context context;

    private SQLiteDatabase database;
    private RecordHelper dbHelper;
    private String[] allColumns = {
            RecordHelper.COLUMN_ID,
            RecordHelper.COLUMN_DATE,
            RecordHelper.COLUMN_ENGINE_RPM,
            RecordHelper.COLUMN_SPEED,
            RecordHelper.COLUMN_DISTANCE,
            RecordHelper.COLUMN_TRIP_ID};

    public RecordDAO(Context context) {
        dbHelper = new RecordHelper(context);
        this.context = context;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Record createRecord(Record record) {
        ContentValues values = new ContentValues();
        values.put(RecordHelper.COLUMN_DATE, Record.DATE_FORMAT.format(record.getDate()));
        values.put(RecordHelper.COLUMN_ENGINE_RPM, record.getEngineRPM());
        values.put(RecordHelper.COLUMN_SPEED, record.getSpeed());
        values.put(RecordHelper.COLUMN_DISTANCE, record.getDistance());
        values.put(RecordHelper.COLUMN_TRIP_ID, record.getTrip().getId());
        long insertId = database.insert(RecordHelper.TABLE_RECORD, null,
                values);
        Cursor cursor = database.query(RecordHelper.TABLE_RECORD,
                new String[]{RecordHelper.COLUMN_ID}, RecordHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        if(cursor.moveToFirst()) {
            record.setId(cursor.getInt(0));
        }
        cursor.close();
        return record;
    }

    public void deleteRecord(Record record) {
        long id = record.getId();
        Log.d(RecordDAO.class.getName(), "Record deleted with id: " + id);
        database.delete(RecordHelper.TABLE_RECORD, RecordHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Record> findAll() {
        List<Record> records = new ArrayList<>();
        Cursor cursor = database.query(RecordHelper.TABLE_RECORD,
                allColumns, null, null, null, null, null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                Record record = CursorToRecord(cursor);
                records.add(record);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return records;
    }

    private Record CursorToRecord(Cursor cursor) {
        int id = cursor.getInt(0);
        Date date = new Date(System.currentTimeMillis());
        try {
            date = Record.DATE_FORMAT.parse(cursor.getString(1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int engineRPM = cursor.getInt(2);
        int speed = cursor.getInt(3);
        int distance = cursor.getInt(4);
        TripDAO tripDAO = new TripDAO(context);
        tripDAO.open();
        Trip trip = null;
        List<Trip> trips = tripDAO.findTripById(cursor.getInt(5));
        if(!trips.isEmpty()){
            trip = trips.get(0);
        }
        tripDAO.close();
        return new Record(id, date, engineRPM, speed, distance, trip);
    }

    public List<Record> findRecordsByTripId(Trip trip) {
        List<Record> records = new ArrayList<>();
        Cursor cursor = database.query(RecordHelper.TABLE_RECORD,
                allColumns, RecordHelper.COLUMN_TRIP_ID + "=" + trip.getId(), null, null, null, null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Record record = CursorToRecord(cursor);
                records.add(record);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return records;

    }
}
