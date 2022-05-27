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

public class HomeListAdapter extends ArrayAdapter<HomeOBJ> {
    private static final String TAG =  "HomeListAdapter";

    private Context mContext;
    int mResource;

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
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        setUpImageLoader();

        //GET INFORMATION
        String price = getItem(position).getPrice();
        String name = getItem(position).getProdName();
        String desc = getItem(position).getProdDesc();
        String imgURL = getItem(position).getImgURL();

        //Creating account object
        HomeOBJ account = new HomeOBJ(price, name, desc, imgURL);

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
        //click event increase
        fields.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item added to cart at " + position, Toast.LENGTH_SHORT).show();
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
        imageLoader.displayImage(imgURL, fields.ivHomeImg, options);

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
