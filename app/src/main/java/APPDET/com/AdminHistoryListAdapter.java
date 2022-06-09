package APPDET.com;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdminHistoryListAdapter extends ArrayAdapter<AdminHistoryOBJ> {

    private static final String TAG =  "AdminHistoryListAdapter";
    String positionID;

    private Context mContext;
    int mResource;


    public AdminHistoryListAdapter(Context context, int resource, ArrayList<AdminHistoryOBJ> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public class valueHolderCart{
        TextView tvPriceAdminHistory;
        TextView tvUserAdminHistory;
        TextView tvDateAdminHistory;
        TextView tvAmountAdminHistory;
        TextView tvAddressAdminHistory;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //GET INFORMATION
        String price = getItem(position).getPrice();
        String user = getItem(position).getUser();
        String date = getItem(position).getDate();
        String amount = getItem(position).getAmountAdmin();
        String address = getItem(position).getAddress();

        //Creating account object
        AdminHistoryOBJ item = new AdminHistoryOBJ(price, user, date, amount, address);

        //creating layout inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //creating object from valueHolder
        valueHolderCart fields = new valueHolderCart();

        //assigning text views to xml design
        fields.tvPriceAdminHistory = (TextView) convertView.findViewById(R.id.tvPriceAdminHistory);
        fields.tvUserAdminHistory = (TextView) convertView.findViewById(R.id.tvUserAdminHistory);
        fields.tvDateAdminHistory = (TextView) convertView.findViewById(R.id.tvDateAdminHistory);
        fields.tvAmountAdminHistory = (TextView) convertView.findViewById(R.id.tvAmountAdminHistory);
        fields.tvAddressAdminHistory = (TextView) convertView.findViewById(R.id.tvAddressAdminHistory);


        fields.tvPriceAdminHistory.setText(price);
        fields.tvUserAdminHistory.setText(user);
        fields.tvDateAdminHistory.setText(date);
        fields.tvAmountAdminHistory.setText(amount);
        fields.tvAddressAdminHistory.setText(address);

        return convertView;
    }
}
