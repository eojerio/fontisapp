package APPDET.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdminListAdapter extends ArrayAdapter<AdminOBJ> {
    private static final String TAG = "AdminListAdapter";
    String positionID;

    private Context mContext;
    int mResource;

    ProgressDialog load;

    ArrayList<String> admin_historyprodID = new ArrayList<String>();
    ArrayList<String> admin_cartuserID = new ArrayList<String>();


    public AdminListAdapter(Context context, int resource, ArrayList<AdminOBJ> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public class valueHolderCart {
        TextView tvPriceAdmin;
        TextView tvUserAdmin;
        TextView tvDateAdmin;
        TextView tvAmountAdmin;
        TextView tvAddressAdmin;
        Button btnAccept;
        Button btnDetails;
    }

    public String getIDPosition() {
        return positionID;
    }

    public void setIDPosition(String position) {
        this.positionID = position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        getIDAdminBreakdown();

        setIDPosition(String.valueOf(position));

        //GET INFORMATION
        String price = getItem(position).getPrice();
        String user = getItem(position).getUser();
        String date = getItem(position).getDate();
        String amount = getItem(position).getAmountAdmin();
        String address = getItem(position).getAddress();

        //Creating account object
        AdminOBJ item = new AdminOBJ(price, user, date, amount, address);

        //creating layout inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //creating object from valueHolder
        valueHolderCart fields = new valueHolderCart();

        //progress dialogue
        load = new ProgressDialog(getContext());


        //dialog process
        final Handler handler = new Handler();

        //assigning text views to xml design
        fields.tvPriceAdmin = (TextView) convertView.findViewById(R.id.tvPriceAdmin);
        fields.tvUserAdmin = (TextView) convertView.findViewById(R.id.tvUserAdmin);
        fields.tvDateAdmin = (TextView) convertView.findViewById(R.id.tvDateAdmin);
        fields.tvAmountAdmin = (TextView) convertView.findViewById(R.id.tvAmountAdmin);
        fields.tvAddressAdmin = (TextView) convertView.findViewById(R.id.tvAddressAdmin);
        fields.btnAccept = (Button) convertView.findViewById(R.id.btnAccept);
        fields.btnDetails = (Button) convertView.findViewById(R.id.btnDetails);

        //for accepting
        fields.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                final String formattedDate = df.format(c);

                //method for generating history list from database
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEADMINDELIVER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")) {
                                Toast.makeText(getContext(), "The order has been delivered.", Toast.LENGTH_SHORT).show();

                                Fragment fragment = new adminHistoryFragment();
                                ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container_botnavAdmin, fragment).commit();
                            } else {
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
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("admin_historyprodID", admin_historyprodID.get(position));
                        params.put("admin_userID", admin_cartuserID.get(position));
                        params.put("admin_prodDate", formattedDate);

                        return params;
                    }
                };
                RequestHandler.getInstance(mContext.getApplicationContext()).addToRequestQueue(stringRequest);

            }
        });

        fields.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), admin_breakdown.class);
                intent.putStringArrayListExtra("historyID", admin_historyprodID);
                intent.putStringArrayListExtra("userID", admin_cartuserID);
                intent.putExtra("positionID", String.valueOf(position));
                mContext.startActivity(intent);

            }

        });

        fields.tvPriceAdmin.setText(price);
        fields.tvUserAdmin.setText(user);
        fields.tvDateAdmin.setText(date);
        fields.tvAmountAdmin.setText(amount);
        fields.tvAddressAdmin.setText(address);

        return convertView;
    }

    public void getIDAdminBreakdown() {
        //get all id 1st string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("adminTrue");

                    //loop
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject adminOBJ = array.getJSONObject(i);

                        admin_historyprodID.add(adminOBJ.getString("admin_historyprodID"));
                        admin_cartuserID.add(adminOBJ.getString("admin_userID"));

                        Log.i("Test ALSO: ", adminOBJ.getString("admin_historyprodID"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
