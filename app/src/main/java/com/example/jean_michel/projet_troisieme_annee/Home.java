package com.example.jean_michel.projet_troisieme_annee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jean_michel.projet_troisieme_annee.DAO.UserDAO;
import com.example.jean_michel.projet_troisieme_annee.donnee.Trip;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;

import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onResume(){
        super.onResume();
        UserDAO userDAO = new UserDAO(this);
        userDAO.open();
        /*User user = new User(1, "Billy", "Nicolas");
        userDAO.createUser(user);
        User user2 = new User(2, "SALETTE", "Cédric");
        userDAO.createUser(user2);
        User user3 = new User(3, "Prevost", "Amandine");
        userDAO.createUser(user3);*/
        List<User> users = userDAO.findAll();
        userDAO.close();
        User.connectedUser = users.get(0);
        List<Trip> trips = User.connectedUser.getTrips(this);



        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, users);
        spinner.setAdapter(adapter1);
    }

    public void createProfil(View view) {
        Intent intent = new Intent(this, CreationProfil.class);
        startActivity(intent);
    }


    public void startTrip(View view) {
        Intent intent = new Intent(this, Capture.class);
        startActivity(intent);
    }

    public void history(View view){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}
