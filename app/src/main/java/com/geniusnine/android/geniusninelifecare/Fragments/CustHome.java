package com.geniusnine.android.geniusninelifecare.Fragments;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Pravin on 7/28/2016.
 */
public class CustHome extends Fragment {
    // Array of strings...
    TextView textViewCurrentbalence,textViewTodaysexpense,textViewWeekexpense,textViewMonthexpense;
    Spinner spinnercategories,spinneraccountname;
    EditText editTextdescription,editTexthowmuch;
    Button buttondate,buttonaddexpense,buttonclearexpense;
    DBHelper dbHelper;
    final Calendar cal = Calendar.getInstance();
    Cursor cursor,cursortoday,cursorweek,cursormonth;
    String weekdate,monthdate;
    String myFormat = "yyyy-MM-DD"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    String[] mobileArray = {"मुंबई","ठाणे","पुणे","कोल्हापूर","नागपूर","नाशिक","सोलापूर","नांदेड","सातारा","सांगली","जळगाव","लातूर","औरंगाबाद","अमरावती","अकोला","परभणी","जालना","बुलढाणा","हिंगोली","गडचिरोली","चंद्रपूर","बीड","यवतमाळ","वर्धा","रत्नागिरी","रायगड","सिंधुदुर्ग","धुळे","नंदुरबार","वाशी","भंडारा","गोंदिया","उस्मानाबाद","अहमदनगर"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_home_page, null);
       /** Get the current date */
        // Loading spinner data from database  

        buttondate=(Button)v.findViewById(R.id.buttondate);
        textViewCurrentbalence=(TextView)v.findViewById(R.id.textViewCurrentbalence);
        textViewTodaysexpense=(TextView)v.findViewById(R.id.textViewTodaysexpense);
        textViewWeekexpense=(TextView)v.findViewById(R.id.textViewWeekexpense);
        textViewMonthexpense=(TextView)v.findViewById(R.id.textViewMonthexpense);
        editTextdescription=(EditText)v.findViewById(R.id.editTextdescription);
        editTexthowmuch=(EditText)v.findViewById(R.id.editTexthowmuch);
        spinnercategories=(Spinner)v.findViewById(R.id.spinnercategories);
        spinneraccountname=(Spinner)v.findViewById(R.id.spinneraccountname);
        buttonaddexpense=(Button)v.findViewById(R.id.buttonaddexpense);
        buttonclearexpense=(Button)v.findViewById(R.id.buttonclearexpense);
        loadSpinnerData();
        loadAccountsData();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        buttondate.setText(sdf.format(cal.getTime()));
          getweekdate();
          getmonthdate();
        buttondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, cal
                        .get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
             spinneraccountname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     String Accountname = spinneraccountname.getSelectedItem().toString().trim();
                   /*  dbHelper.getCurrentBalence(Accountname);
                     cursor = dbHelper.getCurrentBalence(Accountname);*/
                     cursor.moveToFirst();
                     if (cursor != null) {
                        /* String currentbalence = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_AVAILABLE_BALANCE));
                         textViewCurrentbalence.setText(currentbalence);*/
                     }
                     String date=buttondate.getText().toString().trim();
                     dbHelper.getTotalTransationAmountToday(Accountname,date);
                     cursortoday = dbHelper.getTotalTransationAmountToday(Accountname,date);
                     cursortoday.moveToFirst();
                     if (cursortoday != null) {
                         String Totalexpensetoday = cursortoday.getString(0);
                         textViewTodaysexpense.setText(Totalexpensetoday);

                     }
                     String date1=buttondate.getText().toString().trim();
                     dbHelper.getTotalTransationAmountWeek(Accountname,date1,weekdate);
                     cursorweek = dbHelper.getTotalTransationAmountWeek(Accountname,date,weekdate);
                     cursorweek.moveToFirst();
                     if (cursorweek != null) {
                         String Totalexpenseweek = cursorweek.getString(0);
                         textViewWeekexpense.setText(Totalexpenseweek);
                     }
                     dbHelper.getTotalTransationAmountMonth(Accountname,date,monthdate);
                     cursormonth = dbHelper.getTotalTransationAmountMonth(Accountname,date,monthdate);
                     cursormonth.moveToFirst();
                     if (cursormonth != null) {
                         String Totalexpensemonth = cursormonth.getString(0);
                         textViewMonthexpense.setText(Totalexpensemonth);
                     }
                 }
                 @Override
                 public void onNothingSelected(AdapterView<?> parent) {

                 }
             });
         buttonaddexpense.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String categorie_name,account_name,Description,howmuch,date,currentbalence,totalbalence;
               int balence,debit,total;
               categorie_name=spinnercategories.getSelectedItem().toString().trim();
               account_name=spinneraccountname.getSelectedItem().toString().trim();
               Description=editTextdescription.getText().toString().trim();
               howmuch=editTexthowmuch.getText().toString().trim();
               date=buttondate.getText().toString().trim();
               currentbalence=textViewCurrentbalence.getText().toString().trim();
               balence= Integer.parseInt(currentbalence);
               debit=Integer.parseInt(howmuch);
               total=balence-debit;
               totalbalence= String.valueOf(total);
               if(editTextdescription.getText().toString().trim().equals("") ){
                   editTextdescription.setError("Description is Required");
               }else if(editTexthowmuch.getText().toString().trim().equals("")) {
                   editTexthowmuch.setError("How Much Amount is Required");
               }
               else {
                   /*dbHelper.addExpenses(categorie_name, account_name, Description, howmuch, date);
                   dbHelper.addTransation(account_name,totalbalence)*/;
                   Toast.makeText(getActivity(), "Expense Added Successfully", Toast.LENGTH_LONG).show();
               }
           }
       });
        buttonclearexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextdescription.setText("");
                editTexthowmuch.setText("");
            }
        });
        return v;
    }

    private void getmonthdate() {
        //month date
        cal.add(Calendar.DATE, -30);
        monthdate=  sdf.format(cal.getTime());
    }

    private void getweekdate() {
        //week date
        cal.add(Calendar.DATE, -7);
        weekdate= sdf.format(cal.getTime());
    }

    private void updateLabel() {
        buttondate.setText(sdf.format(cal.getTime()));
    }
    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        dbHelper = new DBHelper(getActivity());
      //  List<String> labels = dbHelper.getAllLabels();

        // Creating adapter for spinner
      //  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
       // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
      //  spinnercategories.setAdapter(dataAdapter);
    }
    private void loadAccountsData() {
        dbHelper = new DBHelper(getActivity());
      //  List<String> labels = dbHelper.getAllAccounts();

        // Creating adapter for spinner
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
       // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        //spinneraccountname.setAdapter(dataAdapter);
    }
}
