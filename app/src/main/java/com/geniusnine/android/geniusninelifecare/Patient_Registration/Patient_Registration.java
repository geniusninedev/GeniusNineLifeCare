package com.geniusnine.android.geniusninelifecare.Patient_Registration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.Login_Patient.Patient_Login;
import com.geniusnine.android.geniusninelifecare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Registration extends AppCompatActivity {
    DBHelper dbHelper;
    EditText edittextPatientname,edittextPatientmobilenumber,edittextpatientpassword,edittextPatientemail,edittextPatientage,edittextpatientheight,edittextpatientweight,edittextpatientbloodgroup,edittextpatientaddress,edittextpatientpincode;
    Spinner spinnerPatientgender;
    TextView textViewcurrentdate;
    final Calendar cal = Calendar.getInstance();
    String myFormat = "yyyy-MM-DD"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    Button buttonregisteruser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_registrationform);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        dbHelper = new DBHelper(Patient_Registration.this);
         textViewcurrentdate=(TextView)findViewById(R.id.textViewcurrentDate);
         edittextPatientname = (EditText) findViewById(R.id.edittextpatientname);
         edittextPatientmobilenumber = (EditText)findViewById(R.id.edittextpatientmobilenumber);
         edittextpatientpassword = (EditText)findViewById(R.id.edittextpatientpassword);
         edittextPatientemail = (EditText) findViewById(R.id.edittextpatientemail);
         spinnerPatientgender=(Spinner)findViewById(R.id.spinnerpatientgender);
         edittextPatientage = (EditText)findViewById(R.id. edittextpatientage);
        edittextpatientheight = (EditText) findViewById(R.id.edittextpatientheight);
        edittextpatientweight = (EditText)findViewById(R.id.edittextpatientweight);
        edittextpatientbloodgroup = (EditText)findViewById(R.id.edittextpatientbloodgroup);
        edittextpatientaddress = (EditText) findViewById(R.id.edittextpatientaddress);
        edittextpatientpincode=(EditText) findViewById(R.id. edittextpatientpincode);
        buttonregisteruser=(Button) findViewById(R.id.buttonregisterpatient);

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
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }

        };
        textViewcurrentdate.setText(sdf.format(cal.getTime()));
        buttonregisteruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientname,patientmobilenumber,patientpassword,patientemail,patientgender,patientage,patientheight,patientweight,patientbloodgroup,patientaddress,patientpincode,patientregistrationdate;
                patientname=edittextPatientname.getText().toString().trim();
                patientmobilenumber=edittextPatientmobilenumber.getText().toString().trim();
                patientpassword=edittextpatientpassword.getText().toString().trim();
                patientemail=edittextPatientemail.getText().toString().trim();
                patientgender=spinnerPatientgender.getSelectedItem().toString().trim();
                patientage=edittextPatientage.getText().toString().trim();
                patientheight=edittextpatientheight.getText().toString().trim();
                patientweight=edittextpatientweight.getText().toString().trim();
                patientbloodgroup=edittextpatientbloodgroup.getText().toString().trim();
                patientaddress=edittextpatientaddress.getText().toString().trim();
                patientpincode=edittextpatientpincode.getText().toString().trim();
                patientregistrationdate=textViewcurrentdate.getText().toString().trim();
                String MobileNumberpattern = "[0-9]{10}";
                String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                if(edittextPatientname.getText().toString().trim().equals("")){
                    edittextPatientname.setError("Name is Required");
                }else if(edittextPatientmobilenumber.getText().toString().trim().equals("")){
                    edittextPatientmobilenumber.setError("Mobile Number is Required");
                }else if(!edittextPatientmobilenumber.getText().toString().trim().matches(MobileNumberpattern)){
                    edittextPatientmobilenumber.setError("Please Enter Valid Mobile Number");
                } else if(edittextpatientpassword.getText().toString().trim().equals("")){
                    edittextpatientpassword.setError("Password is Required");
                } else if(!edittextpatientpassword.getText().toString().trim().matches(passpattern)){
                    edittextpatientpassword.setError("Password Contains One capital letter,One number,One symbol (@,$,%,#,)");
                }else if(!(edittextpatientpassword.getText().toString().trim().length() ==10)){
                    edittextpatientpassword.setError("Password size Should 10 Characters");
                }
                else if(edittextPatientemail.getText().toString().trim().equals("")){
                    edittextPatientemail.setError("Email id is Required");
                }else if(!edittextPatientemail.getText().toString().trim().matches(emailpattern)){
                    edittextPatientemail.setError("Please Enter Valid Email");
                } else if(spinnerPatientgender.getSelectedItem().toString().trim().equals("")){
                  Toast.makeText(Patient_Registration.this,"Gender is Required",Toast.LENGTH_LONG).show();
                }else if(edittextPatientage.getText().toString().trim().equals("")){
                    edittextPatientage.setError("Age is Required");
                }else if(edittextpatientheight.getText().toString().trim().equals("")){
                    edittextpatientheight.setError("Height is Required");
                }else if(edittextpatientweight.getText().toString().trim().equals("")){
                    edittextpatientweight.setError("Weight is Required");
                }else if(edittextpatientbloodgroup.getText().toString().trim().equals("")){
                    edittextpatientbloodgroup.setError("Blood Group is Required");
                }else if(edittextpatientaddress.getText().toString().trim().equals("")){
                    edittextpatientaddress.setError("Address is Required");
                }else if(edittextpatientpincode.getText().toString().trim().equals("")){
                    edittextpatientpincode.setError("Picode is Required");
                }
                else{
                    dbHelper.addUser(patientname,patientmobilenumber,patientpassword,patientemail,patientgender,patientage,patientheight,patientweight,patientbloodgroup,patientaddress,patientpincode,patientregistrationdate);
                    Toast.makeText(Patient_Registration.this,"Patient Registred Successfully",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Patient_Registration.this, Patient_Login.class);
                    finish();
                    startActivity(i);
                }


            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);

        }

        return super.onOptionsItemSelected(item);
    }
}