package com.example.jean_michel.projet_troisieme_annee;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CreationProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_profil);
    }

    public void enregistrer(View view) {
        this.finish();
    }

    public void cancel(View view) {
        this.finish();
    }
}
