package com.example.jean_michel.projet_troisieme_annee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jean_michel.projet_troisieme_annee.donnee.Record;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;

import java.sql.Date;

public class History extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView myListView;
    private ListView myListViewTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    @Override
    public void onResume(){
        super.onResume();
        myListView = findViewById(R.id.listView);
        listView();
    }


    public void listView() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, User.connectedUser.getTrips(this));
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(getApplicationContext(), "L'index de cet element est : " + position, Toast.LENGTH_SHORT).show();
    }
}
