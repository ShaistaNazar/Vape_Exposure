package com.example.dell.notifyyourself;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    static SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "NotifyYourselfPref";

    // All Shared Preferences Keys
    public static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_SIGNUP= "IsSignedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_MOBILE_NO = "mobile no.";
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
`     * */
    public void create_S_Session(String name,String email,String location,String phone, String password){
        // Storing login value as TRUE

        editor.putBoolean(IS_SIGNUP, true);
        // Storing name in pref
        editor.putString(KEY_NAME, name);
        // Storing email in pref
        editor.putString(KEY_EMAIL, name);
        //storing location
        editor.putString(KEY_LOCATION, name);
        //storing mobile no.
        editor.putString(KEY_MOBILE_NO, name);
        editor.putString(KEY_PASSWORD, password);
        // commit changes
        editor.commit();
    }

    public void create_L_Session(){
        // Storing login value as TRUE

        editor.putBoolean(IS_LOGIN, true);
        // commit changes
        editor.apply();
    }
    public void create_LO_Session(){
        // Storing login value as TRUE

        editor.putBoolean(IS_LOGIN, false);
        // commit changes
        editor.apply();
    }
    public void create_SO_Session(){
        // Storing login value as TRUE

        editor.putBoolean(IS_SIGNUP, false);
        // commit changes
        editor.apply();
    }
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            Intent i = new Intent(_context, Login_Activity.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

        }
    }
    public void checkSignUp() {
        // user is not logged in redirect him to Login Activity
        if (!this.isSignedUp()) {
            Intent intent=new Intent(_context, Signup.class);
            // Closing all the Activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(intent);
        }}
    /**
     * Get stored session data
     * */
         public  HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // user location
        user.put(KEY_LOCATION, pref.getString(KEY_LOCATION, null));

        // user mobile no
        user.put(KEY_MOBILE_NO, pref.getString(KEY_MOBILE_NO, null));

        // user password
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Signup.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }


    // Get SignUp State
    private boolean isSignedUp(){
        return pref.getBoolean(IS_SIGNUP, false);
    }
    /**
     * Quick check for login
     * **/
    // Get Login State
    private boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

   }
