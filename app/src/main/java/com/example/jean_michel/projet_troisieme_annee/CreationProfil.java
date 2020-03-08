package com.example.jean_michel.projet_troisieme_annee;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jean_michel.projet_troisieme_annee.R;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreationProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_profil);

        List<User> listUser = new ArrayList<>();

        User user = new User(1, "Billy", "Nicolas");
        listUser.add(user);
    }

    public void enregistrer(View view) {
        this.finish();
    }

    public void cancel(View view) {
        this.finish();
    }
}
