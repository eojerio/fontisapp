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