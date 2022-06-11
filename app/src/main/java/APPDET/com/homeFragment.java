package APPDET.com;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    int product1_amount=1;

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

    ListView lv;
    ArrayList<HomeOBJ> data;

    private static final String TAG= "HomeFrag";

    //list of array int for images from drawables
    public int[] image = {R.drawable.prod1c, R.drawable.prod2c, R.drawable.prod3c, R.drawable.prod4c, R.drawable.prod5c,
            R.drawable.prod6c, R.drawable.prod7c, R.drawable.prod8c, R.drawable.prod9c, R.drawable.prod10c, R.drawable.prod11c,
            R.drawable.prod12c, R.drawable.prod13c, R.drawable.prod14c, R.drawable.prod15c, R.drawable.prod16c};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d(TAG, "onCreate: Started");

        final int fk_cart = SharedPreferenceManager.getInstance(getContext()).getUserID();

        lv = (ListView) v.findViewById(R.id.lvList);

        //list of product objects
       HomeOBJ prod1 = new HomeOBJ(fk_cart,"₱35", "Fontis 5 Gallon Mineral Water(refill)", "Refreshing natural mineral water in a round container best used for water dispensers.", image[0]);
       HomeOBJ prod2 = new HomeOBJ(fk_cart,"₱155", "Fontis 5 Gallon Mineral Water(non-refill)", "Refreshing natural mineral water in a round container best used for water dispensers.", image[0]);
       HomeOBJ prod3 = new HomeOBJ(fk_cart,"₱149", "Fontis 5 Gallon Mineral Water(Slim container)","Refreshing natural mineral water in a slim container with faucet for portability." , image[1]);
       HomeOBJ prod4 = new HomeOBJ(fk_cart,"₱75", "Fontis 2.5 Gallon Mineral Water", "Compact water container with faucet, good for carrying on picnics and outdoor activities.", image[2]);
       HomeOBJ prod5 = new HomeOBJ(fk_cart,"₱68", "Fontis 5 liter Mineral Water", "Portable water container for quick thirst quencher and refreshment.", image[3]);
       HomeOBJ prod6 = new HomeOBJ(fk_cart,"₱195", "3 pack Fontis 5 liter Mineral Water", "If one is not enough, add more refreshing natural mineral water for quick thirst quencher and refreshment.", image[4]);
       HomeOBJ prod7 = new HomeOBJ(fk_cart,"₱15", "Fontis 1 liter Mineral Water", "On-the-go refreshment.", image[5]);
       HomeOBJ prod8 = new HomeOBJ(fk_cart,"₱40", "3 pack Fontis 1 liter Mineral Water", "On-the-go refreshment", image[6]);
       HomeOBJ prod9 = new HomeOBJ(fk_cart,"₱20", "Fontis 1 liter Alkaline  Water","A good way to rehydrate more effectively after exercise." , image[7]);
       HomeOBJ prod10 = new HomeOBJ(fk_cart,"₱55", "3 pack Fontis 1 liter Alkaline Water", "Alkaline thirst quencher to share with a friend or gym buddies after exercising.", image[8]);
       HomeOBJ prod11 = new HomeOBJ(fk_cart,"₱50", "Fontis 5 Gallon Purified Water(refill)", "Purified refreshment in a round container best used for water dispensers.", image[9]);
       HomeOBJ prod12 = new HomeOBJ(fk_cart,"₱165", "Fontis 5 Gallon Purified Water(non-refill)", "Purified refreshment in a round container best used for water dispensers.", image[9]);
       HomeOBJ prod13 = new HomeOBJ(fk_cart,"₱159", "Fontis 5 Gallon Purified Water(Slim container)", "Purified refreshment mineral water in a slim container with faucet for portability.", image[10]);
       HomeOBJ prod14 = new HomeOBJ(fk_cart,"₱85", "Fontis 2.5 Gallon Purified Water","Compact purified water, good for carrying on picnics and outdoor activities." , image[11]);
       HomeOBJ prod15 = new HomeOBJ(fk_cart,"₱75", "Fontis 5 liter Purified Water", "Portable purified water container for quick thirst quencher and refreshment.", image[12]);
       HomeOBJ prod16 = new HomeOBJ(fk_cart,"₱210", "3 pack Fontis 5 liter Purified Water", "If one is not enough, add more refreshing purified water for quick thirst quencher and refreshment.", image[13]);
       HomeOBJ prod17 = new HomeOBJ(fk_cart,"₱30", "Fontis 16 ounce infused Water", "A refreshing and healthy twist to your good old thirst quencher.(comes in different flavors: lime, strawberry, apple, kiwi, cucumber & peach)", image[14]);
       HomeOBJ prod18 = new HomeOBJ(fk_cart,"₱169", "Fontis 24 ounce Stainless Steel Bottle", "Get your own insulated bottle to store your favorite beverage for free on every ₱ 399 worth of purchase or get this item for ₱ 169.", image[15]);


        data = new ArrayList<>();

        data.add(prod1);
        data.add(prod2);
        data.add(prod3);
        data.add(prod4);
        data.add(prod5);
        data.add(prod6);
        data.add(prod7);
        data.add(prod8);
        data.add(prod9);
        data.add(prod10);
        data.add(prod11);
        data.add(prod12);
        data.add(prod13);
        data.add(prod14);
        data.add(prod15);
        data.add(prod16);
        data.add(prod17);
        data.add(prod18);

        if (getActivity()!=null) {
            HomeListAdapter adapter = new HomeListAdapter(getActivity(), R.layout.adapter_homeview_layout, data);
            lv.setAdapter(adapter);
        }
        //don't touch!! returns values
        return v;
    }
}