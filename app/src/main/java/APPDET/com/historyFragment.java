package APPDET.com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link historyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class historyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public historyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment historyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static historyFragment newInstance(String param1, String param2) {
        historyFragment fragment = new historyFragment();
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


    /*================================================ OKAY BEN DITO TAYO MAG C'CODE HA =====================================================================*/

    private static final String TAG= "MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_history, container, false);

        Log.d(TAG, "onCreate: Started");

        ListView lv = (ListView) v.findViewById(R.id.lvList);

        //generateListContent();
        HistoryOJB account1 = new HistoryOJB("12-20-2021", "6 Items", "P1200","drawable://" + R.drawable.alvarez);
        HistoryOJB account2 = new HistoryOJB("12-20-2021", "4 Items", "P6200","drawable://" + R.drawable.alvarez);
        HistoryOJB account3 = new HistoryOJB("12-20-2021", "8 Items", "P4200","drawable://" + R.drawable.alvarez);
        HistoryOJB account4 = new HistoryOJB("12-20-2021", "2 Items", "P2200","drawable://" + R.drawable.alvarez);



        ArrayList<HistoryOJB> data = new ArrayList<>();


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

        PersonListAdapter arrayAdapter = new PersonListAdapter(getActivity(), R.layout.adapter_historyview_layout, data);
        lv.setAdapter(arrayAdapter);
        // Inflate the layout for this fragment
        return v;

    }

    public void generateListContent(){
        for(int i = 1; i<20; i++){
            //data.add("This is test example " + i);
        }
    }
}