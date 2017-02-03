package com.geniusnine.android.geniusninelifecare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Fragments.About_Us;
import com.geniusnine.android.geniusninelifecare.Fragments.Add_Health_and_Tips;
import com.geniusnine.android.geniusninelifecare.Fragments.Add_Labs;
import com.geniusnine.android.geniusninelifecare.Fragments.Add_Medicines;
import com.geniusnine.android.geniusninelifecare.Fragments.Contact_US;
import com.geniusnine.android.geniusninelifecare.Fragments.Doctor_Categories;
import com.geniusnine.android.geniusninelifecare.Fragments.Doctor_Registraion;
import com.geniusnine.android.geniusninelifecare.Fragments.Doctor_info;
import com.geniusnine.android.geniusninelifecare.Fragments.Feedback;
import com.geniusnine.android.geniusninelifecare.Fragments.Health_and_Tips;
import com.geniusnine.android.geniusninelifecare.Fragments.Medicines;
import com.geniusnine.android.geniusninelifecare.Fragments.Patient_Book_Appointment;
import com.geniusnine.android.geniusninelifecare.Fragments.Patient_Home;
import com.geniusnine.android.geniusninelifecare.Fragments.Patient_Order;
import com.geniusnine.android.geniusninelifecare.Fragments.Patient_Profile;
import com.geniusnine.android.geniusninelifecare.Helper.Config;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.Helper.Utils;
import com.geniusnine.android.geniusninelifecare.Login_Patient.Patient_Login;


public class MainActivityDrawer extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    DBHelper dbHelper;
    ImageView profilePictureView;
    private static final String TAG = "MainActivityDrawer";
    Cursor cursor;
    private String patient_mobile_Number;
    String patient_id,patientname,patientmobilenumber,patientemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawermain);
        dbHelper=new DBHelper(MainActivityDrawer.this);

        //fetching value from sharedpreference
        SharedPreferences sharedPreferences =getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching thepatient_mobile_Number value form sharedpreferences
        patient_mobile_Number = sharedPreferences.getString(Config.PATIENT_MOBILE_NO_SHARED_PREF,null);

        /**
         *Setup the DrawerLayout and NavigationView
         */


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        TextView Name = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.name);
        TextView email = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.email);
        profilePictureView = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.imageView);
        dbHelper.getPatientData(patient_mobile_Number);
        cursor = dbHelper.getPatientData(patient_mobile_Number);
        cursor.moveToFirst();
        if (cursor != null) {
                       patient_id = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_ID));
            patientname = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_NAME));
            Name.setText(patientname);
           /* patientmobilenumber = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_MOBILE));
            edittextPatientmobilenumber.setText(patientmobilenumber);*/
            patientemail = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_EMAIL));
            email.setText(patientemail);

        }
        loadImageFromDB();
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mNavigationView.setItemIconTintList(null);
        mFragmentTransaction.replace(R.id.containerView, new Patient_Home()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.Home) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Patient_Home()).commit();

                }
                if (menuItem.getItemId() == R.id.Inbox) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Patient_Profile()).commit();
                }
                if (menuItem.getItemId() == R.id.My_Orders) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Patient_Order()).commit();

                }
                if (menuItem.getItemId() == R.id.Medicines) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Medicines()).commit();

                }
                if (menuItem.getItemId() == R.id.Add_Medicines) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Add_Medicines()).commit();

                }
                if (menuItem.getItemId() == R.id.Health_And_Tips) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Health_and_Tips()).commit();

                }
                if (menuItem.getItemId() == R.id.Add_Health_And_Tips){
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Add_Health_and_Tips()).commit();

                }if (menuItem.getItemId() == R.id.Add_Labs) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Add_Labs()).commit();

                }
                if (menuItem.getItemId() == R.id.Book_Appointment) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Patient_Book_Appointment()).commit();

                }
                if (menuItem.getItemId() == R.id.Add_Categories) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Doctor_Categories()).commit();

                }

                if (menuItem.getItemId() == R.id.Add_Doctor) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Doctor_Registraion()).commit();

                }
                if (menuItem.getItemId() == R.id.Doctor_info) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Doctor_info()).commit();

                }
                if (menuItem.getItemId() == R.id.Feedback) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Feedback()).commit();

                }

                if (menuItem.getItemId() == R.id.About_Us) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new About_Us()).commit();

                }
                if (menuItem.getItemId() == R.id.Contact_Us) {
                    loadImageFromDB();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Contact_US()).commit();

                }
                if (menuItem.getItemId() == R.id.Logout) {
                    logout();
                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();


    }
    Boolean loadImageFromDB() {
        try {
            dbHelper.open();
            byte[] bytes = dbHelper.retreiveImageFromDB(patient_id);
            dbHelper.close();
            // Show Image from DB in ImageView
            profilePictureView.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, new Patient_Profile()).commit();
            return true;
        }
      /*   //noinspection SimplifiableIfStatement
        if (id == R.id.action_chart) {
            Toast.makeText(getApplication(),"Pia Chart Clicked clicked",Toast.LENGTH_LONG).show();
            return true;
        }*/
     /*   //noinspection SimplifiableIfStatement
        if (id == R.id.action_transfer) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_export) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_import) {
            return true;
        }*/
        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_logout) {
            logout();
            return true;
        }*/
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.PATIENT_LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.PATIENT_MOBILE_NO_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MainActivityDrawer.this, Patient_Login.class);
                        finish();
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                /*Intent i=new Intent(this,Splash_Screen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);*/
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure you want to close App?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                finish();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}