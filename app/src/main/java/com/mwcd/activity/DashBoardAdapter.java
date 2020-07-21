package com.mwcd.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.MyViewHolder> {

    private Context mContext;
    private List<DashboardItem> albumList;
    private CustomOnClickListener onClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public LinearLayout androidName;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewName);
            thumbnail = (ImageView) view.findViewById(R.id.imageView);
            androidName = (LinearLayout)view.findViewById(R.id.android_name);
        }
    }


    public DashBoardAdapter(Context mContext, List<DashboardItem> albumList,CustomOnClickListener onClick) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.onClick=onClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_dashboard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DashboardItem album = albumList.get(position);

        holder.title.setText(album.getName());
        holder.thumbnail.setImageDrawable(mContext.getDrawable(album.getDataId()));
        holder.androidName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
