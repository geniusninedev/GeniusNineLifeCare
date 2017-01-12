package com.geniusnine.android.geniusninelifecare.Fragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.geniusnine.android.geniusninelifecare.Adapters.SQLiteListAdapter;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev on 09-01-2017.
 */

public class Accounts extends Fragment {
    Button buttonAddAccounts;
    DBHelper dbHelper;
    ListView listView;
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> DESCRIPTION_ArrayList = new ArrayList<String>();
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;
   Spinner spinneraccounttype;
    // Array of strings...
 //   String[] mobileArray = {"मुंबई","ठाणे","पुणे","कोल्हापूर","नागपूर","नाशिक","सोलापूर","नांदेड","सातारा","सांगली","जळगाव","लातूर","औरंगाबाद","अमरावती","अकोला","परभणी","जालना","बुलढाणा","हिंगोली","गडचिरोली","चंद्रपूर","बीड","यवतमाळ","वर्धा","रत्नागिरी","रायगड","सिंधुदुर्ग","धुळे","नंदुरबार","वाशी","भंडारा","गोंदिया","उस्मानाबाद","अहमदनगर"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.accounts, null);
        dbHelper = new DBHelper(getActivity());
        buttonAddAccounts=(Button)v.findViewById(R.id. buttonAddAccounts);
        listView = (ListView) v.findViewById(R.id.listView2);
        ShowSQLiteDBdata() ;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String accountid;
                TextView textViewid=(TextView)view.findViewById(R.id.id);
                accountid=textViewid.getText().toString();

                LayoutInflater inflater =getActivity().getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog, null);
                final EditText edittextaccountname = (EditText) alertLayout.findViewById(R.id.edittextaccountname);
                final EditText edittextdescription = (EditText) alertLayout.findViewById(R.id.edittextdescription);
                spinneraccounttype=(Spinner)alertLayout.findViewById(R.id.spinneraccounttype);
                final EditText edittextopeingbalence = (EditText) alertLayout.findViewById(R.id.edittextopeingbalence);
                dbHelper.getAccountdetails(accountid);
                cursor = dbHelper.getAccountdetails(accountid);
                cursor.moveToFirst();
                if (cursor != null) {
                    /*String accountname = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_ACCOUNT_NAME));
                    edittextaccountname.setText(accountname);
                    String accountdescription = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_ACCOUNT_DESCRIPTION));
                    edittextdescription.setText(accountdescription);
                    // Loading spinner data from database
                    loadSpinnerData(accountid);
                    String openingbalence = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_OPENING_BALANCE));
                    edittextopeingbalence.setText(openingbalence);*/
                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Add Accounts");
                // this is set the view from XML inside AlertDialog
                alertDialogBuilder.setView(alertLayout);
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                String accountname=edittextaccountname.getText().toString().trim();
                                String accountdescription=edittextdescription.getText().toString().trim();
                                String accounttype=spinneraccounttype.getSelectedItem().toString().trim();
                                String openingbalence=edittextopeingbalence.getText().toString().trim();
                               // dbHelper.addAccounts(accountname,accountdescription,accounttype,openingbalence);
                                Toast.makeText(getActivity(),"Account Added Successfully",Toast.LENGTH_LONG).show();

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }

        });

        buttonAddAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater =getActivity().getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog, null);
                final EditText edittextaccountname = (EditText) alertLayout.findViewById(R.id.edittextaccountname);
                final EditText edittextdescription = (EditText) alertLayout.findViewById(R.id.edittextdescription);
                spinneraccounttype=(Spinner)alertLayout.findViewById(R.id.spinneraccounttype);
                // Spinner Drop down elements
                List<String> categories = new ArrayList<String>();
                categories.add("Income");
                categories.add("Expense");

                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spinneraccounttype.setAdapter(dataAdapter);
                final EditText edittextopeingbalence = (EditText) alertLayout.findViewById(R.id.edittextopeingbalence);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Add Accounts");
                // this is set the view from XML inside AlertDialog
                alertDialogBuilder.setView(alertLayout);
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                String accountname=edittextaccountname.getText().toString().trim();
                                String accountdescription=edittextdescription.getText().toString().trim();
                                String accounttype=spinneraccounttype.getSelectedItem().toString().trim();
                                String openingbalence=edittextopeingbalence.getText().toString().trim();
                              //  dbHelper.addAccounts(accountname,accountdescription,accounttype,openingbalence);
                                Toast.makeText(getActivity(),"Account Added Successfully",Toast.LENGTH_LONG).show();

                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void loadSpinnerData(String accountid) {
        // database handler
        DBHelper db = new DBHelper(getActivity());

        // Spinner Drop down elements
        List<String> lables = db.getAccounttype(accountid);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinneraccounttype.setAdapter(dataAdapter);
    }

    private void ShowSQLiteDBdata() {

       // cursor = dbHelper.showAccounts();
        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        DESCRIPTION_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
             /*   ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_ACCOUNT_ID)));


                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_ACCOUNT_NAME)));
                DESCRIPTION_ArrayList.add(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_ACCOUNT_DESCRIPTION)));*/

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