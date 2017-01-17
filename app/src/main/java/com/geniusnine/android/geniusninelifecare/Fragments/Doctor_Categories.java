package com.geniusnine.android.geniusninelifecare.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Helper.Config;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.Helper.Utils;
import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.R;

import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dev on 12-01-2017.
 */

public class Doctor_Categories extends Fragment {
    DBHelper dbHelper;
    EditText name;
    Button button;
    ImageView uploadcategoryProfilepicture;
    private static final int SELECT_PICTURE = 100;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doctor_category, null);
        dbHelper = new DBHelper(getActivity());
        name=(EditText)v.findViewById(R.id.editTextUserName);
        uploadcategoryProfilepicture=(ImageButton)v.findViewById(R.id.imageViewProfilePicture);
        button=(Button)v.findViewById(R.id.buttonaddcategorysubmit);
        dbHelper=new DBHelper(getActivity());
        uploadcategoryProfilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorcategoryname, doctorscategoryimage;
                doctorcategoryname=name.getText().toString().trim();
                       // dbHelper.addUser(doctorcategoryname, );
                                Toast.makeText(getActivity(), "Category succssfully", Toast.LENGTH_LONG).show();
                //Intent i = new Intent(Patient_Registration.this, Patient_Login.class);
                // startActivity(i);
            }

        });

        return v;
    }
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
                        uploadcategoryProfilepicture .setImageURI(selectedImageUri);
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
    Boolean saveImageInDB(Uri selectedImageUri) {
        verifyStoragePermissions(getActivity());
        try {
            dbHelper.open();
            InputStream iStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
           // dbHelper.UpdateProfile(name,inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            //Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            dbHelper.close();
            return false;
        }

    }

    Boolean loadImageFromDB() {
        verifyStoragePermissions(getActivity());
        try {
            dbHelper.open();
            //byte[] bytes = dbHelper.retreiveImageFromDB(patient_id);
            dbHelper.close();
            // Show Image from DB in ImageView
            //uploadPatientProfilepicture .setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
           // Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }
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