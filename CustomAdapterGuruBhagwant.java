package me.kaelaela.sample.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.kaelaela.sample.Model.GurunameModel;
import me.kaelaela.sample.R;

/**
 * Created by genie7 on 3/6/16.
 */
public class CustomAdapterGuruBhagwant extends BaseAdapter {

    private Activity context;
    private ArrayList<GurunameModel> mList;
    private static LayoutInflater inflater;

    public CustomAdapterGuruBhagwant(Activity context, ArrayList<GurunameModel> pUserList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.mList = pUserList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder {
        public de.hdodenhof.circleimageview.CircleImageView profileImage;
        public TextView profileName;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder holder;
        View rowView = view;
        if (rowView == null) {

            rowView = inflater.inflate(R.layout.rowgurubhagwatlayout, parent,false);

            holder = new ViewHolder();
            holder.profileImage = (de.hdodenhof.circleimageview.CircleImageView) rowView.findViewById(R.id.profileImage);
            holder.profileName = (TextView) rowView.findViewById(R.id.Guruname);

            rowView.setTag(holder);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        try {
            holder.profileName.setText(mList.get(position).getGuruname());
            Picasso.with(context).load(mList.get(position).getProfileImage()).into(holder.profileImage);

        }catch (Exception e){e.printStackTrace();}


        return rowView;
    }

}
