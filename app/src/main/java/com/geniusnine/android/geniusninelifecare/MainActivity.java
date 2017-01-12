package com.geniusnine.android.geniusninelifecare;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(MainActivity.this);
        final EditText edittextPatientname = (EditText) findViewById(R.id.edittextpatientname);
        final EditText edittextPatientemail = (EditText) findViewById(R.id.edittextpatientemail);
        final Spinner spinnerPatientgender=(Spinner)findViewById(R.id.spinnerpatientgender);
        final EditText edittextPatientmobilenumber = (EditText)findViewById(R.id.edittextpatientmobilenumber);
        final EditText edittextPatientage = (EditText)findViewById(R.id. edittextpatientage);
        Button buttonregisteruser=(Button) findViewById(R.id.buttonregisterpatient);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerPatientgender.setAdapter(dataAdapter);
        buttonregisteruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientname,patientemail,patientgender,patientmobilenumber,patientage;
                patientname=edittextPatientname.getText().toString().trim();
                patientemail=edittextPatientemail.getText().toString().trim();
                patientgender=spinnerPatientgender.getSelectedItem().toString().trim();
                patientmobilenumber=edittextPatientmobilenumber.getText().toString().trim();
                patientage=edittextPatientage.getText().toString().trim();
                dbHelper.addUser(patientname,patientemail,patientgender,patientmobilenumber,patientage);
                Toast.makeText(MainActivity.this,"Patient Registred SuccessFully",Toast.LENGTH_LONG).show();

            }
        });

    }
}
