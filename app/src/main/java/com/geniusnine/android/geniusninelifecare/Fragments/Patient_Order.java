package com.geniusnine.android.geniusninelifecare.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;;
import com.geniusnine.android.geniusninelifecare.Adapter.Health_Tips_ImageAdapter;
import com.geniusnine.android.geniusninelifecare.Adapter.MyOrder_Adapter;
import com.geniusnine.android.geniusninelifecare.Helper.Config;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;


/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Order extends Fragment {
    ProgressBar Progressbar;
    TextView ShowText;
    int progressbarvalue;
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> DESCRIPTION_ArrayList = new ArrayList<String>();
    ArrayList<String> DATE_ArrayList = new ArrayList<String>();
    Cursor cursor;
    DBHelper dbHelper;
    MyOrder_Adapter myOrder_adapter;
    private RecyclerView recyclerView;
    private String patient_mobile_Number;
    String patient_id;
    String databaseprogressbarvalue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.patient_order, null);
        dbHelper=new DBHelper(getActivity());

        Progressbar = (ProgressBar)v.findViewById(R.id.progressBar);
        ShowText = (TextView)v.findViewById(R.id.textViewprogressstatus);



        //Initializing Views
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
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
        dbHelper.getAppointmentData(patient_id);
        cursor = dbHelper.getAppointmentData(patient_id);
        cursor.moveToFirst();
        if (cursor != null) {
             databaseprogressbarvalue = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_APPOINTMENT_PATIENT_STATUS_PERCENT));
            progressbarvalue= Integer.parseInt(databaseprogressbarvalue);
        }
        Progressbar.setProgress(progressbarvalue);
        ShowText.setText(databaseprogressbarvalue+"%");
        loadorderdata(patient_id);

        return v;
    }

    private void loadorderdata(String patientid) {
        cursor = dbHelper.getTracking(patientid);
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        DATE_ArrayList.clear();


        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_ID)));
                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_CAUSES)));
                DESCRIPTION_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_PATIENT_STATUS)));
                DATE_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_DATE)));

            } while (cursor.moveToNext());
        }

      myOrder_adapter = new MyOrder_Adapter(getActivity(),

                ID_ArrayList,
                NAME_ArrayList,
                DESCRIPTION_ArrayList,
                DATE_ArrayList

        );
        recyclerView.setAdapter(myOrder_adapter);

        cursor.close();
    }

}