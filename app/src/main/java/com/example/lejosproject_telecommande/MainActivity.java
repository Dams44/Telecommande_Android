package com.example.lejosproject_telecommande;

import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements Joystick.JoystickListener {
    //Variables globales
    EditText inputMACReseau;
    TextView resultMAC;

    public static final String MY_PREFS_NAME = "BLUETOOTH";

    BluetoothConnectionService blService;
    private static final Pattern pattern = Pattern.compile("^([0-9A-Z]{2}:){5}([0-9A-Z]{2})$");
    // Joystick joystick = new Joystick(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();


        //Récupération des éléments de la vue
        this.inputMACReseau = findViewById(R.id.editMACRobot);
        this.resultMAC = findViewById(R.id.resultIP);

        this.blService = BluetoothConnectionService.getInstance(this);

        //Contrôle l'événement onClick sur le bouton connection bluetooth
        final Button connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String MAC;
                String success = "0";
                //resultMAC.setText("");
                MAC = inputMACReseau.getText().toString();
                //Vérification de la conformité de l'adresse MAC saisie
                if(pattern.matcher(MAC).matches()) {
                    blService.setMAC(MAC);

                    //blService.setMAC("00:16:53:80:46:E8");
                    success = blService.connectRobot();
                    if (success.equals("1")) {
                        Intent intent = new Intent(MainActivity.this, ControlePanel.class);
                        startActivity(intent);
                    } else {
                        //resultMAC.setText(success);
                        Toast.makeText(MainActivity.this,"error ",Toast.LENGTH_LONG).show();

                    }
                } else {
                    //resultMAC.setText("Adresse MAC incorrect");
                    Toast.makeText(MainActivity.this,"error ",Toast.LENGTH_LONG).show();

                }
                editor.putString("bluetoothState", success);
                editor.apply();

            }
        });
    }

    public void onJoystickMoved(float xPercent, float yPercent, int source){
        Log.d("Main Method", "X percent: " + xPercent + " Y percent: " + yPercent);
    }

    public float getX(){
        return (float) 0.1;
    }

    public float getY(){
        return (float) 0.9;
    }

}
