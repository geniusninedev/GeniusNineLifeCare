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
import android.widget.ImageView;
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
    Spinner spinnerDoctor_name;
    DBHelper dbHelper;
    EditText edittext_medicalcouncil_id,edittext_hospital_location,edittext_doctor_hospital_nearest_location,edittext_hospital_km,edittext_doctor_like,edittext_doctor_rating,edittext_doctor_review,edittext_doctor_views,edittext_doctor_hospital_name;
    TextView textViewcurrentdate;
    ImageView imageViewDoctorProfilePicture;

    Button buttonsubmitdoctorInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doctor_info, null);
        dbHelper = new DBHelper(getActivity());
        imageViewDoctorProfilePicture=(ImageView)v.findViewById(R.id.imageViewDoctorProfilePicture);
        spinnerDoctor_name=(Spinner)v.findViewById(R.id.spinnerdoctorname);
        edittext_medicalcouncil_id = (EditText)v. findViewById(R.id.edittextcouncilid);
        edittext_doctor_hospital_name = (EditText)v. findViewById(R.id.edittexthospitalname);
        edittext_hospital_location = (EditText)v. findViewById(R.id.edittexthospitallocation);
        edittext_doctor_hospital_nearest_location = (EditText)v. findViewById(R.id.edittexthospitalnearestcity);
        edittext_hospital_km = (EditText)v. findViewById(R.id.edittextdistancefromnearestcity);
        edittext_doctor_like = (EditText)v. findViewById(R.id.edittextdoctorlike);
        edittext_doctor_rating = (EditText)v. findViewById(R.id.edittextdoctorrating);
        edittext_doctor_review = (EditText)v. findViewById(R.id.edittextdoctorreview);
        edittext_doctor_views = (EditText)v. findViewById(R.id.edittextdoctorview);
        buttonsubmitdoctorInfo=(Button)v.findViewById(R.id.buttondoctorinfosubmit);
        buttonsubmitdoctorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorname,medicalcouncil_id,doctor_hospital_name,hospital_location,hospital_nearest_location,hospital_km,doctor_like,doctor_rating,doctor_review,doctor_views;
                doctorname = spinnerDoctor_name.getSelectedItem().toString().trim();
                medicalcouncil_id = edittext_medicalcouncil_id.getText().toString();
                doctor_hospital_name = edittext_doctor_hospital_name.getText().toString();
                hospital_location = edittext_hospital_location.getText().toString();
                hospital_nearest_location = edittext_doctor_hospital_nearest_location.getText().toString();
                hospital_km = edittext_hospital_km.getText().toString();
                doctor_like = edittext_doctor_like.getText().toString();
                doctor_rating = edittext_doctor_rating.getText().toString();
                doctor_review = edittext_doctor_review.getText().toString();
                doctor_views = edittext_doctor_views.getText().toString();


                //validations for the doctor information field

                if(spinnerDoctor_name.getSelectedItem().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"Doctor name Required",Toast.LENGTH_LONG).show();
                }else if(edittext_medicalcouncil_id.getText().toString().trim().equals("")){
                    edittext_medicalcouncil_id.setError("Medical council id is Required");
                }else if(edittext_doctor_hospital_name.getText().toString().trim().equals("")){
                    edittext_doctor_hospital_name.setError("Hospital Name Required");
                } else if(edittext_hospital_location.getText().toString().trim().equals("")){
                    edittext_hospital_location.setError("Hospital Location is Required");
                } else if(edittext_doctor_hospital_nearest_location.getText().toString().trim().equals("")){
                    edittext_doctor_hospital_nearest_location.setError("Hospital nearest location is Required");
                }else if(edittext_hospital_km.getText().toString().trim().equals("")){
                    edittext_hospital_km.setError("Please Enter Valid Distance From Nearest Location");
                } else if(edittext_doctor_like.getText().toString().trim().equals("")){
                    edittext_doctor_like.setError("Doctor likes Required");
                }else if(edittext_doctor_rating.getText().toString().trim().equals("")){
                    edittext_doctor_rating.setError("Doctor rating is Required");
                }
                else if(edittext_doctor_review.getText().toString().trim().equals("")){
                    edittext_doctor_review.setError("Doctor reviews Required");
                }else if(edittext_doctor_views.getText().toString().trim().equals("")){
                    edittext_doctor_views.setError("Doctor views Required");
                }
                else{
                    dbHelper.UpdateDoctorinfo(doctorname, medicalcouncil_id,doctor_hospital_name,hospital_location, hospital_nearest_location,hospital_km,doctor_like,doctor_rating,doctor_review,doctor_views);
                    Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getActivity(), MainActivityDrawer.class);
                    getActivity().finish();
                    getActivity().startActivity(intent);
                }

            }
        });




        return  v;
    }
    private void loadSpinnerData() {
        dbHelper = new DBHelper(getActivity());
        List<String> labels = dbHelper.getAllCategory();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerDoctor_name.setAdapter(dataAdapter);
    }
}