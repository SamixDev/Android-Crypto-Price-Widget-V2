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
        for (String s : listID) {
            if (lst.getID().equals(s)) {
                Log.e("id", s);
                holder.addcoin.setImageResource(R.drawable.deleteitem);
                holder.addcoin.setColorFilter(ContextCompat.getColor(context, R.color.red));
            } else {
                holder.addcoin.setImageResource(R.drawable.additem);
                holder.addcoin.setColorFilter(ContextCompat.getColor(context, R.color.orange));
            }
        }

        holder.addcoin.setOnClickListener(v -> {
            Log.e("btn" , lst.getID());
            Log.e("btn" , "btn works "+ lst.getID() );
            Gson gson = new Gson();
            CoinsListController coinprefs =  new CoinsListController(lst.getID(),lst.getName(),lst.getSymbol());
            String objString = gson.toJson(coinprefs);
      //      Log.e("js" , objString);
            if (!coinsJsonList.isEmpty()){
                Log.e("not empty" , coinsJsonList);
                jj = gson.fromJson(coinsJsonList, new TypeToken<List<JsonObject>>(){}.getType());
            }
            try {
                JSONObject jsonObject = new JSONObject(objString);
                jj.add(jsonObject);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("coins",jj.toString());
                myEdit.apply();
                   Log.e("shared" , sharedPreferences.getString("coins", ""));
                MainActivity.UpdateView();
                ((coinsListView)context).finish();

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

    public void checkIfExist(){

    }
}