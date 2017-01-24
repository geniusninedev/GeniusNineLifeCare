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

import com.geniusnine.android.geniusninelifecare.Adapter.History_ListAdapter;
import com.geniusnine.android.geniusninelifecare.Adapter.ImageAdapter;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;

import static android.R.attr.bitmap;


/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_History extends Fragment {
    private RecyclerView recyclerView;
    History_ListAdapter historyAdapter;
    DBHelper dbHelper;
    ArrayList<String> appointmentid_ArrayList = new ArrayList<String>();
    ArrayList<String> appointmentregistrationdate_ArrayList = new ArrayList<String>();
    ArrayList<String> appointmentdate_ArrayList = new ArrayList<String>();
    ArrayList<String> appointmentcauses_ArrayList = new ArrayList<String>();
    ArrayList<String> appointmentstatus_ArrayList = new ArrayList<String>();
    ArrayList<String> appointmentstatuspercent_ArrayList = new ArrayList<String>();
    //  ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    Cursor cursor;
    //  Bitmap bitmap = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_histoy, null);
        dbHelper=new DBHelper(getActivity());
        //Initializing Views
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerViewhistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        loadHistoryData();

        return v;
    }
    private void loadHistoryData() {

        cursor = dbHelper.getHistory();
        appointmentid_ArrayList.clear();
        appointmentregistrationdate_ArrayList.clear();
        appointmentdate_ArrayList.clear();
        appointmentcauses_ArrayList.clear();
        appointmentstatus_ArrayList.clear();
        appointmentstatuspercent_ArrayList.clear();


        //  bitmaps.clear();


        if (cursor.moveToFirst()) {
            do {
                appointmentid_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_ID)));
                appointmentregistrationdate_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_APPOINTMENT_REGISRTION_DATE)));
                appointmentdate_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_DATE)));
                appointmentcauses_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_CAUSES)));
                appointmentstatus_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_BOOK_APPOINTMENT_PATIENT_STATUS)));
                appointmentstatuspercent_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_APPOINTMENT_PATIENT_STATUS_PERCENT)));
                //byte[] blob = cursor.getBlob(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORY_IMAGE));
                //   bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                //   bitmaps.add(bitmap);

            } while (cursor.moveToNext());
        }

        historyAdapter = new History_ListAdapter(getActivity(),

                appointmentid_ArrayList,
                appointmentregistrationdate_ArrayList,
                appointmentdate_ArrayList,
                appointmentcauses_ArrayList,
                appointmentstatus_ArrayList,
                appointmentstatuspercent_ArrayList

                //  bitmaps

        );
        recyclerView.setAdapter(historyAdapter);

        cursor.close();
    }
}
