package com.geniusnine.android.geniusninelifecare.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.geniusnine.android.geniusninelifecare.Helper.Config;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.Helper.RequestHandler;
import com.geniusnine.android.geniusninelifecare.Helper.Utils;
import com.geniusnine.android.geniusninelifecare.Login_Patient.Patient_Login;
import com.geniusnine.android.geniusninelifecare.MainActivityDrawer;
import com.geniusnine.android.geniusninelifecare.Patient_Registration.Patient_Registration;
import com.geniusnine.android.geniusninelifecare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.bitmap;
import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Dev on 12-01-2017.
 */

public class Patient_Profile extends Fragment {
    ImageView uploadPatientProfilepicture;
    DBHelper dbHelper;
    Cursor cursor;
    private int PICK_IMAGE_REQUEST = 1;
    Button buttonupdatePatientDetails;
    TextView textViewPatientName;
    EditText edittextPatientname, edittextPatientmobilenumber, edittextpatientpassword, edittextPatientemail, edittextGender, edittextPatientage, edittextpatientheight, edittextpatientweight, edittextpatientbloodgroup, edittextpatientaddress, edittextpatientpincode;
    //boolean variable to check user is logged in or not
    //initially it is false
    private String patient_mobile_Number;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "MainActivityDrawer";
    String patient_id, patientname, patientmobilenumber, patientpassword, patientemail, patientgender, patientage, patientheight, patientweight, patientbloodgroup, patientaddress, patientpincode, patientregistrationdate;
    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Bitmap image = null;
    private ProgressDialog loading;
    private Bitmap bitmap;
    RequestHandler requestHandler;
    private Uri filePath;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.patient_profile, null);
        uploadPatientProfilepicture = (ImageView) v.findViewById(R.id.imageViewProfilePicture);
        buttonupdatePatientDetails = (Button) v.findViewById(R.id.buttonUpdateProfile);
        textViewPatientName = (TextView) v.findViewById(R.id.textViewPatientName);
        //edittext declaration
        edittextPatientname = (EditText) v.findViewById(R.id.editTextUserNameProfile);
        edittextPatientmobilenumber = (EditText) v.findViewById(R.id.editTextUserMobileProfile);
        edittextpatientpassword = (EditText) v.findViewById(R.id.editTextUserPasswordProfile);
        edittextPatientemail = (EditText) v.findViewById(R.id.editTextUserEmailProfile);
        edittextGender = (EditText) v.findViewById(R.id.editTextUserGenderProfile);
        edittextPatientage = (EditText) v.findViewById(R.id.editTextUserAgeProfile);
        edittextpatientheight = (EditText) v.findViewById(R.id.editTextUserHeightProfile);
        edittextpatientweight = (EditText) v.findViewById(R.id.editTextUserWeightProfile);
        edittextpatientbloodgroup = (EditText) v.findViewById(R.id.editTextUserBloodGroupProfile);
        edittextpatientaddress = (EditText) v.findViewById(R.id.editTextUserAddressProfile);
        edittextpatientpincode = (EditText) v.findViewById(R.id.editTextUserPincodeProfile);
        dbHelper = new DBHelper(getActivity());
        requestHandler = new RequestHandler();

        //loadImageFromDB();
        //fetching value from sharedpreference
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching thepatient_mobile_Number value form sharedpreferences
        patient_mobile_Number = sharedPreferences.getString(Config.PATIENT_MOBILE_NO_SHARED_PREF, null);

        getData(patient_mobile_Number);

       /* dbHelper.getPatientData(patient_mobile_Number);
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



        }*/
        uploadPatientProfilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openImageChooser();
                showFileChooser();
            }

        });





    buttonupdatePatientDetails.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        final String patientname, patientmobilenumber, patientpassword, patientemail, patientgender, patientage, patientheight, patientweight, patientbloodgroup, patientaddress, patientpincode, patientregistrationdate;
        patientname = edittextPatientname.getText().toString().trim();
        patientmobilenumber = edittextPatientmobilenumber.getText().toString().trim();
        patientpassword = edittextpatientpassword.getText().toString().trim();
        patientemail = edittextPatientemail.getText().toString().trim();
        patientgender = edittextGender.getText().toString().trim();
        patientage = edittextPatientage.getText().toString().trim();
        patientheight = edittextpatientheight.getText().toString().trim();
        patientweight = edittextpatientweight.getText().toString().trim();
        patientbloodgroup = edittextpatientbloodgroup.getText().toString().trim();
        patientaddress = edittextpatientaddress.getText().toString().trim();
        patientpincode = edittextpatientpincode.getText().toString().trim();

        String MobileNumberpattern = "[0-9]{10}";
        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        if (edittextPatientname.getText().toString().trim().equals("")) {
            edittextPatientname.setError("Name is Required");
        } else if (edittextPatientmobilenumber.getText().toString().trim().equals("")) {
            edittextPatientmobilenumber.setError("Mobile Number is Required");
        } else if (!edittextPatientmobilenumber.getText().toString().trim().matches(MobileNumberpattern)) {
            edittextPatientmobilenumber.setError("Please Enter Valid Mobile Number");
        } else if (edittextpatientpassword.getText().toString().trim().equals("")) {
            edittextpatientpassword.setError("Password is Required");
        } else if (!edittextpatientpassword.getText().toString().trim().matches(passpattern)) {
            edittextpatientpassword.setError("Password Contains One capital letter,One number,One symbol (@,$,%,#,)");
        } else if (!(edittextpatientpassword.getText().toString().trim().length() == 10)) {
            edittextpatientpassword.setError("Password size Should 10 Characters");
        } else if (edittextPatientemail.getText().toString().trim().equals("")) {
            edittextPatientemail.setError("Email id is Required");
        } else if (!edittextPatientemail.getText().toString().trim().matches(emailpattern)) {
            edittextPatientemail.setError("Please Enter Valid Email");
        } else if (edittextGender.getText().toString().trim().equals("")) {
            //  Toast.makeText(Patient_Registration.this,"Gender is Required",Toast.LENGTH_LONG).show();
            edittextPatientemail.setError("Please Gender is Required");
        } else if (edittextPatientage.getText().toString().trim().equals("")) {
            edittextPatientage.setError("Age is Required");
        } else if (edittextpatientheight.getText().toString().trim().equals("")) {
            edittextpatientheight.setError("Height is Required");
        } else if (edittextpatientweight.getText().toString().trim().equals("")) {
            edittextpatientweight.setError("Weight is Required");
        } else if (edittextpatientbloodgroup.getText().toString().trim().equals("")) {
            edittextpatientbloodgroup.setError("Blood Group is Required");
        } else if (edittextpatientaddress.getText().toString().trim().equals("")) {
            edittextpatientaddress.setError("Address is Required");
        } else if (edittextpatientpincode.getText().toString().trim().equals("")) {
            edittextpatientpincode.setError("Picode is Required");
        } else {
            String UPDATE_PROFILE_URL = Config.PATIENT_PROFILE_UPDATE_URL + patient_id;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_PROFILE_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.COLUMN_PATIENT_NAME, patientname);
                    params.put(Config.COLUMN_PATIENT_MOBILE, patientmobilenumber);
                    params.put(Config.COLUMN_PATIENT_PASSWORD, patientpassword);
                    params.put(Config.COLUMN_PATIENT_EMAIL, patientemail);
                    params.put(Config.COLUMN_PATIENT_GENDER, patientgender);
                    params.put(Config.COLUMN_PATIENT_AGE, patientage);
                    params.put(Config.COLUMN_PATIENT_HEIGHT, patientheight);
                    params.put(Config.COLUMN_PATIENT_WEIGHT, patientweight);
                    params.put(Config.COLUMN_PATIENT_BLOOD_GROUP, patientbloodgroup);
                    params.put(Config.COLUMN_PATIENT_ADDRESS, patientaddress);
                    params.put(Config.COLUMN_PATIENT_PINCODE, patientpincode);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
            dbHelper.UpdateProfileDetails(patient_id, patientname, patientmobilenumber, patientpassword, patientemail, patientgender, patientage, patientheight, patientweight, patientbloodgroup, patientaddress, patientpincode);
            Toast.makeText(getActivity(), "Patient Updated Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getActivity(), MainActivityDrawer.class);
            getActivity().finish();
            getActivity().startActivity(i);

        }


    }
    });

        return v;
    }


    private void getImage() {
        class GetImage extends AsyncTask<String,Void,Bitmap>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Loading Data...", null,true,true);
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);
                loading.dismiss();
                String uri = "@drawable/ic_account_circle_white_24dp";
                int imageResource = getResources().getIdentifier(uri, null,"com.geniusnine.android.geniusninelifecare");
                Drawable res = getResources().getDrawable(imageResource);
                if(image==null) {
                    uploadPatientProfilepicture.setImageDrawable(res);
                }
                else {
                    uploadPatientProfilepicture.setImageBitmap(image);
                }
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                //String id = params[0];
                String add = Config.PATIENT_PROFILE_FETCH_URL+patient_id;
                URL url = null;

                try {
                    url = new URL(add);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return image;
            }
        }

        GetImage gi = new GetImage();
        gi.execute(patient_id);
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }



    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getActivity(), "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(getActivity(), "portrait", Toast.LENGTH_SHORT).show();
        }
    }
    private void getData(String patient_mobile_Number) {

        //loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);

        String url = Config.PATIENT_PROFILE_URL+patient_mobile_Number;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             //   loading.dismiss();
               // Toast.makeText(getActivity(), "response"+response ,Toast.LENGTH_LONG).show();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            patient_id = collegeData.getString(Config.COLUMN_PATIENT_ID);
             getImage();
            patientname = collegeData.getString(Config.COLUMN_PATIENT_NAME);
            patientmobilenumber = collegeData.getString(Config.COLUMN_PATIENT_MOBILE);
            patientpassword = collegeData.getString(Config.COLUMN_PATIENT_PASSWORD);
            patientemail = collegeData.getString(Config.COLUMN_PATIENT_EMAIL);
            patientgender= collegeData.getString(Config.COLUMN_PATIENT_GENDER);
            patientage = collegeData.getString(Config.COLUMN_PATIENT_AGE);
            patientheight = collegeData.getString(Config.COLUMN_PATIENT_HEIGHT);
            patientweight = collegeData.getString(Config.COLUMN_PATIENT_WEIGHT);
            patientbloodgroup = collegeData.getString(Config.COLUMN_PATIENT_BLOOD_GROUP);
            patientaddress = collegeData.getString(Config.COLUMN_PATIENT_ADDRESS);
            patientpincode = collegeData.getString(Config.COLUMN_PATIENT_PINCODE);
            patientregistrationdate = collegeData.getString(Config.COLUMN_PATIENT_REGISRTION_DATE);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        textViewPatientName.setText("Welcome "+patientname);
        edittextPatientname.setText(patientname);
        edittextPatientmobilenumber.setText(patientmobilenumber);
        edittextpatientpassword.setText(patientpassword);
        edittextPatientemail.setText(patientemail);
        edittextGender.setText(patientgender);
        edittextPatientage.setText(patientage);
        edittextpatientheight.setText(patientheight);
        edittextpatientweight.setText(patientweight);
        edittextpatientbloodgroup.setText(patientbloodgroup);
        edittextpatientaddress.setText(patientaddress);
        edittextpatientpincode.setText(patientpincode);

    }
    private void uploadImage() {
        verifyStoragePermissions(getActivity());
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();
          String UPLOAD_PROFILE_PIC_URL=Config.UPLOAD_URL+patient_id;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Uploading Image", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();
                data.put(Config.UPLOAD_KEY, uploadImage);
                String result = rh.sendPostRequest(UPLOAD_PROFILE_PIC_URL, data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                uploadPatientProfilepicture.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loading != null) {
            loading.dismiss();
            loading = null;
        }
    }
  /*

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

// Choose an image from Gallery
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
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
    }*/
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

