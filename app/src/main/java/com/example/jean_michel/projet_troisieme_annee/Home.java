package com.example.jean_michel.projet_troisieme_annee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jean_michel.projet_troisieme_annee.DAO.RecordDAO;
import com.example.jean_michel.projet_troisieme_annee.DAO.TripDAO;
import com.example.jean_michel.projet_troisieme_annee.DAO.UserDAO;
import com.example.jean_michel.projet_troisieme_annee.donnee.Record;
import com.example.jean_michel.projet_troisieme_annee.donnee.RoadType;
import com.example.jean_michel.projet_troisieme_annee.donnee.Trip;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;
import com.example.jean_michel.projet_troisieme_annee.donnee.Weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Date d = new Date();

        // Test
        UserDAO userDAO = new UserDAO(this);
        userDAO.open();
        List<User> users = userDAO.findAll();
        userDAO.close();
        User.connectedUser = users.get(0);
        List<Trip> trips = User.connectedUser.getTrips(this);
        List<Record> records = trips.get(0).getRecords(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.users, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        /*UserDAO userDAO = new UserDAO(this);
        userDAO.open();
        List<User> users = userDAO.findAll();
        userDAO.close();
        User user = users.get(0);
        List<Trip> trips = user.getTrips(this);
        Trip trip = trips.get(0);
        List<Record> records = trip.getRecords(this);*/
    }

    public void createProfil(View view) {
        Intent intent = new Intent(this, CreationProfil.class);
        startActivity(intent);
    }


    public void startTrip(View view) {
        Intent intent = new Intent(this, Capture.class);
        startActivity(intent);
    }
}
