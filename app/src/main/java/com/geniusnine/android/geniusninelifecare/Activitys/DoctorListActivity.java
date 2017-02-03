package com.geniusnine.android.geniusninelifecare.Activitys;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Adapter.DoctorListAdapter;
import com.geniusnine.android.geniusninelifecare.Helper.Config;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;

import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * Created by Dev on 20-01-2017.
 */

public class DoctorListActivity extends AppCompatActivity{
    private RecyclerView recyclerView;


Activity activity;
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> DOCTOR_DEGREE_ArrayList = new ArrayList<String>();
    ArrayList<String> DOCTOR_EXP_ArrayList = new ArrayList<String>();
    ArrayList<String> DOCTOR_SPECILIZATION_ArrayList = new ArrayList<String>();
    ArrayList<String> DOCTOR_Likes_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_Views_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_Reviews_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_Hospitalname_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_Hospitallocation_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_Nearbylocation_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_nearbylocationdistance_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_Availibility_ArrayList= new ArrayList<String>();
    ArrayList<String> DOCTOR_Availibilitytime_ArrayList= new ArrayList<String>();
    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    String catergory;
    Bitmap bitmap = null;
    DoctorListAdapter doctorListAdapter;
    EditText edittestpatientcauses,edittestpatientfrom,edittestpatientreason;
    Spinner spinnerPatienttimings;
    Button buttonsubmituser,buttonAppointmentDate;
    DBHelper dbHelper;
    public Calendar calender;
    private int day;
    private int month;
    private int year;
    private String patient_mobile_Number;
    String patient_id;
    final Calendar cal = Calendar.getInstance();
    String myFormat = "yyyy-MM-DD"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    TextView textViewcurrentdate,textViewAppointmentDate;



    Cursor cursor;
    //  Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list_activity);
        dbHelper=new DBHelper(this);
        //Initializing Views
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewdoctor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
       catergory = getIntent().getStringExtra("CategoryName");
        loadHistoryData();


    }

    private void loadHistoryData() {
        cursor = dbHelper.getDoctorList(catergory);
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        DOCTOR_DEGREE_ArrayList.clear();
        DOCTOR_EXP_ArrayList.clear();
        DOCTOR_SPECILIZATION_ArrayList.clear();
        DOCTOR_Hospitalname_ArrayList.clear();
        DOCTOR_Hospitallocation_ArrayList.clear();
        DOCTOR_Likes_ArrayList.clear();
        DOCTOR_Views_ArrayList.clear();
        DOCTOR_Reviews_ArrayList.clear();
        DOCTOR_Nearbylocation_ArrayList.clear();
        DOCTOR_nearbylocationdistance_ArrayList.clear();
        DOCTOR_Availibility_ArrayList.clear();
        DOCTOR_Availibilitytime_ArrayList.clear();
        bitmaps.clear();


        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_ID)));
                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_NAME)));
                DOCTOR_DEGREE_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_DEGREE)));
                DOCTOR_EXP_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_EXPERIENCE)));
                //DOCTOR_SPECILIZATION_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_SPECILIZATION)));
                DOCTOR_Hospitalname_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_HOSPITAL_NAME)));
                DOCTOR_Hospitallocation_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_HOSPITAL_LOCATION)));
                DOCTOR_Likes_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_LIKE)));
                DOCTOR_Views_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_VIEWS)));
                DOCTOR_Reviews_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_REVIEWS)));
                DOCTOR_Nearbylocation_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_SOURCE_FOR_HOSPITAL)));
                DOCTOR_nearbylocationdistance_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_HOSPITAL_LOCATION_IN_KM_FOR_SOURCE)));
                DOCTOR_Availibility_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_AVAILABILITY_IN_DAYS)));
                DOCTOR_Availibilitytime_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_AVAILABILITY)));


                byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbHelper.COLUMN_DOCTOR_PROFILE_PICTURE));
                bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                bitmaps.add(bitmap);

            } while (cursor.moveToNext());
        }

        doctorListAdapter = new DoctorListAdapter(this,

                DOCTOR_Hospitalname_ArrayList,
                ID_ArrayList,
                NAME_ArrayList,
                DOCTOR_DEGREE_ArrayList,
                DOCTOR_Nearbylocation_ArrayList,
                DOCTOR_Availibility_ArrayList,
                DOCTOR_Availibilitytime_ArrayList,
                DOCTOR_EXP_ArrayList,
                DOCTOR_Likes_ArrayList,
                DOCTOR_Views_ArrayList,
                DOCTOR_Reviews_ArrayList,
                DOCTOR_Hospitallocation_ArrayList,
                DOCTOR_nearbylocationdistance_ArrayList,
                bitmaps

        );
        recyclerView.setAdapter(doctorListAdapter);
        cursor.close();
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

