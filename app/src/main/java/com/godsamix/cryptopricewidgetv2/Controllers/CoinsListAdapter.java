package com.godsamix.cryptopricewidgetv2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsamix.cryptopricewidgetv2.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CoinsListAdapter extends RecyclerView.Adapter<CoinsListAdapter.ViewHolder> {
    private final List<CoinsListController> hard;
    private final Context context;
    public  static SharedPreferences sharedPreferences;
    public static String coinsJsonList;
    List<JSONObject> jj;
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

        holder.addcoin.setOnClickListener(v -> {
            Log.e("btn" , "btn works "+ lst.getID() );
            sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
            coinsJsonList = sharedPreferences.getString("coins", "");
            Gson gson = new Gson();
            CoinsListController coinprefs =  new CoinsListController(lst.getID(),lst.getName(),lst.getSymbol());
            String objString = gson.toJson(coinprefs);
      //      Log.e("js" , objString);
            if (coinsJsonList.isEmpty()){
         //       Log.e("empty" , "empty " + coinsJsonList);
            }else{
                Log.e("not empty" , coinsJsonList);
                jj = gson.fromJson(coinsJsonList, new TypeToken<List<JsonObject>>(){}.getType());
         //       Log.e("new arr" , jj.toString());
            }

           // Log.e("list" , jj.toString());
            try {
                JSONObject jsonObject = new JSONObject(objString);
           //     Log.e("js obj" , jsonObject.toString());
                jj.add(jsonObject);
             //   Log.e("js" , jj.toString());

                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("coins",jj.toString());
                myEdit.commit();
                   Log.e("shared" , sharedPreferences.getString("coins", ""));
            } catch (JSONException e) {
                e.printStackTrace();
            }


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