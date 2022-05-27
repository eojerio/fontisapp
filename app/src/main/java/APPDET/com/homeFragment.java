package APPDET.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    private static final String TAG= "HomeFrag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d(TAG, "onCreate: Started");

        ListView lv = (ListView) v.findViewById(R.id.lvList);

        HomeOBJ account_1 = new HomeOBJ("₱ 150", "1 Gallon Mineral water", "Water that contains a large quantity of dissolved minerals or gases","drawable://" + R.drawable.water_gallon);
        HomeOBJ account_2 = new HomeOBJ("₱ 120", "0.5 Gallon Mineral water", "Water that contains a large quantity of dissolved minerals or gases","drawable://" + R.drawable.water_gallon);
        HomeOBJ account_3 = new HomeOBJ("₱ 300", "2 Gallon Mineral water","Water that contains a large quantity of dissolved minerals or gases" ,"drawable://" + R.drawable.water_gallon);
        HomeOBJ account_4 = new HomeOBJ("₱ 450", "3 Gallon Mineral water", "Water that contains a large quantity of dissolved minerals or gases","drawable://" + R.drawable.water_gallon);

        ArrayList<HomeOBJ> data = new ArrayList<>();

        data.add(account_1);
        data.add(account_2);
        data.add(account_3);
        data.add(account_4);
        data.add(account_1);
        data.add(account_2);
        data.add(account_3);
        data.add(account_4);
        data.add(account_1);
        data.add(account_2);
        data.add(account_3);
        data.add(account_4);

        HomeListAdapter adapter = new HomeListAdapter(getActivity(), R.layout.adapter_homeview_layout, data);
        lv.setAdapter(adapter);

        //don't touch!! returns values
        return v;
    }


}