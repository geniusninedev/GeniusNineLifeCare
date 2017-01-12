package com.geniusnine.android.geniusninelifecare.Fragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Adapters.SQLiteListAdapter;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;


/**
 * Created by Dev on 09-01-2017.
 */

public class Categories extends Fragment {
    Button buttonAddCategories;
    DBHelper dbHelper;
    ListView listView;
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> DESCRIPTION_ArrayList = new ArrayList<String>();
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;
    // Array of strings...
    String[] mobileArray = {"मुंबई","ठाणे","पुणे","कोल्हापूर","नागपूर","नाशिक","सोलापूर","नांदेड","सातारा","सांगली","जळगाव","लातूर","औरंगाबाद","अमरावती","अकोला","परभणी","जालना","बुलढाणा","हिंगोली","गडचिरोली","चंद्रपूर","बीड","यवतमाळ","वर्धा","रत्नागिरी","रायगड","सिंधुदुर्ग","धुळे","नंदुरबार","वाशी","भंडारा","गोंदिया","उस्मानाबाद","अहमदनगर"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, null);
        dbHelper = new DBHelper(getActivity());
        buttonAddCategories=(Button)v.findViewById(R.id. buttonAddCategories);
        listView = (ListView) v.findViewById(R.id.listView2);
        ShowSQLiteDBdata() ;
        buttonAddCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("ADD CATEGORIES");
                alertDialogBuilder.setMessage("Enter Categories Name" );
                final EditText edittext = new EditText(getContext());
                alertDialogBuilder.setView(edittext);
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                String categories=edittext.getText().toString().trim();
                                dbHelper.addCategories(categories);
                                Toast.makeText(getActivity(),"Categories Added Successfully",Toast.LENGTH_LONG).show();

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void ShowSQLiteDBdata() {

        cursor = dbHelper.showcategories();
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        DESCRIPTION_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORIE_ID)));

                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORIE_NAME)));
                DESCRIPTION_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_CATEGORIE_COLOUR)));

            } while (cursor.moveToNext());
        }

        ListAdapter = new SQLiteListAdapter(getActivity(),

                ID_ArrayList,
                NAME_ArrayList,
                DESCRIPTION_ArrayList

        );
        listView.setAdapter(ListAdapter);

        cursor.close();
    }

}