package com.mohit.puchosampleapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohit.puchosampleapp.Objects.Data;
import com.mohit.puchosampleapp.R;

import java.util.List;

/**
 * Created by mohit on 11/10/16.
 */

public class BlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public Context thisContext;
    public LayoutInflater layoutInflater;
    private List<Data> dataList;

    public BlogAdapter(Context context) {
        thisContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(thisContext).inflate(R.layout.card_data,parent,false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BlogViewHolder blogViewHolder = (BlogViewHolder) holder;
        blogViewHolder.tvTitle.setText(dataList.get(position).getTitle());
        blogViewHolder.tvDetails.setText(dataList.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        if(dataList != null) {
            return dataList.size();
        };

        return 0;
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDetails;

        public BlogViewHolder(final View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDetails = (TextView) itemView.findViewById(R.id.tvDetails);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }
}
