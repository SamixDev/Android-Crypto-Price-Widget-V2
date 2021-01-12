package com.godsamix.cryptopricewidgetv2.Helpers;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.godsamix.cryptopricewidgetv2.R;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Url;

import static com.godsamix.cryptopricewidgetv2.MainActivity.sharedPreferences;

public class widgetCoinsAdapter extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new widgetCoinsAdapterFactory(getApplicationContext(), intent);
    }
    static class widgetCoinsAdapterFactory implements RemoteViewsFactory {
        private final Context context;
        private final int appWidgetId;
        public  String coinsJsonList;
        public  JSONArray jsonarray;
        public  String[] listName;
        public  String[] listSymbol;
        public  String[] listID;
        public  String[] listPrice;
        widgetCoinsAdapterFactory(Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        @Override
        public void onCreate() {
                    coinsJsonList = sharedPreferences.getString("coins", "");
        if (coinsJsonList.isEmpty()){
            String btcprefs = "[{\"id\":\"bitcoin\",\"symbol\":\"btc\",\"name\":\"Bitcoin\",\"img\":\"\"}]";
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("coins",btcprefs);
            myEdit.apply();
        }
        try {
            jsonarray = new JSONArray(coinsJsonList);
            int arrLength = jsonarray.length();
            listName = new String[arrLength];
            listSymbol = new String[arrLength];
            listID = new String[arrLength];
            // listImage = new String[arrLength];
            for (int i = 0; i < arrLength; i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                String id = jsonobject.getString("id");
                String symbol = jsonobject.getString("symbol");
                listName[i] = name;
                listSymbol[i] = symbol;
                listID[i] = id;
                if(jsonobject.getString("img").isEmpty()){

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


//            String coingekousd = "https://api.coingecko.com/api/v3/simple/price?ids=0x%2Czcoin%2Cicon%2Cbitcoin%2Cethereum&vs_currencies=usd";
//
//            RequestQueue queue = Volley.newRequestQueue(context);
//            JsonObjectRequest jsObjRequest = new JsonObjectRequest(
//                    Request.Method.GET, coingekousd, null,
//                    new Response.Listener<JSONObject>() {
//
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                Log.w("WidgetExample",
//                                        "Response => " + response.toString());
//                               // views1.setTextViewText(R.id.appwidget_text,response.getJSONObject("bitcoin").getString("usd") + " U$D");
//
//
//                            } catch (JSONException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }, error -> {
//                // TODO Auto-generated method stub
//            });
//            queue.add(jsObjRequest);


          //  SystemClock.sleep(3000);
        }
        @Override
        public void onDataSetChanged() {
        }
        @Override
        public void onDestroy() {
            //close data source
        }
        @Override
        public int getCount() {
            return listName.length;
        }
        @Override
        public RemoteViews getViewAt(int position) {
           final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.coins_list_widget);
            remoteView.setTextViewText(R.id.name, listName[position]);
            remoteView.setTextViewText(R.id.price, "tst");





//            RESTapis RESTapis = RetrofitService.createService(RESTapis.class);
//            Call<JsonObject> call;
//            call = RESTapis.getCoinData(listID[position], "false", "false", "false", "false", "false" );
//            call.enqueue(new Callback<JsonObject>() {
//                @Override
//                public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
//                    if(response.isSuccessful()) {
//                        try {
//                            JSONObject jo = new JSONObject(response.body().get("image").toString());
//
//                            Picasso mPicasso = Picasso.get();
////                              mPicasso.setIndicatorsEnabled(true);
////                            mPicasso.load(jo.get("small").toString()).into(remoteView ,R.id.imgcoin, new int[]{appWidgetId});
//
////                             Bitmap b =   mPicasso.load(jo.get("small").toString()).get();
////                            remoteView.setImageViewBitmap(R.id.imgcoin, b);
//                            remoteView.setImageViewResource(R.id.imgcoin,R.drawable.additem);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }else{
//                    }
//                }
//                @Override
//                public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
//                    Log.e("failed ", t.getMessage());
//                }
//            });

            return remoteView;
        }
        @Override
        public RemoteViews getLoadingView() {
            return null;
        }
        @Override
        public int getViewTypeCount() {
            return 1;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}