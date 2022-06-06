package APPDET.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class registration_form extends AppCompatActivity {
    EditText username, password, first_name, last_name, contact_no, address, birthdate, email_address, employment_status, marital_status;
    TextView tvRegisterSignUp;
    Button btn_submit;
    Intent intent;
    Spinner employmentstatusSpinner, maritalstatusSpinner;
    ArrayAdapter<CharSequence> employmentstatusAdapter, maritalstatusAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        //checks if user is logged in
        if(SharedPreferenceManager.getInstance(this).isUserLoggedIn()){
            finish();
            startActivity(new Intent(this, index_form.class));
            return;
        }
        //Spinner dropdown menu
        employmentstatusSpinner = (Spinner) findViewById(R.id.spinner_dropdown_employment_status);
        maritalstatusSpinner = (Spinner) findViewById(R.id.spinner_dropdown_marital_status);

        //Poppulate ArrayAdapter
        employmentstatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_employment_status, R.layout.spinner_layout);
        maritalstatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_marital_status, R.layout.spinner_layout);

        //Specify Layout use when Choices appear
        employmentstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set The Adapter to spinner to populate the status spinner
        employmentstatusSpinner.setAdapter(employmentstatusAdapter);
        maritalstatusSpinner.setAdapter(maritalstatusAdapter);



        //TextView get value
        username = findViewById(R.id.etUsernameReg);
        password = findViewById(R.id.etPasswordReg);
        first_name =  findViewById(R.id.etFirstNameReg);
        last_name =  findViewById(R.id.etLastNameReg);
        contact_no =  findViewById(R.id.etContactReg);
        address = findViewById(R.id.etAddressReg);
        birthdate =  findViewById(R.id.etBirthDateReg);
        email_address =  findViewById(R.id.etEmailReg);

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
                final String employment_statusDATA = employmentstatusSpinner.getSelectedItem().toString().trim();
                final String marital_statusDATA = maritalstatusSpinner.getSelectedItem().toString().trim();

                //getting value part 9999

                Toast.makeText(getApplicationContext(), marital_statusDATA, Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "REGISTERING USER...", Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Checking...", Toast.LENGTH_SHORT).show();
                        Log.i("employment status: ",employment_statusDATA);

                        //if success
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //if success
                            if(!jsonObject.getBoolean("error")){
                                Toast.makeText(getApplicationContext(), "Register Successful" , Toast.LENGTH_SHORT).show();

                                //intent go to main page
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

                RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }
        });



    }

    //for accessing registration form
    public void loginform(){
        tvRegisterSignUp = (TextView) findViewById(R.id.tvRegisterSignUp);
        intent = new Intent(this, MainActivity.class);

        tvRegisterSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }





}