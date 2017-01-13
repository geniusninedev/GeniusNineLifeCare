package com.geniusnine.android.geniusninelifecare.Helper;

/**
 * Created by Dev on 13-01-2017.
 */

public class Config {
    /*//URL to our login.php file
    public static final String LOGIN_URL = "http://192.168.94.1/Android/LoginLogout/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";*/

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "geniusninelifecareapp";

    //This would be used to store the email of current logged in user
    public static final String PATIENT_EMAIL_SHARED_PREF = "patient_email";
    //This would be used to store the mobile of current logged in user
    public static final String PATIENT_MOBILE_NO_SHARED_PREF = "patient_mobile";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String PATIENT_LOGGEDIN_SHARED_PREF = "loggedin";
}
