package APPDET.com;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPreferenceManager {
    private static SharedPreferenceManager instance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME = "mysharedref12";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "username";
    private static final String KEY_USER_FIRSTNAME = "first_name";
    private static final String KEY_USER_LASTNAME = "last_name";
    private static final String KEY_USER_CONTACTNO = "contact_no";
    private static final String KEY_USER_ADDRESS = "address";
    private static final String KEY_USER_BIRTHDATE = "birthdate";
    private static final String KEY_USER_EMAILADDRESS = "email_address";
    private static final String KEY_USER_EMPLOYMENTSTATUS = "employment_status";
    private static final String KEY_USER_MARITALSTATUS = "marital_status";

    private SharedPreferenceManager(Context context) {
        ctx = context;

    }

    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceManager(context);
        }
        return instance;
    }

    public boolean userLogin(int id, String username, String first_name, String last_name,String contact_no, String address, String birthdate, String email_address, String employment_status, String marital_status){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_USERNAME, username);
        editor.putString(KEY_USER_FIRSTNAME, first_name);
        editor.putString(KEY_USER_LASTNAME, last_name);
        editor.putString(KEY_USER_CONTACTNO, contact_no);
        editor.putString(KEY_USER_ADDRESS, address);
        editor.putString(KEY_USER_BIRTHDATE, birthdate);
        editor.putString(KEY_USER_EMAILADDRESS, email_address);
        editor.putString(KEY_USER_EMPLOYMENTSTATUS, employment_status);
        editor.putString(KEY_USER_MARITALSTATUS, marital_status);

        editor.apply();

        return true;

    }

    public boolean isUserLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(KEY_USER_USERNAME, null) != null ){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
        return true;
    }

    public int getUserID(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID, 0);
    }
    //for profile fragment get data


    public String getFirstName(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_FIRSTNAME, null);
    }

    public String getLastName(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_LASTNAME, null);
    }
}