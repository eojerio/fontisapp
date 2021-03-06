package APPDET.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText usernameLogin, passwordLogin;
    Button btn_login;
    TextView tvRegister;
    Intent intentLogin, intentRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //checks if an account is logged in
        if(SharedPreferenceManager.getInstance(this).isUserLoggedIn()){
            finish();
            startActivity(new Intent(this, index_form.class));
            return;
        }

        usernameLogin = (EditText) findViewById(R.id.etUsername);
        passwordLogin = (EditText) findViewById(R.id.etPassword);



        //Database login code
        login();

        //travel to register activity
        register();
    }

    //for accessing index page
    public void login(){
        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameDATAGET = usernameLogin.getText().toString().trim();
                final String passwordDATAGET = passwordLogin.getText().toString().trim();

                if(usernameDATAGET.equals("G0J0S4T0RU") && passwordDATAGET.equals("P4SSWORD")){
                    //intent go to main page
                    Intent intent = new Intent(getApplicationContext(),admin_form.class);
                    startActivity(intent);
                    finish();
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                //if success
                                if(!jsonObject.getBoolean("error")){

                                    SharedPreferenceManager.getInstance(getApplicationContext()).userLogin(jsonObject.getInt("id"), jsonObject.getString("username"), jsonObject.getString("password"), jsonObject.getString("first_name"),jsonObject.getString("last_name"),jsonObject.getString("contact_no"),jsonObject.getString("address"),jsonObject.getString("birthdate"),jsonObject.getString("email_address"),jsonObject.getString("employment_status"),jsonObject.getString("marital_status"),jsonObject.getString("user_description"));

                                    Toast.makeText(getApplicationContext(), "Login Successful" , Toast.LENGTH_SHORT).show();

                                    //intent go to main page
                                    Intent intent = new Intent(getApplicationContext(), index_form.class);
                                    startActivity(intent);
                                    finish();

                                }else{

                                    //shows error
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("username", usernameDATAGET);
                            params.put("password", passwordDATAGET);
                            return params;
                        }
                    };

                    RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                }

            }
        });
    }

    //for accessing registration form
    public void register(){
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        intentRegister = new Intent (this, registration_form.class);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentRegister);
            }
        });
    }

}