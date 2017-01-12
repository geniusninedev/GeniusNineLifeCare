package com.geniusnine.android.geniusninelifecare.Login_Patient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.Patient_Registration.Patient_Registration;
import com.geniusnine.android.geniusninelifecare.R;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Login extends Activity {
    DBHelper dbHelper;
    EditText edittextUsername,edittextPassword;
    TextView textViewforgetpassword;
    Button buttonsignIn,buttonsignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login);
        edittextUsername=(EditText)findViewById(R.id.editTextUserName);
        edittextPassword=(EditText)findViewById(R.id.editTextPassword);
        textViewforgetpassword=(TextView)findViewById(R.id.textViewforgetpasswordclick);
        buttonsignIn=(Button)findViewById(R.id.buttonSignIn);
        buttonsignUp=(Button)findViewById(R.id.buttonSignUp);
        buttonsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Patient_Login.this, MainActivityDrawer.class);
                startActivity(i1);
            }
        });
        buttonsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Patient_Login.this, Patient_Registration.class);
                startActivity(i2);

            }
        });
        textViewforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Patient_Login.this,"Forget Password",Toast.LENGTH_SHORT).show();

            }
        });
    }



    }
