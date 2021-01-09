package com.godsamix.cryptopricewidgetv2.Helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsamix.cryptopricewidgetv2.R;
import com.squareup.picasso.Picasso;

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.ViewHolder> {
    private String[] id;
    private String[] name;
    private String[] symbol;
    private String[] image;

    public SimpleRVAdapter(String[] idArgs, String[] nameArgs, String[] symbolArgs, String[] imageArgs){
        id = idArgs;
        name = nameArgs;
        symbol = symbolArgs;
        image = imageArgs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coins_list, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView idtext;
        public TextView codetext;
        public TextView nametext;
        public ImageView imgview;

        public ViewHolder(View itemView ) {
            super(itemView);
            idtext = itemView.findViewById(R.id.id);
            codetext = itemView.findViewById(R.id.code);
            nametext = itemView.findViewById(R.id.name);
            imgview = itemView.findViewById(R.id.img);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.idtext.setText(id[position]);
        holder.codetext.setText(symbol[position]);
        holder.nametext.setText(name[position]);
        Picasso.get().load(image[position]).into(holder.imgview);
       // holder.imgview.setImageResource(R.drawable.additem);
    }

    @Override
    public int getItemCount() {
        return id.length;
    }
}
