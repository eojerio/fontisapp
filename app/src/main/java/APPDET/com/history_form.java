package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class history_form extends AppCompatActivity {
    ImageButton btnHome, btnCart, btnAccount;
    Intent intentHomeHISTORY, intentCartHISTORY, intentAccountCART;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_form);

        homeHISTORY();
        cartHISTORY();
        accountHISTORY();
    }

    //going to index page
    public void homeHISTORY(){
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        intentHomeHISTORY = new Intent(this, index_form.class);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentHomeHISTORY);
            }
        });
    }

    //going to index page
    public void cartHISTORY(){
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        intentCartHISTORY = new Intent(this, cart_form.class);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentCartHISTORY);
            }
        });
    }

    //going to account page
    public void accountHISTORY(){
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