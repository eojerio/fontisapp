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

    //for user profile
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "username";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_FIRSTNAME = "first_name";
    private static final String KEY_USER_LASTNAME = "last_name";
    private static final String KEY_USER_CONTACTNO = "contact_no";
    private static final String KEY_USER_ADDRESS = "address";
    private static final String KEY_USER_BIRTHDATE = "birthdate";
    private static final String KEY_USER_EMAILADDRESS = "email_address";
    private static final String KEY_USER_EMPLOYMENTSTATUS = "employment_status";
    private static final String KEY_USER_MARITALSTATUS = "marital_status";
    private static final String KEY_USER_DESCRIPTION = "user_description";

    //for cart
    private static final String KEY_CART_TAG = "cart_prodTag";
    private static final String KEY_CART_PRODPRICE = "cart_prodPrice";
    private static final String KEY_CART_PRODNAME = "cart_prodName";
    private static final String KEY_CART_PRODDESC = "cart_prodDesc";
    private static final String KEY_CART_PRODQTY = "cart_prodQty";

    //for history
    private static final String KEY_HISTORY_ID = "prod_id";
    private static final String KEY_HISTORY_PRICE = "prod_price";
    private static final String KEY_HISTORY_DATE = "prod_price";
    private static final String KEY_HISTORY_AMT = "prod_price";
    private static final String KEY_HISTORY_IMG = "prod_img";
    private static final String KEY_HISTORY_ACCEPTED = "prod_adminAccepted";
    private static final String KEY_HISTORY_FIRSTNAME = "first_name";
    private static final String KEY_HISTORY_LASTNAME = "last_name";

    private SharedPreferenceManager(Context context) {
        ctx = context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceManager(context);
        }
        return instance;
    }

    public boolean getCartDetails(String cart_prodQty){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_CART_PRODQTY, cart_prodQty);
        editor.apply();

        return true;
    }



    public String getCartQty(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CART_PRODQTY, null);
    }

    public boolean getHistoryDetails(String prod_id){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_HISTORY_ID, prod_id);
        editor.apply();

        return true;
    }

    public String getHistoryID(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_HISTORY_ID, null);
    }

    public boolean userLogin(int id, String username, String password,String first_name, String last_name,String contact_no, String address, String birthdate, String email_address, String employment_status, String marital_status, String user_description){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_USERNAME, username);
        editor.putString(KEY_USER_PASSWORD, password);
        editor.putString(KEY_USER_FIRSTNAME, first_name);
        editor.putString(KEY_USER_LASTNAME, last_name);
        editor.putString(KEY_USER_CONTACTNO, contact_no);
        editor.putString(KEY_USER_ADDRESS, address);
        editor.putString(KEY_USER_BIRTHDATE, birthdate);
        editor.putString(KEY_USER_EMAILADDRESS, email_address);
        editor.putString(KEY_USER_EMPLOYMENTSTATUS, employment_status);
        editor.putString(KEY_USER_MARITALSTATUS, marital_status);
        editor.putString(KEY_USER_DESCRIPTION, user_description);

        editor.apply();

        return true;

    }

    public boolean userEditPassword(String password, String contact_no, String address, String birthdate, String email_address, String employment_status, String marital_status, String user_description){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_PASSWORD, password);
        editor.putString(KEY_USER_CONTACTNO, contact_no);
        editor.putString(KEY_USER_ADDRESS, address);
        editor.putString(KEY_USER_BIRTHDATE, birthdate);
        editor.putString(KEY_USER_EMAILADDRESS, email_address);
        editor.putString(KEY_USER_EMPLOYMENTSTATUS, employment_status);
        editor.putString(KEY_USER_MARITALSTATUS, marital_status);
        editor.putString(KEY_USER_DESCRIPTION, user_description);

        editor.apply();

        return true;

    }

    public boolean userEdit(String contact_no, String address, String birthdate, String email_address, String employment_status, String marital_status, String user_description){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_CONTACTNO, contact_no);
        editor.putString(KEY_USER_ADDRESS, address);
        editor.putString(KEY_USER_BIRTHDATE, birthdate);
        editor.putString(KEY_USER_EMAILADDRESS, email_address);
        editor.putString(KEY_USER_EMPLOYMENTSTATUS, employment_status);
        editor.putString(KEY_USER_MARITALSTATUS, marital_status);
        editor.putString(KEY_USER_DESCRIPTION, user_description);

        editor.apply();

        return true;

    }

    public boolean adminPanel(String price, String date, String amount, String image, String first_name, String last_name){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        editor.apply();

        return true;
    }

    public String getPriceAdmin(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_HISTORY_PRICE, null);
    }

    public String getDateAdmin(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_HISTORY_DATE, null);
    }

    public String getAmtAdmin(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_HISTORY_AMT, null);
    }

    public String getFirstNameAdmin(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_HISTORY_FIRSTNAME, null);
    }

    public String getLastNameAdmin(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_HISTORY_LASTNAME, null);
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

    public String getPassword(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PASSWORD, null);
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

    public String getAddress(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ADDRESS, null);
    }

    public String getContactNo(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_CONTACTNO, null);
    }

    public String getEmailAddress(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAILADDRESS, null);
    }

    public String getBirthdate(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_BIRTHDATE, null);
    }

    public String getMaritalStatus(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_MARITALSTATUS, null);
    }

    public String getEmploymentStatus(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMPLOYMENTSTATUS, null);
    }
    public String getDescription(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_DESCRIPTION, null);
    }
}