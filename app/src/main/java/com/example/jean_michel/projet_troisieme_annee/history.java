package com.example.jean_michel.projet_troisieme_annee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jean_michel.projet_troisieme_annee.donnee.Record;

import java.sql.Date;

public class history extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView myListView;
    private ListView myListViewRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        myListView = findViewById(R.id.listView);
        myListViewRecords = findViewById(R.id.listViewRecords);
        listViewRecords();
        listView();
    }

    public void listViewRecords(){
        // Record record1 = new Record(1, new Date(), 25, 50, 125);
    }

    public void listView() {
        String[] prenoms = new String[]{"Cedric", "Nicolas", "Amandine", "QUENTIN"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prenoms);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(getApplicationContext(), "L'index de cet element est : " + position, Toast.LENGTH_SHORT).show();
    }
}
