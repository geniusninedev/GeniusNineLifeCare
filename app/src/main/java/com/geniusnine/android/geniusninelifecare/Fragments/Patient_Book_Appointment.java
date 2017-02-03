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

        //Initializing Views
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        loadCategoryData();

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
