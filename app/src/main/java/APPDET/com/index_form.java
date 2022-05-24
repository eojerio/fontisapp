package APPDET.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class index_form extends AppCompatActivity {

    private BottomNavigationView bot_nav;
    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_form);


        bot_nav = findViewById(R.id.bottomNav);

        bot_nav.setOnItemSelectedListener(bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_botnav, new homeFragment()).commit();
    }

    //passing id from index to cart
    public void getDataFromIndex(int id){
        message = Integer.toString(id);
    }

    private BottomNavigationView.OnItemSelectedListener bottom_nav = new BottomNavigationView.OnItemSelectedListener() {
        @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch(item.getItemId()){

                case R.id.home:
                fragment = new homeFragment();
                break;

                case R.id.history:
                    fragment = new historyFragment();
                    break;

                case R.id.cart:
                    fragment = new cartFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("data", message);

                    fragment.setArguments(bundle);

                    break;

                case R.id.account:
                    fragment = new accountFragment();
                    break;
            }

                getSupportFragmentManager().beginTransaction().replace(R.id.container_botnav, fragment).commit();

            return true;
        }
    };


}