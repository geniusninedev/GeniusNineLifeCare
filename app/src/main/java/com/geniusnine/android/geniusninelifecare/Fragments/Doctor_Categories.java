package com.geniusnine.android.geniusninelifecare.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
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
    Uri selectedImageUri;
    ImageView uploadcategoryProfilepicture;
    private static final int SELECT_PICTURE = 100;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    String doctorcategoryname;
    private static final String TAG = "MainActivityDrawer";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doctor_category, null);
        dbHelper = new DBHelper(getActivity());
        name=(EditText)v.findViewById(R.id.editTextUserName);
        uploadcategoryProfilepicture=(ImageView)v.findViewById(R.id.imageViewcategorypicture);
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
                doctorcategoryname = name.getText().toString().trim();
                if (null == selectedImageUri) {
                    Toast.makeText(getActivity(), "Please Select Image For Category...", Toast.LENGTH_LONG).show();
                }
               else if (name.getText().toString().trim().equals("")) {
                    name.setError("Category Name Required");
                } else {
                    if (null != selectedImageUri) {

                        // Saving to Database...
                        if (saveImageInDB(selectedImageUri)) {
                            // showMessage("Image Saved in Database...");
                            Toast.makeText(getActivity(), "Category Saved in Database...", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(getActivity(), MainActivityDrawer.class);
                            getActivity().finish();
                            getActivity().startActivity(i);
                        }

                    }


                }
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

                selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                        uploadcategoryProfilepicture .setImageURI(selectedImageUri);

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
            dbHelper.addCategory(doctorcategoryname,inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
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