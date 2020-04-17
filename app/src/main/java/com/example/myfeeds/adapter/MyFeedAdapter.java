package com.example.myfeeds.adapter;

import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;


import com.example.myfeeds.R;
import com.example.myfeeds.model.FeedModel;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MyFeedAdapter extends BaseAdapter {
Context context;
ArrayList<FeedModel> feed_data;

    public MyFeedAdapter(Context context, ArrayList<FeedModel> feed_data) {
        this.context = context;
        this.feed_data = feed_data;
    }

    @Override
    public int getCount() {
        return feed_data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=view;
        ViewHolder holder;
        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.feed_item, viewGroup, false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)row.getTag();
        }
        holder.tv_title.setText(feed_data.get(i).getTitle());
        holder.tv_description.setText(feed_data.get(i).getDescription());
        try {
            String image_url=feed_data.get(i).getImageHref();


            Picasso.with(context).load(image_url)
                    .fit()
                    .placeholder(R.drawable.image_holder)
                    .into(holder.iv_feed);

        }catch(Exception e){
            e.printStackTrace();
        }


    return row;
    }

private class ViewHolder{
TextView tv_description,tv_title;
    ImageView iv_feed;
    @SuppressLint("WrongViewCast")
    public ViewHolder(View view) {
        tv_description=(TextView)view.findViewById(R.id.tv_description);
        tv_title=(TextView)view.findViewById(R.id.tv_title);
        iv_feed=(ImageView)view.findViewById(R.id.iv_feed);

    }
}






}
