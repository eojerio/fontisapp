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

public class MainActivity extends AppCompatActivity {
    EditText usernameLogin, passwordLogin;
    Button btn_login, btn_register;
    Intent intentLogin, intentRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameLogin = (EditText) findViewById(R.id.etUsername);
        passwordLogin = (EditText) findViewById(R.id.etPassword);



        //Database login
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
                String username, password;
                username = String.valueOf(usernameLogin.getText());
                password = String.valueOf(passwordLogin.getText());

                //usernameDATA.isEmpty() && passwordDATA.isEmpty() && first_nameDATA.isEmpty() && last_nameDATA.isEmpty() && contact_noDATA.isEmpty() && addressDATA.isEmpty() && birthdateDATA.isEmpty() && email_addressDATA.isEmpty() && employment_statusDATA.isEmpty() && marital_statusDATA.isEmpty()

                //checks if edit text are blank
                if(!username.trim().equals("") && !password.trim().equals("")){
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.254.103/fontisDB/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent signup = new Intent(getApplicationContext(), index_form.class);
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
    public void register(){
        btn_register = (Button) findViewById(R.id.btnRegister);
        intentRegister = new Intent (this, registration_form.class);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentRegister);
            }
        });
    }

}