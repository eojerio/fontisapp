package APPDET.com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminHistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static adminHistoryFragment newInstance(String param1, String param2) {
        adminHistoryFragment fragment = new adminHistoryFragment();
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

    ArrayList<AdminHistoryOBJ> data;
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_history, container, false);

        //list view declaration
        lv = (ListView) v.findViewById(R.id.lvListAdminHistory);

        //new ArrayList
        data = new ArrayList<>();

        generateFragmentHistory();

        return v;
    }

    public void generateFragmentHistory(){
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_POPULATEADMINHISTORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("adminHistoryTrue");

                    //loop
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject adminHistoryOBJ = array.getJSONObject(i);

                        String dateDB = String.valueOf(adminHistoryOBJ.getString("admin_prodDate"));
                        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = dateParser.parse(dateDB);

                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                        String dateHistory = dateFormatter.format(date);

                        AdminHistoryOBJ prod = new AdminHistoryOBJ("₱" + formatter.format(Double.parseDouble(adminHistoryOBJ.getString("prod_price"))),adminHistoryOBJ.getString("first_name") + " " + adminHistoryOBJ.getString("last_name"), "Delivered on: " + dateHistory, adminHistoryOBJ.getString("prod_amt") + " Items", adminHistoryOBJ.getString("address"));
                        data.add(prod);

                        if (getActivity()!=null) {
                            //inflate the layout
                            AdminHistoryListAdapter arrayAdapter = new AdminHistoryListAdapter(getContext(), R.layout.adapter_adminhistoryview_layout, data);
                            lv.setAdapter(arrayAdapter);
                        }
                    }

                } catch (JSONException | ParseException e) {
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