package com.geniusnine.android.geniusninelifecare.Fragments;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.geniusnine.android.geniusninelifecare.Adapter.Health_Tips_ImageAdapter;
import com.geniusnine.android.geniusninelifecare.Adapter.Medicines_ImageAdapter;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;

;


/**
 * Created by Dev on 12-01-2017.
 */

public class Medicines extends Fragment {
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> DESCRIPTION_ArrayList = new ArrayList<String>();
    ArrayList<String> DATE_ArrayList = new ArrayList<String>();
    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    Cursor cursor;
    DBHelper dbHelper;
    Bitmap bitmap = null;
    Button buttonsearch;
    //Creating Views
    private RecyclerView recyclerView;
     Medicines_ImageAdapter medicines_imageAdapter;
    EditText editTextsearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.medicines, null);
        dbHelper=new DBHelper(getActivity());
        //Initializing Views
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        buttonsearch= (Button) v.findViewById(R.id.buttonsearch);
        editTextsearch=(EditText)v.findViewById(R.id.editText);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        loadHealth_and_tips_Data();
        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchstring=editTextsearch.getText().toString().trim();
                if(searchstring.equals("")){
                    editTextsearch.setError("Please enter name or cause");
                }
                else {
                    Search_Medicines(searchstring);
                }


            }
        });
        return v;
    }

    private void Search_Medicines(String searchedstring) {
        cursor = dbHelper.getSerchedMedicines(searchedstring);
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        bitmaps.clear();
        DATE_ArrayList.clear();


        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_ID)));
                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_NAME)));
                DESCRIPTION_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_DESCRIPTION)));
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_IMAGE));
                bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                bitmaps.add(bitmap);
                DATE_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_ADDED_DATE)));

            } while (cursor.moveToNext());
        }

        medicines_imageAdapter = new Medicines_ImageAdapter(getActivity(),

                ID_ArrayList,
                NAME_ArrayList,
                DESCRIPTION_ArrayList,
                bitmaps,
                DATE_ArrayList

        );
        recyclerView.setAdapter(medicines_imageAdapter);


        cursor.close();
    }

    private void loadHealth_and_tips_Data() {
        cursor = dbHelper.getMedicines();
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        bitmaps.clear();
        DATE_ArrayList.clear();


        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_ID)));
                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_NAME)));
                DESCRIPTION_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_DESCRIPTION)));
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_IMAGE));
                bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                bitmaps.add(bitmap);
                DATE_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MEDICINES_ADDED_DATE)));

            } while (cursor.moveToNext());
        }

        medicines_imageAdapter = new Medicines_ImageAdapter(getActivity(),

                ID_ArrayList,
                NAME_ArrayList,
                DESCRIPTION_ArrayList,
                bitmaps,
                DATE_ArrayList

        );
        recyclerView.setAdapter(medicines_imageAdapter);


    cursor.close();
    }


    }
