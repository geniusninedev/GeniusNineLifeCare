package com.geniusnine.android.geniusninelifecare.Login_Patient;

import android.app.Activity;
import android.os.Bundle;

import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Login extends Activity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login);
    }
}