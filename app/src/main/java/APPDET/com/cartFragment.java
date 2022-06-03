package APPDET.com;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cartFragment newInstance(String param1, String param2) {
        cartFragment fragment = new cartFragment();
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

    private static final String TAG= "CartFrag";

    //fields
    TextView tvCart;
    ArrayList<CartOBJ> data;
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        Log.d(TAG, "onCreate: Started");

        //list view declaration
        lv = (ListView) v.findViewById(R.id.lvList);

        //new ArrayList
        data = new ArrayList<>();

        //method for generating cart list from database
        generateCartList();


        //don't touch!! returns values
        return v;
    }

    public void generateCartList(){

        final String cart_userID = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATECART, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("cartTrue");
                        //loop
                        for(int i=0; i < array.length();i++){

                            //array list
                            //get id
                            //arraylistsize

                            JSONObject cartOBJ = array.getJSONObject(i);

                            CartOBJ prod = new CartOBJ("₱ " + cartOBJ.getString("cart_prodPrice"), cartOBJ.getString("cart_prodQty"),cartOBJ.getString( "cart_prodName"), cartOBJ.getString("cart_prodDesc"), "drawable://" +  R.drawable.water_gallon);
                            //5

                            data.add(prod);

                            // Inflate the layout for this fragment
                            CartListAdapter arrayAdapter = new CartListAdapter(getActivity(), R.layout.adapter_cartview_layout, data);
                            lv.setAdapter(arrayAdapter);
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