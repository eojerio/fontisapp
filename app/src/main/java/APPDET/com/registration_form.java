package APPDET.com;

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

import com.vishnusivadas.advanced_httpurlconnection.PutData;

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
                String usernameDATA, passwordDATA, first_nameDATA, last_nameDATA, contact_noDATA, addressDATA, birthdateDATA, email_addressDATA, employment_statusDATA, marital_statusDATA;

                usernameDATA = String.valueOf(username.getText());
                passwordDATA = String.valueOf(password.getText());
                first_nameDATA = String.valueOf(first_name.getText());
                last_nameDATA = String.valueOf(last_name.getText());
                contact_noDATA = String.valueOf(contact_no.getText());
                addressDATA = String.valueOf(address.getText());
                birthdateDATA = String.valueOf(birthdate.getText());
                email_addressDATA =String.valueOf( email_address.getText());
                employment_statusDATA = String.valueOf(employment_status.getText());
                marital_statusDATA = String.valueOf(marital_status.getText());


                //usernameDATA.isEmpty() && passwordDATA.isEmpty() && first_nameDATA.isEmpty() && last_nameDATA.isEmpty() && contact_noDATA.isEmpty() && addressDATA.isEmpty() && birthdateDATA.isEmpty() && email_addressDATA.isEmpty() && employment_statusDATA.isEmpty() && marital_statusDATA.isEmpty()

                //checks if edit text are blank
                if((!usernameDATA.trim().equals("")) && (!passwordDATA.trim().equals("")) && (!first_nameDATA.trim().equals("")) && (!last_nameDATA.trim().equals("")) && (!contact_noDATA.trim().equals("")) && (!addressDATA.trim().equals("")) && (!birthdateDATA.trim().equals("")) && (!email_addressDATA.trim().equals("")) && (!employment_statusDATA.trim().equals("")) && (!marital_statusDATA.trim().equals(""))){
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[10];
                            field[0] = "username";
                            field[1] = "password";
                            field[2] = "first_name";
                            field[3] = "last_name";
                            field[4] = "contact_no";
                            field[5] = "address";
                            field[6] = "birthdate";
                            field[7] = "email_address";
                            field[8] = "employment_status";
                            field[9] = "marital_status";
                            //Creating array for data
                            String[] data = new String[10];
                            data[0] = usernameDATA;
                            data[1] = passwordDATA;
                            data[2] = first_nameDATA;
                            data[3] = last_nameDATA;
                            data[4] = contact_noDATA;
                            data[5] = addressDATA;
                            data[6] = birthdateDATA;
                            data[7] = email_addressDATA;
                            data[8] = employment_statusDATA;
                            data[9] = marital_statusDATA;
                            PutData putData = new PutData("http://192.168.254.103/fontisDB/signUp.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent signup = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(signup);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
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