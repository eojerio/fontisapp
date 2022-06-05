package APPDET.com;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account_editFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account_editFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public account_editFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account_edit.
     */
    // TODO: Rename and change types and number of parameters
    public static account_editFragment newInstance(String param1, String param2) {
        account_editFragment fragment = new account_editFragment();
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
    Button btnSaveAccount;
    EditText etEditAddress, etEditContactNo, etEditEmailAddress, etEditBirthdate, etEditMaritalStatus,etEditEmploymentStatus,etEditDescription;
    TextView tvFragmentName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_edit, container, false);

        etEditAddress = (EditText) v.findViewById(R.id.etEditAddress);
        etEditContactNo = (EditText) v.findViewById(R.id.etEditContactNo);
        etEditEmailAddress = (EditText) v.findViewById(R.id.etEditEmailAddress);
        etEditBirthdate = (EditText) v.findViewById(R.id.etEditBirthdate);
        etEditMaritalStatus = (EditText) v.findViewById(R.id.etEditMaritalStatus);
        etEditEmploymentStatus = (EditText) v.findViewById(R.id.etEditEmploymentStatus);
        etEditDescription = (EditText) v.findViewById(R.id.etEditDescription);
        tvFragmentName = (TextView) v.findViewById(R.id.tvFragmentName);

        final String id = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());

        //assigning text to database
        tvFragmentName.setText(SharedPreferenceManager.getInstance(getContext()).getFirstName() +" "+ SharedPreferenceManager.getInstance(getContext()).getLastName());
        etEditAddress.setText(SharedPreferenceManager.getInstance(getContext()).getAddress());
        etEditContactNo.setText(SharedPreferenceManager.getInstance(getContext()).getContactNo());
        etEditEmailAddress.setText(SharedPreferenceManager.getInstance(getContext()).getEmailAddress());
        etEditBirthdate.setText(SharedPreferenceManager.getInstance(getContext()).getBirthdate());
        etEditMaritalStatus.setText(SharedPreferenceManager.getInstance(getContext()).getMaritalStatus());
        etEditEmploymentStatus.setText(SharedPreferenceManager.getInstance(getContext()).getEmploymentStatus());
        etEditDescription.setText(SharedPreferenceManager.getInstance(getContext()).getDescription());





    // event click for updating user details
        btnSaveAccount = (Button) v.findViewById(R.id.btnSaveAccount);
        btnSaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String address = etEditAddress.getText().toString().trim();
               final String contact_no = etEditContactNo.getText().toString().trim();
               final String email_address = etEditEmailAddress.getText().toString().trim();
               final String birthdate = etEditBirthdate.getText().toString().trim();
               final String marital_status = etEditMaritalStatus.getText().toString().trim();
               final String employment_status = etEditEmploymentStatus.getText().toString().trim();
               final String description = etEditDescription.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_PROFILESAVE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")){

                                SharedPreferenceManager.getInstance(getContext()).userEdit(jsonObject.getString("contact_no"),jsonObject.getString("address"),jsonObject.getString("birthdate"),jsonObject.getString("email_address"),jsonObject.getString("employment_status"),jsonObject.getString("marital_status"),jsonObject.getString("user_description"));

                                Toast.makeText(getContext(),"PROFILE UPDATED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                                Fragment save_account = new accountFragment();
                                getParentFragmentManager().beginTransaction().replace(R.id.container_botnav,save_account).commit();


                            }else{

                                Toast.makeText(getContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id",id);
                        params.put("address",address);
                        params.put("contact_no",contact_no);
                        params.put("email_address",email_address);
                        params.put("birthdate",birthdate);
                        params.put("marital_status",marital_status);
                        params.put("employment_status",employment_status);
                        params.put("user_description",description);

                        return params;

                    }
                };

                RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

            }
        });

        return v;
    }

}