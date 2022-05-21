package APPDET.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Button product1_increase, product1_decrease;
    int product1_amount=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //RETRIEVE FROM DATABASE VALUES






        if (savedInstanceState != null) {
            // Restore value of members from saved state
            product1_amount = savedInstanceState.getInt("saved", 0);
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("saved", product1_amount);
    }

    /*================================================ OKAY BEN DITO TAYO MAG C'CODE HA =====================================================================*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        product1_increase = (Button) v.findViewById(R.id.btnIncrease1);
        product1_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product1_amount++;
                Toast.makeText(getActivity().getApplication(), "ITEM ADDED! " + product1_amount, Toast.LENGTH_SHORT).show();

                Bundle result = new Bundle();
                result.putString("ID", Integer.toString(product1_amount));


                getParentFragmentManager().setFragmentResult("dataFromHome", result);


            }
        });

        product1_decrease = (Button) v.findViewById(R.id.btnDecrease1);
        product1_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product1_amount--;
                Toast.makeText(getActivity().getApplication(), "ITEM REMOVED! " + product1_amount, Toast.LENGTH_SHORT).show();

                Bundle result = new Bundle();
                result.putString("ID", Integer.toString(product1_amount));


                getParentFragmentManager().setFragmentResult("dataFromHome", result);

            }
        });

        //don't touch!! returns values
        return v;
    }


}