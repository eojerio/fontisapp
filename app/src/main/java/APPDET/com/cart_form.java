package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class cart_form extends AppCompatActivity {
    ImageButton btnHome, btnHistory;
    Intent intentHomeCART, intentHistoryCART;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_form);

        homeCART();
    }

    //going to index page
    public void homeCART(){
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        intentHomeCART = new Intent(this, index_form.class);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentHomeCART);
            }
        });
    }
}