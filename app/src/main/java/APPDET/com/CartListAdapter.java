package APPDET.com;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartListAdapter extends ArrayAdapter<CartOBJ> {
    private static final String TAG =  "CartListAdapter";
    String positionIMG;

    private Context mContext;
    int mResource;


    ProgressDialog load;



    public CartListAdapter(Context context, int resource, ArrayList<CartOBJ> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public class valueHolderCart{
        TextView tvPrice;
        TextView tvProdName;
        TextView tvProdAmount;
        TextView tvProdDesc;
        ImageView historyCartImg;
        Button btnIncreaseCart;
        Button btnDecreaseCart;
        Button btnDeleteItem;

    }

    public String getPositionIMG(){
        return positionIMG;
    }

    public void setPositionIMG(String position){
        this.positionIMG = position;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        setUpImageLoader();

        setPositionIMG(String.valueOf(position));
        //GET INFORMATION
        String tag = getItem(position).getTag();
        String price = getItem(position).getPrice();
        String amount = getItem(position).getAmount();
        String name = getItem(position).getProductName();
        String description = getItem(position).getDesc();
        String imgURL = getItem(position).getImgURL();

        //Creating account object
        CartOBJ item = new CartOBJ(tag, price, amount, name, description, imgURL);

        //creating layout inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);



        //creating object from valueHolder
        valueHolderCart fields = new valueHolderCart();

        //assigning text views to xml design
        fields.tvPrice = (TextView) convertView.findViewById(R.id.tvPriceCart);
        fields.tvProdName = (TextView) convertView.findViewById(R.id.tvProdNameCart);
        fields.tvProdAmount = (TextView) convertView.findViewById(R.id.tvAmountCart);
        fields.tvProdDesc = (TextView) convertView.findViewById(R.id.tvProdDescCart);
        fields.historyCartImg = (ImageView) convertView.findViewById(R.id.ivCartImg);
        fields.btnIncreaseCart = (Button) convertView.findViewById(R.id.btnIncrease);
        fields.btnDecreaseCart = (Button) convertView.findViewById(R.id.btnDecrease);
        fields.btnDeleteItem = (Button) convertView.findViewById(R.id.btnDelete);

        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());


        //progress dialogue
        load = new ProgressDialog(getContext());
//
//        load.setMessage("LOADING HISTORY...");
//        load.setCancelable(false);
//        load.setCanceledOnTouchOutside(false);
//        load.show();

        //dialog process
        final Handler handler = new Handler();

        //click event for delete item
        fields.btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userID = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());
                final String item_tag = getItem(position).getTag();

                //alert dialog for confirming delete
                alert_builder.setMessage("Are you sure you want to delete this item?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_DELETEITEMCART, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    if(!jsonObject.getBoolean("error")) {
                                        Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                        Fragment cart = new cartFragment();
                                        ((index_form)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container_botnav,cart).commit();
                                    }else{
                                        //shows error
                                        Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("cart_userID", userID);
                                params.put("cart_prodTag", item_tag);
                                return params;
                            }
                        };
                        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = alert_builder.create();
                alertDialog.setTitle("WARNING");
                alertDialog.show();

            }
        });

        //click event increase
        fields.btnIncreaseCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Incrementing
                final String userID = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());
                final String item_tag = getItem(position).getTag();

                final String item_qty = String.valueOf(Integer.parseInt(getItem(position).getAmount()) + 1);

                //i need to reset

                //Integer.parseInt(getItem(position).getAmount())

                //insert database insertion here
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATECART, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")){

                                //retrieving data
                                SharedPreferenceManager.getInstance(getContext()).getCartDetails(jsonObject.getString("cart_prodQty"));

                                fields.tvProdAmount.setText(SharedPreferenceManager.getInstance(getContext()).getCartQty());

                                Fragment cart = new cartFragment();
                                ((index_form)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container_botnav,cart).commit();
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
                        params.put("cart_userID",userID);
                        params.put("cart_prodTag",item_tag);
                        params.put("cart_prodQty",item_qty);

                        return params;
                    }
                };

                RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

            }
        });

        //click event decrease
        fields.btnDecreaseCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Decrementing
                final String userID = String.valueOf(SharedPreferenceManager.getInstance(getContext()).getUserID());
                final String item_tag = getItem(position).getTag();

                final String item_qty = String.valueOf(Integer.parseInt(getItem(position).getAmount()) - 1);


                //insert database insertion here
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATECART, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")){

                                SharedPreferenceManager.getInstance(getContext()).getCartDetails(jsonObject.getString("cart_prodQty"));

                                fields.tvProdAmount.setText(SharedPreferenceManager.getInstance(getContext()).getCartQty());
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
                        params.put("cart_userID",userID);
                        params.put("cart_prodTag",item_tag);
                        params.put("cart_prodQty",item_qty);

                        return params;
                    }
                };

                RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
                Fragment cart = new cartFragment();
                ((index_form)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container_botnav,cart).commit();
            }
        });

        //instances of image loader for loadign images
        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //download and display image from url
        imageLoader.displayImage(imgURL, fields.historyCartImg, options);

        fields.tvPrice.setText(price);
        fields.tvProdName.setText(name);
        fields.tvProdDesc.setText(description);
        fields.tvProdAmount.setText(amount);

        return convertView;
    }


    private void setUpImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}
