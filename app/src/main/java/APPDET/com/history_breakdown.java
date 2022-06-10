package APPDET.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class history_breakdown extends AppCompatActivity {

    ArrayList<HistoryBreakdownOBJ> data;
    ListView lv;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_breakdown);

        //previous
        addListenerOnButton();

        //list view declaration
        lv = (ListView) findViewById(R.id.lvListBreakdown);

//        HistoryBreakdownOBJ prod1 = new HistoryBreakdownOBJ("20","2","Water","A mineral water","drawable://" + R.drawable.alvarez);
//        HistoryBreakdownOBJ prod2 = new HistoryBreakdownOBJ("20","2","Water","A mineral water","drawable://" + R.drawable.alvarez);
//        HistoryBreakdownOBJ prod3 = new HistoryBreakdownOBJ("20","2","Water","A mineral water","drawable://" + R.drawable.alvarez);
//        HistoryBreakdownOBJ prod4 = new HistoryBreakdownOBJ("20","2","Water","A mineral water","drawable://" + R.drawable.alvarez);

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
//
//        data.add(prod1);
//        data.add(prod2);
//        data.add(prod3);
//        data.add(prod4);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEHISTORYBREAKOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("historyBreakdownTrue");

                    for(int i = 0; i < array.length(); i++){

                        JSONObject adminBreakdownOBJ = array.getJSONObject(i);

                        HistoryBreakdownOBJ prod = new HistoryBreakdownOBJ("₱ " + adminBreakdownOBJ.getString("cart_prodPrice"), "Quantity: " + adminBreakdownOBJ.getString("cart_prodQty"), adminBreakdownOBJ.getString("cart_prodName"), adminBreakdownOBJ.getString("cart_prodDesc"), "drawable://" + Integer.parseInt(adminBreakdownOBJ.getString("cart_prodImg")));

                        data.add(prod);
                        HistoryBreakdownListAdapter arrayAdapter = new HistoryBreakdownListAdapter(getApplicationContext(), R.layout.adapter_historybreakdownview_layout, data);
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
                params.put("historyprodID", historyID);
                params.put("userID", userID);
                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    public void addListenerOnButton() {

        ImageButton imagebtn1 = (ImageButton) findViewById(R.id.imageButtonItemBreakdown);
        imagebtn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();

            }
        });
    }


}