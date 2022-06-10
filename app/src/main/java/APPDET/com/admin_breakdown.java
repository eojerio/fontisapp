package APPDET.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class admin_breakdown extends AppCompatActivity {

    ArrayList<AdminBreakdownOBJ> data;
    ListView lv;
    String position;

    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_breakdown);

        //previous adminbreakdown
        addListenerOnButton();

        //list view declaration
        lv = (ListView) findViewById(R.id.lvListBreakdown);

        //object to get arraylist value position of id
        AdminOBJArrayList prod = new AdminOBJArrayList();

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            position = extras.getString("positionID");
        }

        ArrayList<String> historyIDArr = getIntent().getExtras().getStringArrayList("historyID");
        ArrayList<String> userIDArr = getIntent().getExtras().getStringArrayList("userID");

        String historyID = historyIDArr.get(Integer.parseInt(position));
        String userID = userIDArr.get(Integer.parseInt(position));

        //new ArrayList true
        data = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEADMINBREAKOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("adminBreakdownTrue");

                    for(int i = 0; i < array.length(); i++){

                        JSONObject adminBreakdownOBJ = array.getJSONObject(i);

                        AdminBreakdownOBJ prod = new AdminBreakdownOBJ("₱ " + adminBreakdownOBJ.getString("cart_prodPrice"), "Quantity: " + adminBreakdownOBJ.getString("cart_prodQty"), adminBreakdownOBJ.getString("cart_prodName"), adminBreakdownOBJ.getString("cart_prodDesc"), "drawable://" + Integer.parseInt(adminBreakdownOBJ.getString("cart_prodImg")));

                        data.add(prod);
                        AdminBreakdownListAdapter arrayAdapter = new AdminBreakdownListAdapter(getApplicationContext(), R.layout.adapter_breakdownview_layout, data);
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
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("admin_historyprodID", historyID);
                params.put("admin_cartuserID", userID);
                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    public void addListenerOnButton() {

        ImageButton adminPrevious = (ImageButton) findViewById(R.id.imageButtonItemBreakdown);
        adminPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }


}