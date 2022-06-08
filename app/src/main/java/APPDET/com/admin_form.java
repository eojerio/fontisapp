package APPDET.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_form extends AppCompatActivity {

    private BottomNavigationView bot_nav_admin;
    TextView adminLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_form);

        bot_nav_admin = findViewById(R.id.bottomNavAdmin);

        bot_nav_admin.setOnItemSelectedListener(bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_botnavAdmin, new adminDeliveriesFragment()).commit();

        logoutAdmin();
    }

    public void logoutAdmin(){
        adminLogout = (TextView) findViewById(R.id.tvAdminLogout);
        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_builder = new AlertDialog.Builder(admin_form.this);

                alert_builder.setMessage("Are you sure you want to exit admin dashboard?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alert_builder.create();
                alertDialog.setTitle("WARNING");
                alertDialog.show();
            }
        });
    }

    private BottomNavigationView.OnItemSelectedListener bottom_nav = new BottomNavigationView.OnItemSelectedListener() {
        @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch(item.getItemId()){

                case R.id.deliveriesAdmin:
                    fragment = new adminDeliveriesFragment();
                    break;

                case R.id.historyAdmin:
                    fragment = new adminHistoryFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container_botnavAdmin, fragment).commit();

            return true;
        }
    };
}