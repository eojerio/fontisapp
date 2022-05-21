package APPDET.com;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class index_form extends AppCompatActivity {

    private BottomNavigationView bot_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_form);

        bot_nav = findViewById(R.id.bottomNav);

        bot_nav.setOnItemSelectedListener(bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_botnav, new homeFragment()).commit();

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
                    break;

                case R.id.account:
                    fragment = new accountFragment();
                    break;
            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_botnav, fragment).commit();
            }

            return true;
        }
    };


}