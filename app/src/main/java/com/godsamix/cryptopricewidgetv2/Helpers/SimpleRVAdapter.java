package com.godsamix.cryptopricewidgetv2.Helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.godsamix.cryptopricewidgetv2.R;

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.ViewHolder> {
    private String[] dataSource;
    private String[] nameSource;
    public SimpleRVAdapter(String[] dataArgs, String[] nameArgs){
        dataSource = dataArgs;
        nameSource = nameArgs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coins_list, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public TextView textView2;
        public ImageView imgview;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.code);
            textView2 = itemView.findViewById(R.id.name);
            imgview = itemView.findViewById(R.id.img);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataSource[position]);
        holder.textView2.setText(nameSource[position]);
        holder.imgview.setImageResource(R.drawable.additem);;
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }
}
