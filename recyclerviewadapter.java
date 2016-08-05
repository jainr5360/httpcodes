package com.example.genie7.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by genie7 on 1/8/16.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<ModelClass> moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView ivprofile;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name);
            ivprofile = (ImageView) view.findViewById(R.id.iv_profile);

        }
    }

    public CustomAdapter(Context context, List<ModelClass> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowtelephonelayout, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ModelClass movie = moviesList.get(position);

        try {
            holder.title.setText(movie.getName());
            Picasso.with(context).load(movie.getProfile()).into(holder.ivprofile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
        public int getItemCount () {
            return moviesList.size();
        }
    }
