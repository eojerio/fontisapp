package APPDET.com;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link historyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class historyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public historyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment historyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static historyFragment newInstance(String param1, String param2) {
        historyFragment fragment = new historyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    /*================================================ OKAY BEN DITO TAYO MAG C'CODE HA =====================================================================*/

    private static final String TAG= "HistoryFrag";

    ProgressDialog load;
    ListView lv;
    ArrayList<HistoryOBJ> data;

    DecimalFormat formatter = new DecimalFormat("#,###.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        Log.d(TAG, "onCreate: Started");

        lv = (ListView) v.findViewById(R.id.lvList);

        data = new ArrayList<>();

        //progress dialogue
        load = new ProgressDialog(getContext());

        load.setMessage("LOADING HISTORY...");
        load.setCancelable(false);
        load.setCanceledOnTouchOutside(false);
        load.show();

        //dialog process
        final Handler handler = new Handler();
        //Updating the mood box
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //method for generating history list from database
                generateHistoryList();
                load.dismiss();
            }
        }, 300);



        // Inflate the layout for this fragment
        return v;

    }

    public void generateHistoryList(){
        final String cart_userID = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEHISTORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("historyTrue");
                    //loop
                    for(int i=0; i < array.length();i++){

                        JSONObject historyOBJ = array.getJSONObject(i);

                        HistoryOBJ prod = new HistoryOBJ("₱" + formatter.format(Double.parseDouble(historyOBJ.getString("prod_price"))), historyOBJ.getString("prod_date"), historyOBJ.getString("prod_amt"), "drawable://" +  R.drawable.cart_history);

                        data.add(prod);
                        if (getActivity()!=null){
                            HistoryListAdapter arrayAdapter = new HistoryListAdapter(getActivity(), R.layout.adapter_historyview_layout, data);
                            lv.setAdapter(arrayAdapter);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "JSON Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cart_userID", cart_userID);
                return params;
            }
        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}