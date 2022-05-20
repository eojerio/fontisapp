package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class account_form extends AppCompatActivity {
    ImageButton btnHome, btnCart, btnHistory;
    Intent intentHomeACCOUNT, intentHistoryACCOUNT, intentCartACCOUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_form);

        homeACCOUNT();
        historyACCOUNT();
        cartACCOUNT();
    }

    //going to index page
    public void homeACCOUNT(){
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        intentHomeACCOUNT = new Intent(this, index_form.class);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentHomeACCOUNT);
            }
        });
    }

    //going to history page
    public void historyACCOUNT(){
        btnHistory = (ImageButton) findViewById(R.id.btnHistory);
        intentHistoryACCOUNT = new Intent (this, history_form.class);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentHistoryACCOUNT);
            }
        });
    }

    //going to cart page
    public void cartACCOUNT(){
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        intentCartACCOUNT = new Intent (this, cart_form.class);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentCartACCOUNT);
            }
        });
    }
}