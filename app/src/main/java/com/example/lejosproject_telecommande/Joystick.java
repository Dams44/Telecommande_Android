package com.example.lejosproject_telecommande;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;
    private JoystickListener joystickCallback;

    public Joystick(Context c) { // constructeur d'un objet joystick
        super(c);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(c instanceof JoystickListener){
            joystickCallback = (JoystickListener) c;
        }
    }

    public Joystick(Context c, AttributeSet a, int style) {
        super(c,a,style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(c instanceof JoystickListener){
            joystickCallback = (JoystickListener) c;
        }
    }

    public Joystick(Context c, AttributeSet a){
        super(c,a);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(c instanceof JoystickListener){
            joystickCallback = (JoystickListener) c;
        }
    }

    private void drawJoystick(float newX, float newY){  //vue graphique du joystick sur l'application
        if(getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            myCanvas.drawColor(Color.WHITE);

            colors.setARGB(255,16,57,82); //couleur du périmètre d'action du joystick (le cercle extérieur)
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);
            colors.setARGB(255, 201, 213, 221); //couleur du joystick (cercle intérieur)
            myCanvas.drawCircle(newX, newY, hatRadius, colors);

            getHolder().unlockCanvasAndPost(myCanvas);
        }
    }

    private void setupDimensions() { //Settings de base des dimensions du joystick et de l'emplacement de son centre
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 5;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){ //création du joystick
        setupDimensions();
        drawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }

    public boolean onTouch(View v, MotionEvent e){ //méthode permettant de visualiser les déplacements du joystick
        if(v.equals(this)){
            if(e.getAction() != e.ACTION_UP){
                float displacement = (float) Math.sqrt(Math.pow(e.getX() - centerX, 2) + Math.pow(e.getY() - centerY, 2));
                if(displacement < baseRadius) {
                    drawJoystick(e.getX(), e.getY());
                    //Calcul des nouvelles positions du joystick
                    joystickCallback.onJoystickMoved((e.getX() - centerX) / baseRadius, (e.getY() - centerY) / baseRadius, getId());
                    //Récupération des coordonnées du joystick pour effectuer les déplacements du robot
                    joystickCallback.getX();
                    joystickCallback.getY();
                }else{
                    //permet de faire en sorte que le joystick ne sorte pas de son périmètre d'action (cercle extérieur)
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (e.getX() - centerX) * ratio;
                    float constrainedY = centerY + (e.getY() - centerY) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                    //Calcul des nouvelles positions du joystick
                    joystickCallback.onJoystickMoved((constrainedX - centerX) / baseRadius, (constrainedY - centerY) / baseRadius, getId());
                    //Récupération des coordonnées du joystick pour effectuer les déplacements du robot
                    joystickCallback.getX();
                    joystickCallback.getY();
                }
            }else{
                drawJoystick(centerX, centerY);
                //Calcul des nouvelles positions du joystick
                joystickCallback.onJoystickMoved(0, 0, getId());
                //Récupération des coordonnées du joystick pour effectuer les déplacements du robot
                joystickCallback.getX();
                joystickCallback.getY();
            }
        }
        return true;
    }


    public interface JoystickListener { // l'interface permet au ControlPanel de récupérer les coordonnées du joystick grâce aux méthodes getX, getY
        void onJoystickMoved(float xPercent, float yPercent, int source);
        float getX();
        float getY();
    }
}
