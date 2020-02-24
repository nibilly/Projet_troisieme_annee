package com.example.mobilitycapture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class capture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
    }


    public void stop(View view){
        Intent intent = new Intent(this, selection.class);
        startActivity(intent);
    }

}
