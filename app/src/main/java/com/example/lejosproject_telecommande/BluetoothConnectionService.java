package com.example.lejosproject_telecommande;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class BluetoothConnectionService {
    //Variable d'instance
    private static BluetoothConnectionService instance;
    private Context context;

    //Variables globales
    private String MAC;
    private BluetoothAdapter localAdapter;
    private BluetoothSocket socketRobot;
    private OutputStreamWriter out;


    private BluetoothConnectionService(Context context) {
        this.context = context;
    }

    public static BluetoothConnectionService getInstance(Context context) {
        if (instance == null) {
            instance = new BluetoothConnectionService(context);
        }
        return(instance);
    }

    public String enableBluetooth(){
        //Lancement du Bluetooth sur le smartphone s'il n'est pas en fonction
        localAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!localAdapter.isEnabled()){
            localAdapter.enable();
            return "Erreur";
        }
        return "Success";
    }

    public String connectRobot(){
        //Mise en place de la connection bluetooth avec le robot
        String success;
        success = enableBluetooth();
        if (success.equals("Success")) {
            BluetoothDevice robot = localAdapter.getRemoteDevice(MAC);
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            try {
                //Ouverture d'une socket permettant la communication avec le robot
                socketRobot = robot.createRfcommSocketToServiceRecord(uuid);
                socketRobot.connect();
                //Ouverture d'un canal d'écriture vers le robot grâce à la socket
                out = new OutputStreamWriter(socketRobot.getOutputStream());
                success = "1";
            } catch (IOException e) {
                success = e.getMessage();
            }
        } else {
            success += " - Le bluetooth est désactivé";
        }
        return success;
    }

    public void setMAC(String MAC){
        this.MAC = MAC;
    }

    public void send(byte message) {
        //Envoi d'un message vers le robot grâce au canal d'écriture
        try{
            out.write(message);
            out.flush();
        } catch (IOException ioe) {
            System.out.println("IO Exception");
        }
    }

    public void stopBluetooth() {
        //Coupure de la connection avec la fermeture de la socket et du canal d'écriture
        try{
            socketRobot.close();
            out.close();
        } catch (IOException ioe) {
            System.out.println("IO Exception");
        }
    }
}
