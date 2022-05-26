package APPDET.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        //assigning text views to xml design
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tvPriceCart);
        TextView tvProdName = (TextView) convertView.findViewById(R.id.tvProdNameCart);
        TextView tvProdAmount = (TextView) convertView.findViewById(R.id.tvAmountCart);
        TextView tvProdDesc = (TextView) convertView.findViewById(R.id.tvProdDescCart);
        ImageView historyCartImg = (ImageView) convertView.findViewById(R.id.ivCartImg);

        //instances of image loader for loadign images
        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //download and display image from url
        imageLoader.displayImage(imgURL, historyCartImg, options);

        tvPrice.setText(price);
        tvProdName.setText(name);
        tvProdDesc.setText(description);
        tvProdAmount.setText(amount);

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
