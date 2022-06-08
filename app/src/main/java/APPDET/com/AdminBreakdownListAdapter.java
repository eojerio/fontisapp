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

public class AdminBreakdownListAdapter extends ArrayAdapter<AdminBreakdownOBJ> {
    private static final String TAG =  "AdminBreakdownListAdapter";

    private Context mContext;
    int mResource;

    public AdminBreakdownListAdapter(Context context, int resource, ArrayList<AdminBreakdownOBJ> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public class valueHolderCart{
        TextView tvPriceAdminBreakdown;
        TextView tvAmountAdminBreakdown;
        TextView tvNameAdminBreakdown;
        TextView tvDescAdminBreakdown;
        ImageView ivBreakdownImage;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        setUpImageLoader();

        //GET INFORMATION
        String price = getItem(position).getPrice();
        String amount = getItem(position).getAmount();
        String name = getItem(position).getProduct_name();
        String description = getItem(position).getDesc();
        String imgURL = getItem(position).getImgURL();

        //Creating account object
        AdminBreakdownOBJ item = new AdminBreakdownOBJ(price, amount, name, description, imgURL);

        //creating layout inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //creating object from valueHolder
        valueHolderCart fields = new valueHolderCart();

        //assigning text views to xml design
        fields.tvPriceAdminBreakdown = (TextView) convertView.findViewById(R.id.tvPriceBreakdown);
        fields.tvAmountAdminBreakdown = (TextView) convertView.findViewById(R.id.tvAmountBreakdown);
        fields.tvNameAdminBreakdown = (TextView) convertView.findViewById(R.id.tvProdNameBreakdown);
        fields.tvDescAdminBreakdown = (TextView) convertView.findViewById(R.id.tvProdDescBreakdown);
        fields.ivBreakdownImage = (ImageView) convertView.findViewById(R.id.ivBreakdownImg);

        //instances of image loader for loadign images
        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //download and display image from url
        imageLoader.displayImage(imgURL, fields.ivBreakdownImage, options);


        fields.tvPriceAdminBreakdown.setText(price);
        fields.tvAmountAdminBreakdown.setText(amount);
        fields.tvNameAdminBreakdown.setText(name);
        fields.tvDescAdminBreakdown.setText(description);

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
