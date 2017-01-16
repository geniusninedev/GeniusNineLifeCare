package com.geniusnine.android.geniusninelifecare.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Helper.Config;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.Helper.Utils;
import com.geniusnine.android.geniusninelifecare.Login_Patient.Patient_Login;
import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.Patient_Registration.Patient_Registration;
import com.geniusnine.android.geniusninelifecare.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Profile extends Fragment {
    ImageView uploadPatientProfilepicture;
    DBHelper dbHelper;
    Cursor cursor;
    Button buttonupdatePatientDetails;
    TextView textViewPatientName;
    EditText edittextPatientname,edittextPatientmobilenumber,edittextpatientpassword,edittextPatientemail,edittextGender,edittextPatientage,edittextpatientheight,edittextpatientweight,edittextpatientbloodgroup,edittextpatientaddress,edittextpatientpincode;
    //boolean variable to check user is logged in or not
    //initially it is false
    private String patient_mobile_Number;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "MainActivityDrawer";
    String patient_id,patientname,patientmobilenumber,patientpassword,patientemail,patientgender,patientage,patientheight,patientweight,patientbloodgroup,patientaddress,patientpincode,patientregistrationdate;
    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.patient_profile, null);
        uploadPatientProfilepicture = (ImageView) v.findViewById(R.id.imageViewProfilePicture);
        buttonupdatePatientDetails=(Button)v.findViewById(R.id.buttonUpdateProfile);
        textViewPatientName=(TextView)v.findViewById(R.id.textViewPatientName);
        //edittext declaration
        edittextPatientname = (EditText)v.findViewById(R.id.editTextUserNameProfile);
        edittextPatientmobilenumber = (EditText)v.findViewById(R.id.editTextUserMobileProfile);
        edittextpatientpassword = (EditText)v.findViewById(R.id.editTextUserPasswordProfile);
        edittextPatientemail = (EditText) v.findViewById(R.id.editTextUserEmailProfile);
        edittextGender=(EditText) v.findViewById(R.id.editTextUserGenderProfile);
        edittextPatientage = (EditText)v.findViewById(R.id. editTextUserAgeProfile);
        edittextpatientheight = (EditText)v. findViewById(R.id.editTextUserHeightProfile);
        edittextpatientweight = (EditText)v.findViewById(R.id.editTextUserWeightProfile);
        edittextpatientbloodgroup = (EditText)v.findViewById(R.id.editTextUserBloodGroupProfile);
        edittextpatientaddress = (EditText) v.findViewById(R.id.editTextUserAddressProfile);
        edittextpatientpincode=(EditText) v.findViewById(R.id. editTextUserPincodeProfile);
        dbHelper=new DBHelper(getActivity());
        loadImageFromDB();
        //fetching value from sharedpreference
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching thepatient_mobile_Number value form sharedpreferences
        patient_mobile_Number = sharedPreferences.getString(Config.PATIENT_MOBILE_NO_SHARED_PREF,null);
        dbHelper.getPatientData(patient_mobile_Number);
        cursor = dbHelper.getPatientData(patient_mobile_Number);
        cursor.moveToFirst();
        if (cursor != null) {
            patient_id = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_ID));
            patientname = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_NAME));
            textViewPatientName.setText("Welcome "+patientname);
            edittextPatientname.setText(patientname);
            patientmobilenumber = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_MOBILE));
            edittextPatientmobilenumber.setText(patientmobilenumber);
            patientpassword = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_PASSWORD));
            edittextpatientpassword.setText(patientpassword);
            patientemail = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_EMAIL));
            edittextPatientemail.setText(patientemail);
            patientgender = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_GENDER));
            edittextGender.setText(patientgender);
            patientage = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_AGE));
            edittextPatientage.setText(patientage);
            patientheight = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_HEIGHT));
            edittextpatientheight.setText(patientheight);
            patientweight = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_WEIGHT));
            edittextpatientweight.setText(patientweight);
            patientbloodgroup = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_BLOOD_GROUP));
            edittextpatientbloodgroup.setText(patientbloodgroup);
            patientaddress = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_ADDRESS));
            edittextpatientaddress.setText(patientaddress);
            patientpincode = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_PINCODE));
            edittextpatientpincode.setText(patientpincode);
            patientregistrationdate = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATIENT_REGISRTION_DATE));



        }
        uploadPatientProfilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        buttonupdatePatientDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientname,patientmobilenumber,patientpassword,patientemail,patientgender,patientage,patientheight,patientweight,patientbloodgroup,patientaddress,patientpincode,patientregistrationdate;
                patientname=edittextPatientname.getText().toString().trim();
                patientmobilenumber=edittextPatientmobilenumber.getText().toString().trim();
                patientpassword=edittextpatientpassword.getText().toString().trim();
                patientemail=edittextPatientemail.getText().toString().trim();
                patientgender=edittextGender.getText().toString().trim();
                patientage=edittextPatientage.getText().toString().trim();
                patientheight=edittextpatientheight.getText().toString().trim();
                patientweight=edittextpatientweight.getText().toString().trim();
                patientbloodgroup=edittextpatientbloodgroup.getText().toString().trim();
                patientaddress=edittextpatientaddress.getText().toString().trim();
                patientpincode=edittextpatientpincode.getText().toString().trim();

                String MobileNumberpattern = "[0-9]{10}";
                String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                if(edittextPatientname.getText().toString().trim().equals("")){
                    edittextPatientname.setError("Name is Required");
                }else if(edittextPatientmobilenumber.getText().toString().trim().equals("")){
                    edittextPatientmobilenumber.setError("Mobile Number is Required");
                }else if(!edittextPatientmobilenumber.getText().toString().trim().matches(MobileNumberpattern)){
                    edittextPatientmobilenumber.setError("Please Enter Valid Mobile Number");
                } else if(edittextpatientpassword.getText().toString().trim().equals("")){
                    edittextpatientpassword.setError("Password is Required");
                } else if(!edittextpatientpassword.getText().toString().trim().matches(passpattern)){
                    edittextpatientpassword.setError("Password Contains One capital letter,One number,One symbol (@,$,%,#,)");
                }else if(!(edittextpatientpassword.getText().toString().trim().length() ==10)){
                    edittextpatientpassword.setError("Password size Should 10 Characters");
                }
                else if(edittextPatientemail.getText().toString().trim().equals("")){
                    edittextPatientemail.setError("Email id is Required");
                }else if(!edittextPatientemail.getText().toString().trim().matches(emailpattern)){
                    edittextPatientemail.setError("Please Enter Valid Email");
                } else if(edittextGender.getText().toString().trim().equals("")){
                  //  Toast.makeText(Patient_Registration.this,"Gender is Required",Toast.LENGTH_LONG).show();
                    edittextPatientemail.setError("Please Gender is Required");
                }else if(edittextPatientage.getText().toString().trim().equals("")){
                    edittextPatientage.setError("Age is Required");
                }else if(edittextpatientheight.getText().toString().trim().equals("")){
                    edittextpatientheight.setError("Height is Required");
                }else if(edittextpatientweight.getText().toString().trim().equals("")){
                    edittextpatientweight.setError("Weight is Required");
                }else if(edittextpatientbloodgroup.getText().toString().trim().equals("")){
                    edittextpatientbloodgroup.setError("Blood Group is Required");
                }else if(edittextpatientaddress.getText().toString().trim().equals("")){
                    edittextpatientaddress.setError("Address is Required");
                }else if(edittextpatientpincode.getText().toString().trim().equals("")){
                    edittextpatientpincode.setError("Picode is Required");
                }
                else{
                    dbHelper.UpdateProfileDetails(patient_id,patientname,patientmobilenumber,patientpassword,patientemail,patientgender,patientage,patientheight,patientweight,patientbloodgroup,patientaddress,patientpincode);
                    Toast.makeText(getActivity(),"Patient Updated Successfully",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getActivity(), MainActivityDrawer.class);
                    getActivity().finish();
                    getActivity().startActivity(i);

                }


            }
        });

        return v;
    }
    // Choose an image from Gallery
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {

                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // Saving to Database...
                    if (saveImageInDB(selectedImageUri)) {
                       // showMessage("Image Saved in Database...");
                        Toast.makeText(getActivity(),"Image Saved in Database...",Toast.LENGTH_LONG).show();
                        uploadPatientProfilepicture .setImageURI(selectedImageUri);
                    }

                    // Reading from Database after 3 seconds just to show the message
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loadImageFromDB()) {
                                Toast.makeText(getActivity(),"Image Loaded from Database...",Toast.LENGTH_LONG).show();
                              //  showMessage("Image Loaded from Database...");
                            }
                        }
                    }, 3000);
                }

            }
        }
    }


    // Save the
    Boolean saveImageInDB(Uri selectedImageUri) {
        verifyStoragePermissions(getActivity());
        try {
            dbHelper.open();
            InputStream iStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            dbHelper.UpdateProfile(patient_id,inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            dbHelper.close();
            return false;
        }

    }

    Boolean loadImageFromDB() {
        verifyStoragePermissions(getActivity());
        try {
            dbHelper.open();
            byte[] bytes = dbHelper.retreiveImageFromDB(patient_id);
            dbHelper.close();
            // Show Image from DB in ImageView
            uploadPatientProfilepicture .setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }
    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}

