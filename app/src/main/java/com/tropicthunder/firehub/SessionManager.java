package com.tropicthunder.firehub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;

/**
 * Created by Gareth Teoh on 04-Jun-16.
 */
public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Access token (make variable public to access from outside)
    public static final String KEY_ACCESS_TOKEN = "access_token";

//    public static final String NAME = "display_name";
//    public static final String LOCATION = "location";
//    public static final String GENDER = "gender";
//    public static final String TOTAL_QUESTION = "totalquestion";
//    public static final String URL = "url";
//    public static final String EMAIL = "email";
//    public static final String ID = "id";
//    public static final String CAPTION = "caption";
//    public static final String DOB = "dob";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String access_token) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing access code in pref
        editor.putString(KEY_ACCESS_TOKEN, access_token);
//        editor.putString(NAME, name);
//        editor.putString(LOCATION, location);
//        ​
//        editor.putString(URL, url);
//        editor.putString(ID, id);
        // commit changes
        editor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            //user is not logged in, redirect to login activity
            Intent i = new Intent(_context, LoginActivity.class);

            //Close all activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new flag to start new activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Start login activity
            _context.startActivity(i);
        }
    }

    /**
     * Clear session details
     */
    public void logoutUser(){
        // Clear all data from shared preference
        editor.clear();
        editor.commit();

        // Redirect to login activity after lofout
        Intent i = new Intent(_context, LoginActivity.class);

        //Close all activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new flag to start new activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Start login activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setKeyAccessToken(String accessToken) {
        editor.remove(KEY_ACCESS_TOKEN);

        // Storing access code in pref
        editor.putString(KEY_ACCESS_TOKEN, accessToken);

        // commit changes
        editor.commit();
    }

    public String getKeyAccessToken(){
        return pref.getString(KEY_ACCESS_TOKEN, "NO ACCESS TOKEN");
    }
}
