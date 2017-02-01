package com.geniusnine.android.geniusninelifecare.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dev on 12-01-2017.
 */

public class Doctor_info extends Fragment {
    Spinner spinnergender,spinnertimings,spinnerdoctorcategory,spinnerLunchtimings,spinnerdays;
    DBHelper dbHelper;
    EditText editTextname,editTextmobile,editTextemail,editTextdegree,editTextspecialization,editTextexperience,editTextachievements;
    TextView textViewcurrentdate;

    Button buttonsubmitdoctor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doctor_info, null);
        dbHelper = new DBHelper(getActivity());
        spinnerdays=(Spinner)v.findViewById(R.id.spinnerdoctorDays);
        edittext = (EditText)v. findViewById(R.id.edittextpatientFrom);


        buttonsubmitdoctor=(Button)v.findViewById(R.id.buttondoctorsubmit);



        return  v;+
    }
    private void loadSpinnerData() {
        dbHelper = new DBHelper(getActivity());
        List<String> labels = dbHelper.getAllCategory();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerdoctorcategory.setAdapter(dataAdapter);
    }
}