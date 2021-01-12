package com.godsamix.cryptopricewidgetv2.Helpers;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godsamix.cryptopricewidgetv2.R;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.ViewHolder> {
    private String[] id;
    private String[] name;
    private String[] symbol;

    public SimpleRVAdapter(String[] idArgs, String[] nameArgs, String[] symbolArgs){
        id = idArgs;
        name = nameArgs;
        symbol = symbolArgs;
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



        RESTapis RESTapis = RetrofitService.createService(RESTapis.class);
        Call<JsonObject> call;
        call = RESTapis.getCoinData(id[position], "false", "false", "false", "false", "false" );
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if(response.isSuccessful()) {
                    try {
                        JSONObject jo = new JSONObject(response.body().get("image").toString());
                        Picasso mPicasso = Picasso.get();
                      //  mPicasso.setIndicatorsEnabled(true);
                        mPicasso.load(jo.get("small").toString()).into(holder.imgview);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                }
            }
            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Log.e("failed ", t.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.length;
    }
}
