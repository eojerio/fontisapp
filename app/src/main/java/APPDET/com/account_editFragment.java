package APPDET.com;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
    EditText etEditAddress, etEditContactNo, etEditEmailAddress, etEditBirthdate, etEditMaritalStatus, etEditEmploymentStatus, etEditDescription;
    EditText etEditPassword, etEditNewPassword, etEditConfirmPassword;
    TextView tvFragmentName;
    Spinner employmentstatusSpinner, maritalstatusSpinner;
    ArrayAdapter<CharSequence> employmentstatusAdapter, maritalstatusAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_edit, container, false);

        etEditAddress = (EditText) v.findViewById(R.id.etEditAddress);
        etEditContactNo = (EditText) v.findViewById(R.id.etEditContactNo);
        etEditEmailAddress = (EditText) v.findViewById(R.id.etEditEmailAddress);
        etEditMaritalStatus = (EditText) v.findViewById(R.id.etEditMaritalStatus);
        etEditEmploymentStatus = (EditText) v.findViewById(R.id.etEditEmploymentStatus);
        etEditBirthdate = (EditText) v.findViewById(R.id.etEditBirthdate);
        etEditDescription = (EditText) v.findViewById(R.id.etEditDescription);
        etEditPassword = (EditText) v.findViewById(R.id.etEditPassword);
        etEditNewPassword = (EditText) v.findViewById(R.id.etEditNewPassword);
        etEditConfirmPassword = (EditText) v.findViewById(R.id.etEditConfirmPassword);

        tvFragmentName = (TextView) v.findViewById(R.id.tvFragmentName);

        //Spinner dropdown menu
        employmentstatusSpinner = (Spinner) v.findViewById(R.id.spinner_dropdown_employment_status_edit);
        maritalstatusSpinner = (Spinner) v.findViewById(R.id.spinner_dropdown_marital_status_edit);


        //Poppulate ArrayAdapter
        employmentstatusAdapter = ArrayAdapter.createFromResource(getContext(), R.array.array_employment_status2, R.layout.spinner_layout);
        maritalstatusAdapter = ArrayAdapter.createFromResource(getContext(), R.array.array_marital_status2, R.layout.spinner_layout);

        int sp_position, sp_position2;
        String myString = "Status";
        final String[] selected = new String[1];
        final String[] spinner_item = new String[1];

        sp_position = employmentstatusAdapter.getPosition(myString);
        sp_position2 = maritalstatusAdapter.getPosition(myString);

        employmentstatusSpinner.setAdapter(employmentstatusAdapter);
        maritalstatusSpinner.setAdapter(maritalstatusAdapter);

        //Specify Layout use when Choices appear
        employmentstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalstatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //For employment dropdown menu
        employmentstatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selected[0] = employmentstatusSpinner.getSelectedItem().toString();
                if (!selected[0].equals("Choose Status")) {
                    spinner_item[0] = selected[0];
                    System.out.println(selected[0]);

                    setid();
                }
            }

            private void setid() {
                employmentstatusSpinner.setSelection(sp_position);
                etEditEmploymentStatus.setText(spinner_item[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //For marital dropdown menu
        maritalstatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selected[0] = maritalstatusSpinner.getSelectedItem().toString();
                if (!selected[0].equals("Choose Status")) {
                    spinner_item[0] = selected[0];
                    System.out.println(selected[0]);

                    setid();
                }
            }

            private void setid() {
                maritalstatusSpinner.setSelection(sp_position);
                etEditMaritalStatus.setText(spinner_item[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //assigning text to database
        tvFragmentName.setText(SharedPreferenceManager.getInstance(getContext()).getFirstName() + " " + SharedPreferenceManager.getInstance(getContext()).getLastName());
        etEditAddress.setText(SharedPreferenceManager.getInstance(getContext()).getAddress());
        etEditContactNo.setText(SharedPreferenceManager.getInstance(getContext()).getContactNo());
        etEditEmailAddress.setText(SharedPreferenceManager.getInstance(getContext()).getEmailAddress());

        //getting date and parsing it
        String dateDB = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getBirthdate());
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateParser.parse(dateDB);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateHistory = dateFormatter.format(date);

        etEditBirthdate.setText(dateHistory);
        etEditMaritalStatus.setText(SharedPreferenceManager.getInstance(getContext()).getMaritalStatus());
        etEditEmploymentStatus.setText(SharedPreferenceManager.getInstance(getContext()).getEmploymentStatus());
        etEditDescription.setText(SharedPreferenceManager.getInstance(getContext()).getDescription());


        // event click for updating user details
        btnSaveAccount = (Button) v.findViewById(R.id.btnSaveAccount);
        btnSaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null) {
                    //checks if password et is empty
                    if (!TextUtils.isEmpty(etEditPassword.getText().toString())) {
                        final String id = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());
                        final String password = etEditPassword.getText().toString().trim();
                        final String new_password = etEditNewPassword.getText().toString().trim();
                        final String address = etEditAddress.getText().toString().trim();
                        final String contact_no = etEditContactNo.getText().toString().trim();
                        final String email_address = etEditEmailAddress.getText().toString().trim();
                        final String marital_status = etEditMaritalStatus.getText().toString().trim();
                        final String employment_status = etEditEmploymentStatus.getText().toString().trim();
                        final String description = etEditDescription.getText().toString().trim();

                        //birthdate string to date
                        String dateTime = etEditBirthdate.getText().toString().trim();

                        SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");

                        Date c = null;
                        try {
                            c = dateParser.parse(dateTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                        String formattedDate = df.format(c);

                        final String birthdate = formattedDate;

                        //checks if password is same as password from database and
                        if (!TextUtils.isEmpty(etEditPassword.getText().toString()) && !TextUtils.isEmpty(etEditNewPassword.getText().toString()) && !TextUtils.isEmpty(etEditConfirmPassword.getText().toString())) {
                            if (etEditNewPassword.getText().toString().trim().equals(etEditConfirmPassword.getText().toString().trim())) {
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_PROFILESAVEPASSWORD, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {

                                            JSONObject jsonObject = new JSONObject(response);

                                            if (!jsonObject.getBoolean("error")) {

                                                SharedPreferenceManager.getInstance(getContext()).userEditPassword(jsonObject.getString("password"), jsonObject.getString("contact_no"), jsonObject.getString("address"), jsonObject.getString("birthdate"), jsonObject.getString("email_address"), jsonObject.getString("employment_status"), jsonObject.getString("marital_status"), jsonObject.getString("user_description"));

                                                Toast.makeText(getContext(), "PROFILE UPDATED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                                                Fragment save_account = new accountFragment();
                                                getParentFragmentManager().beginTransaction().replace(R.id.container_botnav, save_account).commit();


                                            } else {

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
                                        params.put("id", id);
                                        params.put("password", password);
                                        params.put("new_password", new_password);
                                        params.put("address", address);
                                        params.put("contact_no", contact_no);
                                        params.put("email_address", email_address);
                                        params.put("birthdate", birthdate);
                                        params.put("marital_status", marital_status);
                                        params.put("employment_status", employment_status);
                                        params.put("user_description", description);

                                        return params;

                                    }
                                };

                                RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
                            } else {
                                Toast.makeText(getContext(), "NEW PASSWORD AND CONFIRM NEW PASSWORD IS NOT THE SAME", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Password fields are required", Toast.LENGTH_SHORT).show();
                        }
                        //if et password is empty true
                    } else {

                        final String id = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());
                        final String address = etEditAddress.getText().toString().trim();
                        final String contact_no = etEditContactNo.getText().toString().trim();
                        final String email_address = etEditEmailAddress.getText().toString().trim();
                        final String marital_status = etEditMaritalStatus.getText().toString().trim();
                        final String employment_status = etEditEmploymentStatus.getText().toString().trim();
                        final String description = etEditDescription.getText().toString().trim();

                        //birthdate string to date
                        String dateTime = etEditBirthdate.getText().toString().trim();

                        SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");

                        Log.i("DATE: ", dateTime);

                        Date c = null;
                        try {
                            c = dateParser.parse(dateTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                        String formattedDate = df.format(c);

                        final String birthdate = formattedDate;

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_PROFILESAVE, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {

                                    JSONObject jsonObject = new JSONObject(response);

                                    if (!jsonObject.getBoolean("error")) {

                                        SharedPreferenceManager.getInstance(getContext()).userEdit(jsonObject.getString("contact_no"), jsonObject.getString("address"), jsonObject.getString("birthdate"), jsonObject.getString("email_address"), jsonObject.getString("employment_status"), jsonObject.getString("marital_status"), jsonObject.getString("user_description"));

                                        Toast.makeText(getContext(), "PROFILE UPDATED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();
                                        Fragment save_account = new accountFragment();
                                        getParentFragmentManager().beginTransaction().replace(R.id.container_botnav, save_account).commit();


                                    } else {

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
                                params.put("id", id);
                                params.put("address", address);
                                params.put("contact_no", contact_no);
                                params.put("email_address", email_address);
                                params.put("birthdate", birthdate);
                                params.put("marital_status", marital_status);
                                params.put("employment_status", employment_status);
                                params.put("user_description", description);

                                return params;

                            }
                        };

                        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
                    }
                }

            }
        });

        return v;
    }

}