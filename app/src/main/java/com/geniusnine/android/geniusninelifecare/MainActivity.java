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
        final EditText edittextusername = (EditText) findViewById(R.id.edittextusername);
        final EditText edittextuseremail = (EditText) findViewById(R.id.edittextuseremail);
        final Spinner spinnergender=(Spinner)findViewById(R.id.spinnergender);
        final EditText edittextusermobilenumber = (EditText)findViewById(R.id.edittextusermobilenumber);
        final EditText edittextuserage = (EditText)findViewById(R.id. edittextuserage);
        Button buttonregisteruser=(Button) findViewById(R.id.buttonregisteruser);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnergender.setAdapter(dataAdapter);
        buttonregisteruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,useremail,usergender,usermobilenumber,userage;
                username=edittextusername.getText().toString().trim();
                useremail=edittextuseremail.getText().toString().trim();
                usergender=spinnergender.getSelectedItem().toString().trim();
                usermobilenumber=edittextusermobilenumber.getText().toString().trim();
                userage=edittextuserage.getText().toString().trim();
                dbHelper.addUser(username,useremail,usergender,usermobilenumber,userage);
                Toast.makeText(MainActivity.this,"User Registred SuccessFully",Toast.LENGTH_LONG).show();

            }
        });

    }
}
