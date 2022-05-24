package APPDET.com;

import android.app.Person;
import android.content.Context;
import android.media.Image;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends ArrayAdapter<HistoryOJB> {
    private static final String TAG =  "HistoryListAdapter";

    private Context mContext;
    int mResource;

    public HistoryListAdapter(Context context, int resource, ArrayList<HistoryOJB> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        setUpImageLoader();

        //GET INFORMATION
        String date = getItem(position).getDate();
        String amount = getItem(position).getAmount();
        String total = getItem(position).getTotal();
        String imgURL = getItem(position).getImgURL();

        //Creating account object
        HistoryOJB account = new HistoryOJB(date, amount, total, imgURL);

        //creating layout inflater
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //assigning text views to xml design
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDateHistory);
        TextView tvAmount = (TextView) convertView.findViewById(R.id.tvAmountHistory);
        TextView tvTotal = (TextView) convertView.findViewById(R.id.tvTotalCostHistory);
        ImageView historyCartImg = (ImageView) convertView.findViewById(R.id.ivHistoryImg);

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

        tvDate.setText(date);
        tvAmount.setText(amount);
        tvTotal.setText(total);

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
