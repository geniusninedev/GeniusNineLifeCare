package com.geniusnine.android.geniusninelifecare.Patient_Registration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Registration extends Activity {
    DBHelper dbHelper;
    EditText edittextPatientname,edittextPatientemail,edittextPatientmobilenumber,edittextPatientage;
    Spinner spinnerPatientgender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_registrationform);
        dbHelper = new DBHelper(Patient_Registration.this);
         edittextPatientname = (EditText) findViewById(R.id.edittextpatientname);
         edittextPatientemail = (EditText) findViewById(R.id.edittextpatientemail);
         spinnerPatientgender=(Spinner)findViewById(R.id.spinnerpatientgender);
         edittextPatientmobilenumber = (EditText)findViewById(R.id.edittextpatientmobilenumber);
         edittextPatientage = (EditText)findViewById(R.id. edittextpatientage);
        Button buttonregisteruser=(Button) findViewById(R.id.buttonregisterpatient);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Patient_Registration.this, android.R.layout.simple_spinner_item, categories);

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
                Toast.makeText(Patient_Registration.this,"Patient Registred SuccessFully",Toast.LENGTH_LONG).show();

            }
        });

    }
}