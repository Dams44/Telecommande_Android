package com.example.lejosproject_telecommande;

import android.os.SystemClock;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ControlePanel extends AppCompatActivity implements Joystick.JoystickListener{
    //Variable globale
    BluetoothConnectionService bluetoothConnectionService;
    private float X;
    private float Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_panel);

        this.bluetoothConnectionService = BluetoothConnectionService.getInstance(this);
    }

    //Chaque méthode correspondent au onClick d'un des boutons de la vue télécommande
    //Elles appelent la méthode request qui permet d'envoyer une requête au robot
    /*public void marche(View view){
        request((byte) 0);
    }*/

    public void quitter (View view){
        request((byte) 9);
        bluetoothConnectionService.stopBluetooth();
        System.exit(0);
    }

    public void connectPortail (View view){
        request((byte) 99);
    }

    /*public void arret(View view){
        request((byte) 14);
    }*/

    public void request(byte message) {
        this.bluetoothConnectionService.send(message);
    }

    public void onJoystickMoved (float xPercent, float yPercent, int source){
        Log.d("Main Method", "X percent: " + xPercent + " Y percent: " + yPercent);
        this.X = xPercent;
        this.Y = yPercent;
        if((this.getX() < 0 && this.getX() > -1) && (this.getY() > -0.2 && this.getY() < 0.2)){//Left
            // request((byte) 3);
            if(this.getX() <= -0.2 && this.getX() > -0.4){
                //vitesse1 la plus faible accélération
                request((byte) 31);
                SystemClock.sleep(100);
            }else if(this.getX() <= -0.4 && this.getX() > -0.6){
                //vitesse2
                request((byte) 32);
                SystemClock.sleep(100);
            }else if(this.getX() <= -0.6 && this.getX() > -0.8){
                //vitesse 3
                request((byte) 33);
                SystemClock.sleep(100);
            }else if(this.getX() <= -0.8 && this.getX() > -1){
                //vitesse 4 la plus forte accélération
                request((byte) 34);
                SystemClock.sleep(100);
            }
        }else if((this.getX() > 0 && this.getX() < 1) && (this.getY() > -0.2 && this.getY() < 0.2)){//Right
            //  request((byte) 2);
            if(this.getX() >= 0.2 && this.getX() < 0.4){
                //vitesse1 la plus faible accélération
                request((byte) 21);
                SystemClock.sleep(100);
            }else if(this.getX() >= -0.4 && this.getX() < 0.6){
                //vitesse2
                request((byte) 22);
                SystemClock.sleep(100);
            }else if(this.getX() >= -0.6 && this.getX() < 0.8){
                //vitesse 3
                request((byte) 23);
                SystemClock.sleep(100);
            }else if(this.getX() >= -0.8 && this.getX() < 1){
                //vitesse 4 la plus forte accélération
                request((byte) 24);
                SystemClock.sleep(100);
            }
        }else if((this.getY() < 0 && this.getY() > -1) && (this.getX() > -0.2 && this.getX() < 0.2)){//Forward
            //request((byte) 4);
            if(this.getY() <= -0.2 && this.getY() > -0.4){
                //vitesse1 la plus faible accélération
                request((byte) 41);
                SystemClock.sleep(100);
            }else if(this.getY() <= -0.4 && this.getY() > -0.6){
                //vitesse2
                request((byte) 42);
                SystemClock.sleep(100);
            }else if(this.getY() <= -0.6 && this.getY() > -0.8){
                //vitesse 3
                request((byte) 43);
                SystemClock.sleep(100);
            }else if(this.getY() <= -0.8 && this.getY() > -1){
                //vitesse 4 la plus forte accélération
                request((byte) 44);
                SystemClock.sleep(100);
            }
        }else if((this.getY() > 0 && this.getY() < 1) && (this.getX() > -0.2 && this.getX() < 0.2)){//Backward
            //request((byte) 5);
            if(this.getY() >= 0.2 && this.getY() < 0.4){
                //vitesse1 la plus faible accélération
                request((byte) 51);
                SystemClock.sleep(100);
            }else if(this.getY() >= -0.4 && this.getY() < 0.6){
                //vitesse2
                request((byte) 52);
                SystemClock.sleep(100);
            }else if(this.getY() >= -0.6 && this.getY() < 0.8){
                //vitesse 3
                request((byte) 53);
                SystemClock.sleep(100);
            }else if(this.getY() >= -0.8 && this.getY() < 1){
                //vitesse 4 la plus forte accélération
                request((byte) 54);
                SystemClock.sleep(100);
            }
        }else if ((this.getX() < -0.2 && this.getX() > -1) && (this.getY() < -0.2 && this.getY() > -1)){//LeftForward

            if((this.getX() < -0.2 && this.getX() >= -0.5) && (this.getY() < -0.2 && this.getY() >= -0.5)){
                //vitesse1
                request((byte) 61);
                SystemClock.sleep(100);
            }else if((this.getX() < -0.5 && this.getX() >= -0.8) && (this.getY() < -0.5 && this.getY() >= -0.8)){
                //vitesse 4
                request((byte) 62);
                SystemClock.sleep(100);
            }
        }else if ((this.getX() > 0.2 && this.getX() < 1) && (this.getY() < -0.2 && this.getY() > -1)){//RightForward
            if((this.getX() > 0.2 && this.getX() <= 0.5) && (this.getY() < -0.2 && this.getY() >= -0.5)){
                //vitesse1
                request((byte) 71);
                SystemClock.sleep(100);
            }else if((this.getX() > 0.5 && this.getX() <= 0.8) && (this.getY() < -0.5 && this.getY() >= -0.8)){
                //vitesse 4
                request((byte) 72);
                SystemClock.sleep(100);
            }
        }else if ((this.getX() < -0.2 && this.getX() > -1) && (this.getY() > 0.2 && this.getY() < 1)){//LeftBackward
            if((this.getX() < -0.2 && this.getX() >= -0.5) && (this.getY() > 0.2 && this.getY()  <= 0.5)){
                //vitesse1
                request((byte) 81);
                SystemClock.sleep(100);
            }else if((this.getX() < -0.5 && this.getX() >= -0.8) && (this.getY() > 0.5 && this.getY() <= 0.8)){
                //vitesse 4
                request((byte) 82);
                SystemClock.sleep(100);
            }
        }else if ((this.getX() > 0.2 && this.getX() < 1) && (this.getY() > 0.2 && this.getY() < 1)){//RightBackward
            if((this.getX() > 0.2 && this.getX() <= 0.5) && (this.getY() > 0.2 && this.getY() <= 0.5)){
                //vitesse1
                request((byte) 91);
                SystemClock.sleep(100);
            }else if((this.getX() > 0.5 && this.getX() <= 0.8) && (this.getY() > 0.5 && this.getY() <= 0.8)){
                //vitesse 4
                request((byte) 92);
                SystemClock.sleep(100);
            }
        }else if ((this.getX() == 0) && (this.getY() == 0)){//stop
            request((byte) 14);
            SystemClock.sleep(100);
        }
    }

    public float getX(){
        Log.d("Le X est :","ee" + this.X);
        return this.X;
    }

    public float getY(){
        Log.d("Le Y est :","ee" + this.Y);
        return this.Y;
    }
}
