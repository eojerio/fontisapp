package APPDET.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class admin_form extends AppCompatActivity {

    ArrayList<AdminOBJ> data;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_form);

        //list view declaration
        lv = (ListView) findViewById(R.id.lvAdmin);

        //new ArrayList
        data = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("adminTrue");
                    //loop
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject adminOBJ = array.getJSONObject(i);

                        AdminOBJ prod = new AdminOBJ("₱" + adminOBJ.getString("prod_price"),adminOBJ.getString("first_name") + " " + adminOBJ.getString("last_name"), adminOBJ.getString("prod_date"), adminOBJ.getString("prod_amt") + " Items", adminOBJ.getString("address"));
                        data.add(prod);
                        // Inflate the layout for this fragment
                        AdminListAdapter arrayAdapter = new AdminListAdapter(getApplicationContext(), R.layout.adapter_adminview_layout, data);
                        lv.setAdapter(arrayAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}