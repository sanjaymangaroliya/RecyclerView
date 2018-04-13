package com.zt.recyclerview.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zt.recyclerview.R;
import com.zt.recyclerview.global.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<HashMap<String, String>> list;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;

    public EventAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        this.activity = activity;
        this.list = list;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_short_date, tv_title, tv_description, tv_full_date;

        public MyViewHolder(View view) {
            super(view);
            tv_short_date = itemView.findViewById(R.id.tv_short_date);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_full_date = itemView.findViewById(R.id.tv_full_date);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Map<String, String> map = list.get(position);
        String title = map.get("title");
        String description = map.get("description");
        String date = map.get("date");
        String time = map.get("time");

        //Title
        if (Utils.isNotEmptyString(title)) {
            String text = "<font color=#F47E02>Title: </font><font color=#000000>" + title + "</font>";
            holder.tv_title.setText(Html.fromHtml(text));
        }
        //Description
        if (Utils.isNotEmptyString(description)) {
            String text = "<font color=#F47E02>Description: </font><font color=#000000>" + description + "</font>";
            holder.tv_description.setText(Html.fromHtml(text));
        }
        //Sort Date
        if (Utils.isNotEmptyString(date)) {
            holder.tv_short_date.setText(Utils.convertDate(date));
        }
        //Full Date
        if (Utils.isNotEmptyString(date)) {
            String text = "<font color=#F47E02>Date: </font><font color=#000000>" + date + " : " + time + "</font>";
            holder.tv_full_date.setText(Html.fromHtml(text));
        }
        //Image
        //ImageLoader.getInstance().displayImage(hotel_image,
        // holder.img_hotels, options, animateFirstListener);
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}