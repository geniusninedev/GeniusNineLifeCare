package com.geniusnine.android.geniusninelifecare.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.geniusnine.android.geniusninelifecare.Helper.DBHelper;
import com.geniusnine.android.geniusninelifecare.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Dev on 12-01-2017.
 */

public class Doctor_Registraion extends Fragment {
    DBHelper dbHelper;
    EditText editTextname,editTextmobile,editTextemail,editTextdegree,editTextspecialization,editTextexperience,editTextachievements,editTextconnectedhospitals,editTextavailabletimings,
            editTextfax,editTexttwitter,editTextfacebook,editTextpassword,editTextfees,editTextgender,editTextage,editTextaddress;
    TextView textViewcurrentdate;
    final Calendar cal = Calendar.getInstance();
    String myFormat = "yyyy-MM-DD"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    Button buttonsubmitdoctor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doctor_registration_form, null);
        dbHelper = new DBHelper(getActivity());
        buttonsubmitdoctor=(Button)v.findViewById(R.id.buttondoctorsubmit);
        textViewcurrentdate=(TextView)v.findViewById(R.id.textViewcurrentDate);
        editTextname=(EditText)v.findViewById(R.id.edittextdoctorname);
        editTextmobile=(EditText)v.findViewById(R.id.edittextdoctormobile);
        editTextemail=(EditText)v.findViewById(R.id.edittextdoctoremail);
        editTextdegree=(EditText)v.findViewById(R.id.edittextdoctordegree);
        editTextspecialization=(EditText)v.findViewById(R.id.edittextdoctorspecialization);
        editTextexperience=(EditText)v.findViewById(R.id.edittextdoctorexperience);
        editTextachievements=(EditText)v.findViewById(R.id.edittextdoctorAchievements);
        editTextconnectedhospitals=(EditText)v.findViewById(R.id.edittextdoctorconnectedhospitals);
        editTextavailabletimings=(EditText)v.findViewById(R.id.edittextdoctoravailabletimings);
        editTextfax=(EditText)v.findViewById(R.id.edittextdoctorfax);
        editTexttwitter=(EditText)v.findViewById(R.id.edittextdoctortwitter);
        editTextfacebook=(EditText)v.findViewById(R.id.edittextdoctorfacebook);
        editTextpassword=(EditText)v.findViewById(R.id.edittextdoctorpassword);
        editTextfees=(EditText)v.findViewById(R.id.edittextdoctorfees);
        editTextgender=(EditText)v.findViewById(R.id.edittextdoctorgender);
        editTextage=(EditText)v.findViewById(R.id.edittextdoctorage);
        editTextaddress=(EditText)v.findViewById(R.id.edittextdoctoraddress);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }

        };
        buttonsubmitdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorname,doctormobile,doctoremail,doctordegree,doctorspecialization,doctorexperience,doctorachievements,doctorconnectedhospitals,doctoravailabletimings,
                        doctorfax,doctortwitter,doctorfacebook,doctorpassword,doctorfees,doctorgender,doctorage,doctoraddress,doctorcurrentdate;
                doctorname = editTextname.getText().toString();
                doctormobile = editTextmobile.getText().toString();
                doctoremail = editTextemail.getText().toString();
                doctordegree = editTextdegree.getText().toString();
                doctorspecialization = editTextspecialization.getText().toString();
                doctorexperience = editTextexperience.getText().toString();
                doctorachievements = editTextachievements.getText().toString();
                doctorconnectedhospitals =editTextconnectedhospitals .getText().toString();
                doctoravailabletimings = editTextavailabletimings.getText().toString();
                doctorfax = editTextfax.getText().toString();
                doctortwitter = editTexttwitter.getText().toString();
                doctorfacebook = editTextfacebook.getText().toString();
                doctorpassword = editTextpassword.getText().toString();
                doctorfees = editTextfees.getText().toString();
                doctorgender = editTextgender.getText().toString();
                doctorage = editTextage.getText().toString();
                doctoraddress = editTextaddress.getText().toString();
                doctorcurrentdate=textViewcurrentdate.getText().toString().trim();
                String MobileNumberpattern = "[0-9]{10}";
                String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
                if(editTextname.getText().toString().trim().equals("")){
                    editTextname.setError("Name is Required");
                }else if(editTextmobile.getText().toString().trim().equals("")){
                    editTextmobile.setError("Mobile Number is Required");
                }else if(!editTextmobile.getText().toString().trim().matches(MobileNumberpattern)){
                    editTextmobile.setError("Please Enter Valid Mobile Number");
                } else if(editTextpassword.getText().toString().trim().equals("")){
                    editTextpassword.setError("Password is Required");
                } else if(!editTextpassword.getText().toString().trim().matches(passpattern)){
                    editTextpassword.setError("Password Contains One capital letter,One number,One symbol (@,$,%,#,)");
                }else if(!(editTextpassword.getText().toString().trim().length() ==10)){
                    editTextpassword.setError("Password size Should 10 Characters");
                } else if(editTextemail.getText().toString().trim().equals("")){
                    editTextemail.setError("Email id is Required");
                }else if(!editTextemail.getText().toString().trim().matches(emailpattern)){
                    editTextemail.setError("Please Enter Valid Email");
                } else if(editTextdegree.getText().toString().trim().equals("")){
                    editTextdegree.setError("Degree is Required");
                }else if(editTextspecialization.getText().toString().trim().equals("")){
                    editTextspecialization.setError("Specialization is Required");
                }else if(editTextexperience.getText().toString().trim().equals("")){
                    editTextexperience.setError("Experience is Required");
                }else if(editTextachievements.getText().toString().trim().equals("")){
                    editTextachievements.setError("Achievemnets is Required");
                }else if(editTextconnectedhospitals.getText().toString().trim().equals("")){
                    editTextconnectedhospitals.setError("Connecting Hospitals is Required");
                }else if(editTextavailabletimings.getText().toString().trim().equals("")){
                    editTextavailabletimings.setError("Available Timings is Required");
                }else if(editTextfax.getText().toString().trim().equals("")){
                    editTextfax.setError("Fax is Required");
                }else if(editTextfacebook.getText().toString().trim().equals("")){
                    editTextfacebook.setError("Facebook URL is Required");
                }else if(editTexttwitter.getText().toString().trim().equals("")){
                    editTexttwitter.setError("Twitter URL is Required");
                }else if(editTextfees.getText().toString().trim().equals("")){
                    editTextfees.setError("Fees is Required");
                }else if(editTextage.getText().toString().trim().equals("")){
                    editTextage.setError("Age is Required");
                }else if(editTextaddress.getText().toString().trim().equals("")){
                    editTextaddress.setError("Address is Required");
                }else if(editTextgender.getText().toString().trim().equals("")){
                    editTextgender.setError("Gender is Required");
                }
                else{
                    dbHelper.addDoctor(doctorname, doctormobile, doctoremail, doctordegree,doctorspecialization,doctorexperience,doctorachievements,doctorconnectedhospitals,doctoravailabletimings,doctorfax,doctortwitter,doctorfacebook,doctorpassword,doctorfees,doctorgender,doctorage,doctoraddress,doctorcurrentdate);
                    Toast.makeText(getActivity(), "Registered succssfully", Toast.LENGTH_LONG).show();
                }

            }
        });
        return  v;
    }
}