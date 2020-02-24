package com.example.mobilitycapture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class creationProfil extends AppCompatActivity {

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
