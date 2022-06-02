package APPDET.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link accountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class accountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public accountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment accountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static accountFragment newInstance(String param1, String param2) {
        accountFragment fragment = new accountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*================================================ OKAY BEN DITO TAYO MAG C'CODE HA =====================================================================*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button btnEditAccount, btnLogout;
    TextView name,address,contact_no,email_address,birthdate,marital_status,employment_status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);


            name = (TextView) v.findViewById(R.id.tvNameAccount);
            address = (TextView) v.findViewById(R.id.tvAddress2);
            contact_no = (TextView) v.findViewById(R.id.tvContactNo2);
            email_address = (TextView) v.findViewById(R.id.tvEmailAddress2);
            birthdate = (TextView) v.findViewById(R.id.tvBirthdate2);
            marital_status = (TextView) v.findViewById(R.id.tvMaritalStatus2);
            employment_status = (TextView) v.findViewById(R.id.tvEmploymentStatus2);

            //assigning textview values from database through SharedPreferenceManager

            name.setText(SharedPreferenceManager.getInstance(getContext()).getFirstName() +" "+ SharedPreferenceManager.getInstance(getContext()).getLastName());
            address.setText(SharedPreferenceManager.getInstance(getContext()).getAddress2());
            contact_no.setText(SharedPreferenceManager.getInstance(getContext()).getContactNo2());
            email_address.setText(SharedPreferenceManager.getInstance(getContext()).getEmailAddress2());
            birthdate.setText(SharedPreferenceManager.getInstance(getContext()).getBirthdate2());
            marital_status.setText(SharedPreferenceManager.getInstance(getContext()).getMaritalStatus2());
            employment_status.setText(SharedPreferenceManager.getInstance(getContext()).getEmploymentStatus2());




            //methods for button

            btnEditAccount = (Button) v.findViewById(R.id.btnSaveAccount);
            btnEditAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Fragment edit_account = new account_edit();
                  getParentFragmentManager().beginTransaction().replace(R.id.container_botnav,edit_account).commit();

                }
            });

            btnLogout = (Button) v.findViewById(R.id.btnLogout);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferenceManager.getInstance(getContext()).logout();
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
            });

        return v;
    }


}