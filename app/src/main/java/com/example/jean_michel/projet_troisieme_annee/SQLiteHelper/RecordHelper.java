package com.example.jean_michel.projet_troisieme_annee.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RecordHelper extends SQLiteOpenHelper {

    public static final String TABLE_RECORD = "record";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ENGINE_RPM = "engineRPM";
    public static final String COLUMN_SPEED = "speed";
    public static final String COLUMN_DISTANCE= "distance";
    public static final String COLUMN_TRIP_ID = "tripId";

    private static final  String DATABASE_NAME = "record.db";
    private static final  int DATABASE_VERSION = 1;

    // Command sql for bdd creation
    private static final String DATABASE_CREATE = "create table " + TABLE_RECORD + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DATE + " text not null, "
            + COLUMN_ENGINE_RPM + " integer not null,"
            + COLUMN_SPEED + " integer not null, "
            + COLUMN_DISTANCE + " integer not null, "
            + COLUMN_TRIP_ID + " integer not null, "
            + "FOREIGN KEY(" + COLUMN_TRIP_ID +") REFERENCES "+ TripHelper.TABLE_TRIP + "(" + TripHelper.COLUMN_ID + "));";

    public RecordHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(RecordHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }
}
