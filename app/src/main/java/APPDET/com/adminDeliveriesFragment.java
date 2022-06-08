package APPDET.com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminDeliveriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminDeliveriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminDeliveriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static adminDeliveriesFragment newInstance(String param1, String param2) {
        adminDeliveriesFragment fragment = new adminDeliveriesFragment();
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

    ArrayList<AdminOBJ> data;
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_home, container, false);

        //list view declaration
        lv = (ListView) v.findViewById(R.id.lvListAdmin);

        //new ArrayList
        data = new ArrayList<>();

        generateFragmentDeliveries();

        return v;
    }

    public void generateFragmentDeliveries(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("adminTrue");

                    //loop
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject adminOBJ = array.getJSONObject(i);

                        AdminOBJ prod = new AdminOBJ("₱" + adminOBJ.getString("prod_price"),adminOBJ.getString("first_name") + " " + adminOBJ.getString("last_name"), "Date: " + adminOBJ.getString("prod_date"), adminOBJ.getString("prod_amt") + " Items", adminOBJ.getString("address"));
                        data.add(prod);

                        if (getActivity()!=null) {
                            // Inflate the layout for this fragment
                            AdminListAdapter arrayAdapter = new AdminListAdapter(getContext(), R.layout.adapter_adminview_layout, data);
                            lv.setAdapter(arrayAdapter);
                        }
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