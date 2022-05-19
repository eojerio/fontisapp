package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registration_form extends AppCompatActivity {
    Button btn_loginform, btn_submit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        loginform();
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