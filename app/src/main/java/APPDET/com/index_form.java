package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class index_form extends AppCompatActivity {
    ImageButton btnCart, btnHistory, btnAccount;
    Intent intentCart, intentHistory, intentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_form);

        history();
        cart();
        account();
    }

    //going to history page
    public void history(){
        btnHistory = (ImageButton) findViewById(R.id.btnHistory);
        intentHistory = new Intent (this, history_form.class);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentHistory);
            }
        });
    }

    //going to cart page
    public void cart(){
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        intentCart = new Intent (this, cart_form.class);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentCart);
            }
        });
    }

    //going to account page
    public void account(){
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        intentAccount = new Intent (this, account_form.class);

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAccount);
            }
        });
    }


}