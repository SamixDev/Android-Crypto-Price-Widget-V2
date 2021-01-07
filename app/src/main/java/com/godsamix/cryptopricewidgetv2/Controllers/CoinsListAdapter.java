package com.godsamix.cryptopricewidgetv2.Controllers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsamix.cryptopricewidgetv2.R;

import java.util.List;

public class CoinsListAdapter extends RecyclerView.Adapter<CoinsListAdapter.ViewHolder> {
    private final List<CoinsListController> hard;
    private final Context context;


    public CoinsListAdapter(Context context, List<CoinsListController> hardware){
        this.hard = hardware;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CoinsListController lst = hard.get(position);
        holder.id.setText(lst.getID());
        holder.name.setText(lst.getName());
        holder.symbol.setText(lst.getSymbol());

        holder.itemView.setOnClickListener(v -> {
//                Bundle bundle = new Bundle();
//                bundle.putString("HardwareCode", holder.code.getText().toString());
//                bundle.putString("HardwareType", hardwareItemsListFragment.args);
//                Navigation.findNavController(v).navigate(R.id.action_nav_hardware_to_nav_hardware_specs,bundle);
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coins_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return hard.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView id;
        private final TextView name;
        private final TextView symbol;
        private final ImageView addcoin;
        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            symbol = itemView.findViewById(R.id.code);
            addcoin = itemView.findViewById(R.id.addcoin);
            addcoin.setVisibility(View.VISIBLE);
        }
    }
}