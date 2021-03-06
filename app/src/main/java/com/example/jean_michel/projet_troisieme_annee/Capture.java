package com.example.jean_michel.projet_troisieme_annee;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jean_michel.projet_troisieme_annee.donnee.Record;
import com.example.jean_michel.projet_troisieme_annee.donnee.Trip;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Capture extends AppCompatActivity {

    // Message types for the handler
    public static final String ENGINE_VALUE_CHANGED = "EngineValueChanged";
    public static final String SPEED_VALUE_CHANGED = "SpeedValueChanged";
    public static final String SOCKET_CONNECTED = "SocketConnected";

    // Communication between threads
    private static Handler handler;
    private Thread thread;

    // Adresse de l appareil bluetooth obd2Selection
    private static String deviceAddress = null;
    public static String getDeviceAddress() {
        return deviceAddress;
    }

    private TextView textViewEngineRPM;
    private TextView textViewSpeed;
    private Button buttonStop;
    private ProgressBar progressBarConnexion;

    public static Trip trip;
    public static List<Record> records;
    private Record record;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        if(deviceAddress == null) {
            obd2Selection();
        }
        else {
            startRecup();
        }

        trip = new Trip(new Date(), User.connectedUser);
        records = new ArrayList<>();

        // MessageQueue on the handler to wait vehicule information
        textViewEngineRPM = (TextView)findViewById(R.id.textViewEngineRPM);
        textViewSpeed = (TextView)findViewById(R.id.textViewSpeed);
        buttonStop = (Button)findViewById(R.id.buttonStop);
        progressBarConnexion = (ProgressBar) findViewById(R.id.progressBarConnexion);
        buttonStop.setEnabled(false);
        progressBarConnexion.setVisibility(View.VISIBLE);
        handler = new Handler(){
            @Override
            public void handleMessage(Message message){
                String engineValue = message.getData().getString(ENGINE_VALUE_CHANGED);
                if(engineValue != null) {
                    textViewEngineRPM.setText(engineValue+" RPM");
                    record = new Record(new Date(), Integer.parseInt(engineValue), trip);
                }
                else{
                    String speedValue = message.getData().getString(SPEED_VALUE_CHANGED);
                    if(speedValue != null) {
                        textViewSpeed.setText(speedValue+" km/h");
                        int speedInt = Integer.parseInt(speedValue);
                        record.setSpeed(speedInt);
                        record.setDistance(speedInt/3600);
                        records.add(record);
                    }
                    else{
                        if(message.getData().getBoolean(SOCKET_CONNECTED, false)){
                            buttonStop.setEnabled(true);
                            progressBarConnexion.setVisibility(View.GONE);
                        }
                    }
                }
            }
        };
        // buttonStop.setEnabled(true);
        // progressBarConnexion.setVisibility(View.GONE);
    }

    public void stop(View view){
        // Stop thread of recuperation
        thread.interrupt();

        trip.setFinishedDate(new Date());
        Intent intent = new Intent(this, Selection.class);
        startActivity(intent);
    }

    // Selection de l appareil obd2Selection dans la liste des appareils bluetooth
    private void obd2Selection()
    {
        ArrayList<String> deviceStrs = new ArrayList<>();
        final ArrayList<String> devices = new ArrayList<>();

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0)
        {
            for (BluetoothDevice device : pairedDevices)
            {
                deviceStrs.add(device.getName() + "\n" + device.getAddress());
                devices.add(device.getAddress());
            }
        }

        // show list
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        ArrayAdapter<Object> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice,
                deviceStrs.toArray(new Object[0]));

        alertDialog.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                deviceAddress = devices.get(position);
                startRecup();
            }
        });

        alertDialog.setTitle("Choose OBDII device already paired");
        alertDialog.show();
    }

    private void startRecup(){
        thread = new Thread( new CaptureVehicleData(handler));
        thread.start();
    }
}
