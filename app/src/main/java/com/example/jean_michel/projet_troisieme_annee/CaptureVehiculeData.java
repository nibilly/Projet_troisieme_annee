package com.example.jean_michel.projet_troisieme_annee;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.github.pires.obd.commands.SpeedCommand;
import com.github.pires.obd.commands.engine.RPMCommand;
import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.github.pires.obd.commands.protocol.LineFeedOffCommand;
import com.github.pires.obd.commands.protocol.SelectProtocolCommand;
import com.github.pires.obd.commands.protocol.TimeoutCommand;
import com.github.pires.obd.enums.ObdProtocols;

import java.io.IOException;
import java.util.UUID;

public class CaptureVehiculeData implements Runnable {
    private Handler handler;

    public CaptureVehiculeData(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        // connection
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = btAdapter.getRemoteDevice(MainActivity.getDeviceAddress());
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        BluetoothSocket socket = null;
        try {
            socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
            socket.connect();

            // config
            new EchoOffCommand().run(socket.getInputStream(), socket.getOutputStream());

            new LineFeedOffCommand().run(socket.getInputStream(), socket.getOutputStream());

            new TimeoutCommand(250).run(socket.getInputStream(), socket.getOutputStream());

            new SelectProtocolCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());

            final RPMCommand engineRpmCommand = new RPMCommand();
            SpeedCommand speedCommand = new SpeedCommand();
            while (!Thread.currentThread().isInterrupted())
            {
                engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
                speedCommand.run(socket.getInputStream(), socket.getOutputStream());
                String engineRpm = engineRpmCommand.getFormattedResult();
                String speed = speedCommand.getFormattedResult();

                Log.d("CaptureVehiculeData", "RPM: " + engineRpm);
                Message message = handler.obtainMessage();
                Bundle messageBundle = new Bundle();
                messageBundle.putString(MainActivity.ENGINE_VALUE_CHANGED, engineRpm);
                message.setData(messageBundle);
                handler.sendMessage(message);

                Log.d("CaptureVehiculeData", "Speed: " + speed);
                message = handler.obtainMessage();
                 messageBundle = new Bundle();
                messageBundle.putString(MainActivity.SPEED_VALUE_CHANGED, speed);
                message.setData(messageBundle);
                handler.sendMessage(message);

                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        catch (com.github.pires.obd.exceptions.NoDataException e)
        {
            // end of recording
            Log.d("CaptureVehiculeData", "End of recording");
        }
    }
}