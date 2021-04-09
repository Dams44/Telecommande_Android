package com.example.lejosproject_telecommande;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.lejosproject_telecommande.MainActivity.MY_PREFS_NAME;
import static org.junit.Assert.*;

public class BluetoothConnectionServiceTest {

    private Context context;
    String bluetoothState;

    @Before
    public void setUp() throws Exception {
        bluetoothState = context.getSharedPreferences(MY_PREFS_NAME, 0).getString("bluetoothState", null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConnectRobot() {
        assertEquals(bluetoothState, "1");
    }

}