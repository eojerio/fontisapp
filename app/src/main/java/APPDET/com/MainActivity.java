package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_login, btn_register;
    Intent intentLogin, intentRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login();
        register();
    }

    //for accessing index page
    public void login(){
        btn_login = (Button) findViewById(R.id.btnLogin);
        intentLogin = new Intent (this, index_form.class);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentLogin);
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