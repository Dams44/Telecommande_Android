package com.example.lejosproject_telecommande;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoystickTest {


    @Rule
    public ActivityTestRule<ControlePanel> mainActivityTestRule = new ActivityTestRule<ControlePanel>(ControlePanel.class);
    private ControlePanel mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void TestLaunch() {
        View view = mActivity.findViewById(R.id.joystick);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void surfaceCreated() {
    }

    @Test
    public void surfaceChanged() {
    }

    @Test
    public void surfaceDestroyed() {
    }

    @Test
    public void onTouch() {
    }
}