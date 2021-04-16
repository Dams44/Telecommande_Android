package com.example.lejosproject_telecommande;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;


import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Automatic  extends AppCompatActivity {

    private Button automatic;
    private Button manuel;

    private BluetoothConnectionService bluetoothConnectionService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.automatic_activity);

        bluetoothConnectionService =  BluetoothConnectionService.getInstance(this);

        automatic =findViewById(R.id.automatic);
        manuel = findViewById(R.id.manuel);

        manuel.setOnClickListener(manuelListener);
        automatic.setOnClickListener(automaticListener);


    }
    public void request(byte message){this.bluetoothConnectionService.send(message);}

    public View.OnClickListener manuelListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Automatic.this, ControlePanel.class);
            startActivity(intent);
        }
    };

    public  View.OnClickListener automaticListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            request((byte)84);
            SystemClock.sleep(100);

        }
    };
}

