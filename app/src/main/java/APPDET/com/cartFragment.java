package APPDET.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
    ProgressDialog load;
    TextView tvCart;
    ArrayList<CartOBJ> data;

    public final double[] total = {0};
    public final double[] amount = {0};

    public ArrayList<String> price;
    public ArrayList<String> qty;
    ArrayList<String> img;

    public String getTotal;

    ListView lv;
    Button checkout;

    public TextView tvAmountCart;

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
        price = new ArrayList<String>();
        qty = new ArrayList<String>();
        img = new ArrayList<String>();

        //progress dialogue
        load = new ProgressDialog(getContext());

        tvAmountCart = (TextView) v.findViewById(R.id.tvAmountCartTotal);
//        load.setMessage("UPDATING CART...");
//        load.setCancelable(false);
//        load.setCanceledOnTouchOutside(false);
//        load.show();
//
//
//        //dialog process
//        final Handler handler = new Handler();
//
//        //Updating the mood box
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //method for generating cart list from database
//
//                load.dismiss();
//            }
//        }, 300);

        generateCartList();

        //for checking out
        checkout = (Button) v.findViewById(R.id.btnCheckout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String imageTRUE = "2131165281";

                final Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
                final String formattedDate = df.format(c);

                final String cart_userID = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());
                final String price = String.valueOf(total[0]);
                final String amountQty = String.valueOf(amount[0]);

                //checking values
                Log.i("DATE", formattedDate);
                Log.i("TOTAL", String.valueOf(total[0]));
                Log.i("AMOUNT", String.valueOf(amount[0]));

                if(total[0] <= 0 && amount[0] <= 0){
                    Toast.makeText(getContext(), "Please add an item first" , Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CHECKOUT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                if(!jsonObject.getBoolean("error")) {
                                    Toast.makeText(getContext(), "Items have been Checked out" , Toast.LENGTH_SHORT).show();

                                    //intent go to main page
                                    Intent intent = new Intent(getContext(), index_form.class);
                                    startActivity(intent);
                                }else{
                                    //shows error
                                    Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("cart_userID", cart_userID);
                            params.put("prod_price", price);
                            params.put("prod_date", formattedDate);
                            params.put("prod_amt", amountQty);
                            params.put("prod_img", imageTRUE);
                            return params;
                        }
                    };

                    RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
                }
            }
        });


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

                            JSONObject cartOBJ = array.getJSONObject(i);

                            CartOBJ prod = new CartOBJ(cartOBJ.getString("cart_prodTag"),"₱ " + cartOBJ.getString("cart_prodPrice"), cartOBJ.getString("cart_prodQty"),cartOBJ.getString( "cart_prodName"), cartOBJ.getString("cart_prodDesc"), "drawable://" +  Integer.parseInt(cartOBJ.getString("cart_prodImg")));

                            price.add(cartOBJ.getString("cart_prodPrice"));
                            qty.add(cartOBJ.getString("cart_prodQty"));
                            img.add(cartOBJ.getString("cart_prodImg"));


                            data.add(prod);

                            if (getActivity()!=null){
                                // Inflate the layout for this fragment
                                CartListAdapter arrayAdapter = new CartListAdapter(getActivity(), R.layout.adapter_cartview_layout, data);
                                lv.setAdapter(arrayAdapter);
                            }
                        }
                    for(int j = 0; j < price.size(); j++){
                        total[0] += (Double.parseDouble(price.get(j)) * Integer.parseInt(qty.get(j)));
                        amount[0] += Integer.parseInt(qty.get(j));
                        Log.i("AMOUNT ONCE", price.get(j) + " " + qty.get(j));
                    }

                    if(total[0] <= 0){
                        tvAmountCart.setText("₱0");
                    }else{
                        tvAmountCart.setText(String.valueOf("₱"+total[0]));
                        Log.i("AMOUNT TEST", String.valueOf(amount[0]));
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