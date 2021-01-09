package com.godsamix.cryptopricewidgetv2.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.godsamix.cryptopricewidgetv2.MainActivity;
import com.godsamix.cryptopricewidgetv2.R;
import com.godsamix.cryptopricewidgetv2.coinsListView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.godsamix.cryptopricewidgetv2.MainActivity.canAdd;
import static com.godsamix.cryptopricewidgetv2.MainActivity.coinsJsonList;
import static com.godsamix.cryptopricewidgetv2.MainActivity.jsonarray;
import static com.godsamix.cryptopricewidgetv2.MainActivity.listID;
import static com.godsamix.cryptopricewidgetv2.MainActivity.sharedPreferences;

public class CoinsListAdapter extends RecyclerView.Adapter<CoinsListAdapter.ViewHolder> {
    private final List<CoinsListController> hard;
    private final Context context;

    //public  static SharedPreferences sharedPreferences;
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
        holder.addOrDel = true; //true add, false delete
        for (String s : listID) {
            if (lst.getID().equals(s)) {
                holder.addcoin.setImageResource(R.drawable.deleteitem);
                holder.addcoin.setColorFilter(ContextCompat.getColor(context, R.color.red));
                holder.addOrDel = false;
                break;
            } else {
                holder.addcoin.setImageResource(R.drawable.additem);
                holder.addcoin.setColorFilter(ContextCompat.getColor(context, R.color.orange));
                holder.addOrDel = true;

            }
        }

        holder.addcoin.setOnClickListener(v -> {
            Gson gson = new Gson();
            CoinsListController coinprefs =  new CoinsListController(lst.getID(),lst.getName(),lst.getSymbol());
            String objString = gson.toJson(coinprefs);
            if (!coinsJsonList.isEmpty()){
                jj = gson.fromJson(coinsJsonList, new TypeToken<List<JsonObject>>(){}.getType());
            }

            switch (String.valueOf(holder.addOrDel)){
                case "false":
                    try {
                        JSONObject jsonObject = new JSONObject(objString);
                        for(int i = 0;i<jj.size();i++){
                            if(String.valueOf(jj.get(i)).equals(String.valueOf(jsonObject))){
                            jj.remove(i);
                        }
                        }
                        updateSharedPrefs();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case "true":
                default:
                    if (canAdd){
                        try {
                            JSONObject jsonObject = new JSONObject(objString);
                            jj.add(jsonObject);
                            updateSharedPrefs();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(context,"Max number of Coins reached",
                                Toast.LENGTH_SHORT).show();
                    }
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
        public boolean addOrDel;

        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            symbol = itemView.findViewById(R.id.code);
            addcoin = itemView.findViewById(R.id.addcoin);
            addcoin.setVisibility(View.VISIBLE);
            addOrDel = false;
        }
    }

    public void updateSharedPrefs(){
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("coins",jj.toString());
        myEdit.apply();
        MainActivity.UpdateView();
        ((coinsListView)context).finish();
    }
}