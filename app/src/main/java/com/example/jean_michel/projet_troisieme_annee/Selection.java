package com.example.jean_michel.projet_troisieme_annee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.jean_michel.projet_troisieme_annee.DAO.RecordDAO;
import com.example.jean_michel.projet_troisieme_annee.donnee.Record;
import com.example.jean_michel.projet_troisieme_annee.donnee.RoadType;
import com.example.jean_michel.projet_troisieme_annee.donnee.Trip;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;
import com.example.jean_michel.projet_troisieme_annee.donnee.Weather;

import java.util.ArrayList;
import java.util.List;

public class Selection extends AppCompatActivity {

    private Weather weather;
    private RoadType roadType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }

    public void save(View view){
        if(weather == null || roadType == null){
            Toast toast = Toast.makeText(this, "Please select first", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Trip trip = Capture.trip;
            trip.setWeather(weather);
            trip.setRoadType(roadType);
            trip = User.connectedUser.addTrip(trip, this);
            List<Record> records = Capture.records;
            for (Record record: records) {
                record.setTrip(trip);
                trip.addRecord(record, this);
            }
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
    }

    public void test(View view) {
        // TextView textView
        this.finish();
    }

    public void roadType1(View view){
        TextView roadType1 = findViewById(R.id.roadType1);
        TextView roadType2 = findViewById(R.id.roadType2);
        TextView roadType3 = findViewById(R.id.roadType3);
        TextView roadType4 = findViewById(R.id.roadType4);
        TextView roadType5 = findViewById(R.id.roadType5);

        roadType1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.roadType = RoadType.HIGHWAY;
    }


    public void roadType2(View view){
        TextView roadType1 = findViewById(R.id.roadType1);
        TextView roadType2 = findViewById(R.id.roadType2);
        TextView roadType3 = findViewById(R.id.roadType3);
        TextView roadType4 = findViewById(R.id.roadType4);
        TextView roadType5 = findViewById(R.id.roadType5);

        roadType1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.roadType = RoadType.DEPARTMENT;
    }


    public void roadType3(View view){
        TextView roadType1 = findViewById(R.id.roadType1);
        TextView roadType2 = findViewById(R.id.roadType2);
        TextView roadType3 = findViewById(R.id.roadType3);
        TextView roadType4 = findViewById(R.id.roadType4);
        TextView roadType5 = findViewById(R.id.roadType5);

        roadType1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.roadType = RoadType.NATIONAL;
    }


    public void roadType4(View view){
        TextView roadType1 = findViewById(R.id.roadType1);
        TextView roadType2 = findViewById(R.id.roadType2);
        TextView roadType3 = findViewById(R.id.roadType3);
        TextView roadType4 = findViewById(R.id.roadType4);
        TextView roadType5 = findViewById(R.id.roadType5);

        roadType1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.roadType = RoadType.DIRT;
    }


    public void roadType5(View view){
        TextView roadType1 = findViewById(R.id.roadType1);
        TextView roadType2 = findViewById(R.id.roadType2);
        TextView roadType3 = findViewById(R.id.roadType3);
        TextView roadType4 = findViewById(R.id.roadType4);
        TextView roadType5 = findViewById(R.id.roadType5);

        roadType1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        roadType5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.roadType = RoadType.CITY;
    }


    public void weather1(View view) {
        TextView weather1 = findViewById(R.id.weather1);
        TextView weather2 = findViewById(R.id.weather2);
        TextView weather3 = findViewById(R.id.weather3);
        TextView weather4 = findViewById(R.id.weather4);

        weather1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.weather = Weather.SUN;
    }

    public void weather2(View view) {
        TextView weather1 = findViewById(R.id.weather1);
        TextView weather2 = findViewById(R.id.weather2);
        TextView weather3 = findViewById(R.id.weather3);
        TextView weather4 = findViewById(R.id.weather4);

        weather1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.weather = Weather.SNOW;
    }

    public void weather3(View view) {
        TextView weather1 = findViewById(R.id.weather1);
        TextView weather2 = findViewById(R.id.weather2);
        TextView weather3 = findViewById(R.id.weather3);
        TextView weather4 = findViewById(R.id.weather4);

        weather1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.weather = Weather.RAIN;
    }

    public void weather4(View view) {
        TextView weather1 = findViewById(R.id.weather1);
        TextView weather2 = findViewById(R.id.weather2);
        TextView weather3 = findViewById(R.id.weather3);
        TextView weather4 = findViewById(R.id.weather4);

        weather1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionVide));
        weather4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectionPleine));
        this.weather = Weather.CLOUD;
    }
}
