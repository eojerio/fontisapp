package APPDET.com;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdminListAdapter extends ArrayAdapter<AdminOBJ> {
    private static final String TAG =  "AdminListAdapter";
    String positionIMG;

    private Context mContext;
    int mResource;


    public AdminListAdapter(Context context, int resource, ArrayList<AdminOBJ> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public class valueHolderCart{
        TextView tvPriceAdmin;
        TextView tvUserAdmin;
        TextView tvDateAdmin;
        TextView tvAmountAdmin;
        TextView tvAddressAdmin;
        Button btnAccept;
        Button btnDetails;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //GET INFORMATION
        String price = getItem(position).getPrice();
        String user = getItem(position).getUser();
        String date = getItem(position).getDate();
        String amount = getItem(position).getAmountAdmin();
        String address = getItem(position).getAddress();

        //Creating account object
        AdminOBJ item = new AdminOBJ(price, user, date, amount, address);

        //creating layout inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //creating object from valueHolder
        valueHolderCart fields = new valueHolderCart();

        //assigning text views to xml design
        fields.tvPriceAdmin = (TextView) convertView.findViewById(R.id.tvPriceAdmin);
        fields.tvUserAdmin = (TextView) convertView.findViewById(R.id.tvUserAdmin);
        fields.tvDateAdmin = (TextView) convertView.findViewById(R.id.tvDateAdmin);
        fields.tvAmountAdmin = (TextView) convertView.findViewById(R.id.tvAmountAdmin);
        fields.tvAddressAdmin = (TextView) convertView.findViewById(R.id.tvAddressAdmin);
        fields.btnAccept = (Button) convertView.findViewById(R.id.btnAccept);
        fields.btnDetails = (Button) convertView.findViewById(R.id.btnDetails);

        fields.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();


            }
        });

        fields.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();


            }
        });



        fields.tvPriceAdmin.setText(price);
        fields.tvUserAdmin.setText(user);
        fields.tvDateAdmin.setText(date);
        fields.tvAmountAdmin.setText(amount);
        fields.tvAddressAdmin.setText(address);

        return convertView;
    }

}
