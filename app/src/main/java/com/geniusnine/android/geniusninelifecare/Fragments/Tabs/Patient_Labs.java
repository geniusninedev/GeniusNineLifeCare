package com.geniusnine.android.geniusninelifecare.Fragments.Tabs;

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

import com.geniusnine.android.geniusninelifecare.Adapter.Health_Tips_ImageAdapter;
import com.geniusnine.android.geniusninelifecare.Adapter.Labs_ImageAdapter;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Labs extends Fragment {
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> DESCRIPTION_ArrayList = new ArrayList<String>();
    ArrayList<String> DATE_ArrayList = new ArrayList<String>();
    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    Cursor cursor;
    DBHelper dbHelper;
    Bitmap bitmap = null;
    //Creating Views
    private RecyclerView recyclerView;
   Labs_ImageAdapter labs_imageAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.labs, null);
        dbHelper=new DBHelper(getActivity());
        //Initializing Views
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        loadHealth_and_tips_Data();


        return v;
    }

    private void loadHealth_and_tips_Data() {
        cursor = dbHelper.getLabs();
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        bitmaps.clear();
        DATE_ArrayList.clear();


        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_LAB_ID)));
                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_LAB_NAME)));
                DESCRIPTION_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_LAB_DESCRIPTION)));
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbHelper.COLUMN_LAB_IMAGE));
                bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                bitmaps.add(bitmap);
                DATE_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_LAB_REGISRTION_DATE)));

            } while (cursor.moveToNext());
        }

        labs_imageAdapter = new Labs_ImageAdapter(getActivity(),

                ID_ArrayList,
                NAME_ArrayList,
                DESCRIPTION_ArrayList,
                bitmaps,
                DATE_ArrayList

        );
        recyclerView.setAdapter( labs_imageAdapter);

        cursor.close();
    }
}