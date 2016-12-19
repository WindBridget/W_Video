package com.windbridget.w_videoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
/**
 * Created by Ru on 11/18/2016.
 */

public class VideoAdapter extends BaseAdapter{

    class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView videoid;
    }

    public ArrayList<Video> listVideo;
    LayoutInflater infater = null;
    private Context mContext;


    public VideoAdapter(Context context, ArrayList<Video> apps) {
        infater = LayoutInflater.from(context);
        mContext = context;
        this.listVideo = apps;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listVideo.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listVideo.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = infater.inflate(R.layout.item_video,
                    null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.image);
            holder.title = (TextView) convertView
                    .findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Video item = (Video) getItem(position);
        if (item != null) {

            holder.title.setText(item.getTitle());
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(item.getUrl(), holder.imageView);


        }
        return convertView;
    }

}
