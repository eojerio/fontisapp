package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class cart_form extends AppCompatActivity {
    ImageButton btnHome, btnHistory, btnAccount;
    Intent intentHomeCART, intentHistoryCART, intentAccountCART;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_form);

        homeCART();
        historyCART();
        accountCART();
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

    //going to cart page
    public void historyCART(){
        btnHistory = (ImageButton) findViewById(R.id.btnHistory);
        intentHistoryCART = new Intent (this, history_form.class);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentHistoryCART);
            }
        });
    }

    //going to account page
    public void accountCART(){
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        intentAccountCART = new Intent (this, account_form.class);

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAccountCART);
            }
        });
    }
}