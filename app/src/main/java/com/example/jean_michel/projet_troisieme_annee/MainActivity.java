package com.example.jean_michel.projet_troisieme_annee;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static String getDeviceAddress() {
        return deviceAddress;
    }

    private static String deviceAddress = null;

    public static final String ENGINE_VALUE_CHANGED = "EngineValueChanged";
    public static final String SPEED_VALUE_CHANGED = "SpeedValueChanged";

    private static Handler handler;

    private TextView textViewEngineRPM;
    private TextView textViewSpeed;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewEngineRPM = (TextView)findViewById(R.id.textViewEngineRPM);
        textViewSpeed = (TextView)findViewById(R.id.textViewSpeed);
        handler = new Handler(){
          @Override
          public void handleMessage(Message message){
              String engineValue = message.getData().getString(ENGINE_VALUE_CHANGED);
              if(engineValue != null) {
                  textViewEngineRPM.setText(engineValue);
              }
              String speedValue = message.getData().getString(SPEED_VALUE_CHANGED);
              if(speedValue != null) {
                  textViewSpeed.setText(speedValue);
              }
          }
        };
        obd2();
    }

    private void obd2()
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
                Thread thread = new Thread( new CaptureVehiculeData(handler));
                thread.start();
            }
        });

        alertDialog.setTitle("Choose OBDII device already paired");
        alertDialog.show();
    }
}
