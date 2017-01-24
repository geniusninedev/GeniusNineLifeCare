package com.geniusnine.android.geniusninelifecare.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Adapter.ImageAdapter;
import com.geniusnine.android.geniusninelifecare.Helper.Config;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.R.attr.bitmap;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Book_Appointment extends Fragment {

    EditText edittestpatientcauses,edittestpatientfrom,edittestpatientreason;
    Spinner spinnerPatienttimings;
    Button buttonsubmituser,buttonAppointmentDate;
    DBHelper dbHelper;
    private String patient_mobile_Number;
    String patient_id;
    TextView textViewcurrentdate,textViewAppointmentDate;
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    Cursor cursor;
    Bitmap bitmap = null;
    //For calender
    public Calendar calender;
    private int day;
    private int month;
    private int year;

    final Calendar cal = Calendar.getInstance();
    String myFormat = "yyyy-MM-DD"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    //Creating Views
    private RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.patient_book_appointment, null);
        dbHelper=new DBHelper(getActivity());



        textViewcurrentdate=(TextView)v.findViewById(R.id.textViewcurrentdate);
        textViewAppointmentDate=(TextView)v.findViewById(R.id.textViewAppointmentDate);
        spinnerPatienttimings = (Spinner)v. findViewById(R.id.spinnerpatienttimings);
        buttonsubmituser = (Button)v. findViewById(R.id.buttonsubmitpatient);
        buttonAppointmentDate = (Button)v. findViewById(R.id.buttonCalenderAppointmentdate);
        edittestpatientcauses = (EditText)v. findViewById(R.id.edittextpatientcauses);
        edittestpatientfrom = (EditText)v. findViewById(R.id.edittextpatientFrom);
        edittestpatientreason = (EditText)v. findViewById(R.id.edittextpatientReason);
        //Initializing Views
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        loadCategoryData();

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
        //fetching value from sharedpreference
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching thepatient_mobile_Number value form sharedpreferences
        patient_mobile_Number = sharedPreferences.getString(Config.PATIENT_MOBILE_NO_SHARED_PREF,null);
        dbHelper.getPatientData(patient_mobile_Number);
        cursor = dbHelper.getPatientData(patient_mobile_Number);
        cursor.moveToFirst();
        if (cursor != null) {
            patient_id = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_ID));
        }

        List<String> timings = new ArrayList<String>();
        timings.add("11.00 am- 1.00 pm");
        timings.add("2.00 pm - 4.00 pm");
        timings.add("6.30 pm - 8.00 pm");

        buttonAppointmentDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Process to get Current Date
                calender = Calendar.getInstance();
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH);
                year = cal.get(Calendar.YEAR);
                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                textViewAppointmentDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, year, month, day);
                dpd.show();
            }


        });

        // Creating adapter for spinner
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, timings);

        // Drop down layout style - list view with radio button
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerPatienttimings.setAdapter(Adapter);
        buttonsubmituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appointment_status="Request sent",appointment_status_percent="10";

                String patientcauses, appointment_from_days, patientreasons, appointmenttimings,currentdate;
                patientcauses = edittestpatientcauses.getText().toString();
                String appointmentdate=textViewAppointmentDate.getText().toString().trim();
                appointment_from_days = edittestpatientfrom.getText().toString();
                patientreasons = edittestpatientreason.getText().toString();
                appointmenttimings = spinnerPatienttimings.getSelectedItem().toString().trim();
                currentdate=textViewcurrentdate.getText().toString().trim();
                if (patientcauses.equals("")) {
                    edittestpatientcauses.setError("Causes is Required");
                } else if (appointment_from_days.equals("")) {
                    edittestpatientfrom.setError("Duration is Required");
                } else if (patientreasons.equals("")) {
                    edittestpatientreason.setError("Reason is Required");
                } else if (spinnerPatienttimings.getSelectedItem().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "Timings Required", Toast.LENGTH_LONG).show();

                } else {
                    dbHelper.addBookAppointment(patient_id,currentdate,appointmentdate,appointmenttimings,patientcauses,patientreasons,appointment_from_days,appointment_status,appointment_status_percent);
                    Toast.makeText(getActivity(), "Appointment Booked Successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getActivity(), MainActivityDrawer.class);
                    getActivity().finish();
                    getActivity().startActivity(intent);
                }
            }
        });

        return v;
    }
    private void loadCategoryData() {

        cursor = dbHelper.getCategory();
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        bitmaps.clear();


        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORY_ID)));
                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORY_NAME)));
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORY_IMAGE));
                bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                bitmaps.add(bitmap);

            } while (cursor.moveToNext());
        }

        imageAdapter = new ImageAdapter(getActivity(),

                ID_ArrayList,
                NAME_ArrayList,
                bitmaps

        );
        recyclerView.setAdapter(imageAdapter);

        cursor.close();
    }
}
