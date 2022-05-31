package APPDET.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registration_form extends AppCompatActivity {
    EditText username, password, first_name, last_name, contact_no, address, birthdate, email_address, employment_status, marital_status;
    Button btn_loginform, btn_submit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        //TextView get value
        username = findViewById(R.id.etUsernameReg);
        password = findViewById(R.id.etPasswordReg);
        first_name =  findViewById(R.id.etFirstNameReg);
        last_name =  findViewById(R.id.etLastNameReg);
        contact_no =  findViewById(R.id.etContactReg);
        address = findViewById(R.id.etAddressReg);
        birthdate =  findViewById(R.id.etBirthDateReg);
        email_address =  findViewById(R.id.etEmailReg);
        employment_status = findViewById(R.id.etEmploymentReg);
        marital_status = findViewById(R.id.etMaritalStatusReg);

        //for database registration
        submitReg();

        //for login activity
        loginform();

    }

    //database submission
    public void submitReg(){
        btn_submit = (Button) findViewById(R.id.btnSubmit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameDATA = username.getText().toString().trim();
                final String passwordDATA = password.getText().toString().trim();
                final String first_nameDATA = first_name.getText().toString().trim();
                final String last_nameDATA = last_name.getText().toString().trim();
                final String contact_noDATA = contact_no.getText().toString().trim();
                final String addressDATA = address.getText().toString().trim();
                final String birthdateDATA = birthdate.getText().toString().trim();
                final String email_addressDATA = email_address.getText().toString().trim();
                final String employment_statusDATA = employment_status.getText().toString().trim();
                final String marital_statusDATA = marital_status.getText().toString().trim();

                Toast.makeText(getApplicationContext(), "REGISTERING USER...", Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Inserting to database...", Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                        params.put("username", usernameDATA);
                        params.put("password", passwordDATA);
                        params.put("first_name", first_nameDATA);
                        params.put("last_name", last_nameDATA);
                        params.put("contact_no", contact_noDATA);
                        params.put("address", addressDATA);
                        params.put("birthdate", birthdateDATA);
                        params.put("email_address", email_addressDATA);
                        params.put("employment_status", employment_statusDATA);
                        params.put("marital_status", marital_statusDATA);

                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }

    //for accessing registration form
    public void loginform(){
        btn_loginform = (Button) findViewById(R.id.btnLoginReg);
        intent = new Intent(this, MainActivity.class);

        btn_loginform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }


}