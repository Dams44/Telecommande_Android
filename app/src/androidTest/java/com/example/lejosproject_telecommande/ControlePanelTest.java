package com.example.lejosproject_telecommande;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.view.MotionEvent;
import android.view.View;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControlePanelTest {

    @Rule
    public ActivityTestRule<ControlePanel> controlpanel = new ActivityTestRule<ControlePanel>(ControlePanel.class);
    private ControlePanel mActivity = null;

    Joystick joystick;


    @Test
    public void testJoystick(){

        joystick = (Joystick)mActivity.findViewById(R.id.joystick);

        MotionEvent event = MotionEvent.obtain(1,1,1,1,1,1);

        joystick.onTouch(joystick,event);
        assertNotEquals(mActivity.getX(), 0);
        assertNotEquals(mActivity.getY(), 0);
    }


    @Before
    public void setUp() throws Exception {
        mActivity = controlpanel.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void on() {
    }

    @Test
    public void stop() {
    }

    @Test
    public void request() {
    }

    @Test
    public void onJoystickMoved() {


    }

    @Test
    public void getX() {
    }

    @Test
    public void getY() {
    }
}