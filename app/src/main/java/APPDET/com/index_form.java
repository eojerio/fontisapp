package APPDET.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class index_form extends AppCompatActivity {
    ImageButton btnHome, btnCart;
    Intent intentHome, intentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_form);

        cart();
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


}