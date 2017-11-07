package com.zt.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zt.recyclerview.R;
import com.zt.recyclerview.global.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.PlaceOrderHolder> {

    private Context context;
    private ArrayList<HashMap<String, String>> arrayList;

    public EventAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.arrayList = list;
    }

    public class PlaceOrderHolder extends RecyclerView.ViewHolder {
        private TextView tv_short_date, tv_title, tv_description, tv_full_date;

        public PlaceOrderHolder(View itemView) {
            super(itemView);
            this.tv_short_date = (TextView) itemView.findViewById(R.id.tv_short_date);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            this.tv_full_date = (TextView) itemView.findViewById(R.id.tv_full_date);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public PlaceOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_event, parent, false);
        PlaceOrderHolder viewHolder = new PlaceOrderHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PlaceOrderHolder holder, final int position) {

        Map<String, String> map = arrayList.get(position);
        String title = map.get("title");
        String description = map.get("description");
        String date = map.get("date");
        String time = map.get("time");

        //Title
        if (!Utils.isStringNull(title)) {
            String text = "<font color=#F47E02>Title: </font><font color=#000000>" + title + "</font>";
            holder.tv_title.setText(Html.fromHtml(text));
        }
        //Description
        if (!Utils.isStringNull(description)) {
            String text = "<font color=#F47E02>Description: </font><font color=#000000>" + description + "</font>";
            holder.tv_description.setText(Html.fromHtml(text));
        }
        //Sort Date
        if (!Utils.isStringNull(date)) {
            holder.tv_short_date.setText(Utils.convertDate(date));
        }
        //Full Date
        if (!Utils.isStringNull(date)) {
            String text = "<font color=#F47E02>Date: </font><font color=#000000>" + date + " : " + time + "</font>";
            holder.tv_full_date.setText(Html.fromHtml(text));
        }
    }
}
