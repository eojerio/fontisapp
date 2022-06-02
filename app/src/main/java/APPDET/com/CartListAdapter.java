package APPDET.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends ArrayAdapter<CartOBJ> {
    private static final String TAG =  "CartListAdapter";

    private Context mContext;
    int mResource;

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
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        setUpImageLoader();

        //GET INFORMATION
        String price = getItem(position).getPrice();
        String amount = getItem(position).getAmount();
        String name = getItem(position).getProductName();
        String description = getItem(position).getDesc();
        String imgURL = getItem(position).getImgURL();

        //Creating account object
        CartOBJ account = new CartOBJ(price, amount, name, description, imgURL);

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

        //click event increase
        fields.btnIncreaseCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item added at " + position, Toast.LENGTH_SHORT).show();


                //insert database insertion here
            }
        });

        //click event decrease
        fields.btnDecreaseCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item removed at " + position, Toast.LENGTH_SHORT).show();
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
