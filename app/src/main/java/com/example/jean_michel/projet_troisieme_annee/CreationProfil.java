package com.example.jean_michel.projet_troisieme_annee;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jean_michel.projet_troisieme_annee.DAO.UserDAO;
import com.example.jean_michel.projet_troisieme_annee.R;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreationProfil extends AppCompatActivity {

    public UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_profil);
        userDAO = new UserDAO(getApplicationContext());

    }

    public void enregistrer(View view) {
        EditText prenom = findViewById(R.id.prenom);
        EditText nom = findViewById(R.id.name);
        User user = new User( nom.getText().toString(), prenom.getText().toString());
        userDAO.open();
        userDAO.createUser(user);
        userDAO.close();
        this.finish();
    }

    public void cancel(View view) {
        this.finish();
    }
}
