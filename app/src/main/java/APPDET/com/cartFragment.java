package APPDET.com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

    TextView tvCart;
    String data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            data = savedInstanceState.getString("saved", "");
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
        savedInstanceState.putString("saved", data);
    }

    /*================================================ OKAY BEN DITO TAYO MAG C'CODE HA =====================================================================*/
    int test_holder = 0;
    String strtext = "0";

    private static final String TAG= "CartFrag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        Log.d(TAG, "onCreate: Started");

        ListView lv = (ListView) v.findViewById(R.id.lvList);

        /*
        database insert here
        while(){

        HistoryOJB account.concat('-') = new HistoryOJB(variable date, variable amount, variable total,variable dirImage);
        }
         */

        CartOBJ account1 = new CartOBJ ("₱ 150", "6", "1 Gallon Mineral water", "A mineral water","drawable://" + R.drawable.alvarez);
        CartOBJ account2 = new CartOBJ ("₱ 150", "4", "1 Gallon Mineral water", "A mineral water","drawable://" + R.drawable.alvarez);
        CartOBJ account3 = new CartOBJ ("₱ 150", "8", "1 Gallon Mineral water", "A mineral water","drawable://" + R.drawable.alvarez);
        CartOBJ account4 = new CartOBJ ("₱ 150", "2", "1 Gallon Mineral water", "A mineral water", "drawable://" + R.drawable.alvarez);

        ArrayList<CartOBJ> data = new ArrayList<>();

        data.add(account1);
        data.add(account2);
        data.add(account3);
        data.add(account4);
        data.add(account1);
        data.add(account2);
        data.add(account3);
        data.add(account4);
        data.add(account1);
        data.add(account2);
        data.add(account3);
        data.add(account4);

        CartListAdapter arrayAdapter = new CartListAdapter(getActivity(), R.layout.adapter_cartview_layout, data);
        lv.setAdapter(arrayAdapter);


        Toast.makeText(getActivity().getApplication(), "ITEM ADDED! " + test_holder, Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        //don't touch!! returns values
        return v;
    }
}