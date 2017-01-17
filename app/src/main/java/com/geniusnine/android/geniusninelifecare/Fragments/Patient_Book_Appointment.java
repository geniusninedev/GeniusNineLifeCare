package com.geniusnine.android.geniusninelifecare.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Patient_Book_Appointment extends Fragment {
    DBHelper dbHelper;
    EditText edittestpatientcauses,edittestpatientfrom,edittestpatientreason;
    Spinner spinnerPatienttimings;
    Button buttonsubmituser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.patient_book_appointment, null);
        dbHelper=new DBHelper(getActivity());
        spinnerPatienttimings = (Spinner)v. findViewById(R.id.spinnerpatienttimings);
        buttonsubmituser = (Button)v. findViewById(R.id.buttonsubmitpatient);
        edittestpatientcauses = (EditText)v. findViewById(R.id.edittextpatientcauses);
        edittestpatientfrom = (EditText)v. findViewById(R.id.edittextpatientFrom);
        edittestpatientreason = (EditText)v. findViewById(R.id.edittextpatientReason);
        List<String> timings = new ArrayList<String>();
        timings.add("11.00 am- 1.00 pm");
        timings.add("2.00 pm - 4.00 pm");
        timings.add("6.30 pm - 8.00 pm");

        // Creating adapter for spinner
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, timings);

        // Drop down layout style - list view with radio button
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerPatienttimings.setAdapter(Adapter);
        buttonsubmituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientcauses, patientfrom, patientreasons, patienttimings;
                patientcauses = edittestpatientcauses.getText().toString();
                patientfrom = edittestpatientfrom.getText().toString();
                patientreasons = edittestpatientreason.getText().toString();
                patienttimings = spinnerPatienttimings.getSelectedItem().toString().trim();
                if (patientcauses.equals("")) {
                    edittestpatientcauses.setError("Causes is Required");
                } else if (patientfrom.equals("")) {
                    edittestpatientfrom.setError("From is Required");
                } else if (patientreasons.equals("")) {
                    edittestpatientreason.setError("reason is Required");
                } else if (spinnerPatienttimings.getSelectedItem().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "Timings Required", Toast.LENGTH_LONG).show();

                } else {
                    // dbHelper.addBookAppointmentDetails(patientcauses, patientfrom, patientreasons, patienttimings);
                    Toast.makeText(getActivity(), "Appointment Booked Successfully", Toast.LENGTH_LONG).show();
                    //Intent i = new Intent(Patient_Registration.this, Patient_Login.class);
                    // startActivity(i);
                }
            }
        });

        return v;
    }
}
