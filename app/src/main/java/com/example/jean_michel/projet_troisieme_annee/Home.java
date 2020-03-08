package com.example.jean_michel.projet_troisieme_annee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jean_michel.projet_troisieme_annee.donnee.User;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<User> listUser = new ArrayList<>();

        User user = new User(1, "Billy", "Nicolas");
        listUser.add(user);
        User user2 = new User(2, "SALETTE", "Cédric");
        listUser.add(user2);
        User user3 = new User(3, "Prevost", "Amandine");
        listUser.add(user3);

        ArrayAdapter<User> adapter1 = new ArrayAdapter<User>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, listUser);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
    }

    public void createProfil(View view) {
        Intent intent = new Intent(this, CreationProfil.class);
        startActivity(intent);
    }

    public void startTrip(View view) {
        Log.i("cedric", "here");
        Intent intent = new Intent(this, Capture.class);
        Log.i("cedric", "here2");
        startActivity(intent);
        Log.i("cedric", "here3");
    }

    public void history(View view) {
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }
}
