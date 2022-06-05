package APPDET.com;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import java.util.Map;
import java.util.regex.Pattern;

public class HomeListAdapter extends ArrayAdapter<HomeOBJ> {
    private static final String TAG =  "HomeListAdapter";

    private Context mContext;
    int mResource;

    public String replaceAll(String regex, String replacement) {
        return Pattern.compile(regex).matcher((CharSequence) this).replaceAll(replacement);
    }

    public HomeListAdapter(Context context, int resource, ArrayList<HomeOBJ> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public class valueHolder{
        TextView tvPriceHome;
        TextView tvProdName;
        TextView tvProdDesc;
        ImageView ivHomeImg;
        Button btnAddToCart;
        ListView lvListHome;
    }

    public String getTag(String tag){
        String cart_Prodtag = tag;
        return cart_Prodtag;
    }

    public int getPositionID(int position){
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        setUpImageLoader();

        //GET INFORMATION
        String price = getItem(position).getPrice();
        String name = getItem(position).getProdName();
        String desc = getItem(position).getProdDesc();
        int imgURL = getItem(position).getImgURL();
        int fk_id = getItem(position).getfk_id();

        //Creating account object
        HomeOBJ account = new HomeOBJ(fk_id, price, name, desc, imgURL);

        //creating layout inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //creating object from valueHolder
        valueHolder fields = new valueHolder();

        //assigning text views to xml design
        fields.tvPriceHome = (TextView) convertView.findViewById(R.id.tvPriceHome);
        fields.tvProdName = (TextView) convertView.findViewById(R.id.tvProductNameHome);
        fields.tvProdDesc = (TextView) convertView.findViewById(R.id.tvProductDescHome);
        fields.ivHomeImg = (ImageView) convertView.findViewById(R.id.ivHomeImg);
        fields.btnAddToCart = (Button) convertView.findViewById(R.id.btnAddToCart);
        fields.lvListHome = (ListView) convertView.findViewById(R.id.lvList);

        //click event increase
        fields.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //final fields
                    final String fk_IDuser = String.valueOf(fk_id);
                    final String product_tag = String.valueOf(position+1);
                    final String product_price = price.replaceAll("₱","").trim();
                    final String product_name = name.trim();
                    final String product_description = desc.trim();
                    final String product_quantity = "1"; //constant 1
                    final String product_img = String.valueOf(imgURL);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ADDCART, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //if success
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                //if success
                                if(!jsonObject.getBoolean("error")){
                                    Toast.makeText(mContext.getApplicationContext(), "Added to cart" , Toast.LENGTH_SHORT).show();

                                }else{
                                    //shows error
                                    Toast.makeText(mContext.getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(mContext.getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("cart_userID", fk_IDuser);
                            params.put("cart_prodTag", product_tag);
                            params.put("cart_prodPrice", product_price);
                            params.put("cart_prodName", product_name);
                            params.put("cart_prodDesc", product_description);
                            params.put("cart_prodQty", product_quantity);
                            params.put("cart_prodImg", product_img);

                            return params;

                        }
                    };

                    RequestHandler.getInstance(mContext.getApplicationContext()).addToRequestQueue(stringRequest);
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
        imageLoader.displayImage("drawable://"+imgURL, fields.ivHomeImg, options);

        fields.tvPriceHome.setText(price);
        fields.tvProdName.setText(name);
        fields.tvProdDesc.setText(desc);



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
