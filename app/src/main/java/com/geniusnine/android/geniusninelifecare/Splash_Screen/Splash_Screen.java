package com.geniusnine.android.geniusninelifecare.Splash_Screen;

import android.app.Activity;
import android.os.Bundle;

import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

/**
 * Created by Dev on 12-01-2017.
 */

public class Splash_Screen extends Activity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }
}