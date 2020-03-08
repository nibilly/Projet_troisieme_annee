package com.example.jean_michel.projet_troisieme_annee.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TripHelper extends SQLiteOpenHelper {

    public static final String TABLE_TRIP = "trip";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STARTED_DATE = "startedDate";
    public static final String COLUMN_FINISHED_DATE = "finishedDate";
    public static final String COLUMN_WEATHER = "weather";
    public static final String COLUMN_ROAD_TYPE = "roadType";
    public static final String COLUMN_USER_ID = "userId";

    private static final  String DATABASE_NAME = "trip.db";
    private static final  int DATABASE_VERSION = 1;

    // Command sql for bdd creation
    private static final String DATABASE_CREATE = "create table " + TABLE_TRIP + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_STARTED_DATE + " text not null, "
            + COLUMN_FINISHED_DATE + " text not null,"
            + COLUMN_WEATHER + " text not null, "
            + COLUMN_ROAD_TYPE + " text not null, "
            + COLUMN_USER_ID + " integer not null, "
            + "FOREIGN KEY(" + COLUMN_USER_ID +") REFERENCES "+ UserHelper.TABLE_USER + "(" + UserHelper.COLUMN_ID + "));";

    public TripHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TripHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);
        onCreate(sqLiteDatabase);
    }
}
