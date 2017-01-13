package com.geniusnine.android.geniusninelifecare.Fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.Helper.Utils;
import com.geniusnine.android.geniusninelifecare.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Profile extends Fragment {
    private static final int SELECT_PICTURE = 100;
    ImageView uploadPatientProfilepicture;
    DBHelper dbHelper;
    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.patient_profile, null);

        uploadPatientProfilepicture = (ImageView)v.findViewById(R.id.imageViewProfilePicture);

        uploadPatientProfilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
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
                        Toast.makeText(getActivity(),"Image Saved in Database...",Toast.LENGTH_LONG).show();
                        uploadPatientProfilepicture.setImageURI(selectedImageUri);
                    }

                    // Reading from Database after 3 seconds just to show the message
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (loadImageFromDB()) {
                                Toast.makeText(getActivity(),"Image Loaded from Database...",Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 3000);
                }

            }
        }
    }
    // Save the
    Boolean saveImageInDB(Uri selectedImageUri) {
        try {
            dbHelper.open();
            InputStream iStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            dbHelper.UpdateProfile(1,inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
           dbHelper.close();
            return false;
        }

    }

    Boolean loadImageFromDB() {
        try {
            dbHelper.open();
            byte[] bytes = dbHelper.retreiveImageFromDB(1);
            dbHelper.close();
            // Show Image from DB in ImageView
            uploadPatientProfilepicture.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }
}
