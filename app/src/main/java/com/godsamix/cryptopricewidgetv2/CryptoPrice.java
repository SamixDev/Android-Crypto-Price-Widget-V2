package com.godsamix.cryptopricewidgetv2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.godsamix.cryptopricewidgetv2.Helpers.SimpleRVAdapter;
import com.godsamix.cryptopricewidgetv2.Helpers.widgetCoinsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.godsamix.cryptopricewidgetv2.MainActivity.recyclerView;
import static com.godsamix.cryptopricewidgetv2.MainActivity.sharedPreferences;

/**
 * Implementation of App Widget functionality.
 */
public class CryptoPrice extends AppWidgetProvider {
    public static String coinsJsonList;
    public static JSONArray jsonarray;
    public static String[] listName;
    public static String[] listSymbol;
    public static String[] listID;
    public static String[] listPrice;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent serviceIntent = new Intent(context, widgetCoinsAdapter.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.crypto_price);
        views.setRemoteAdapter(R.id.list, serviceIntent);



//        coinsJsonList = sharedPreferences.getString("coins", "");
//        if (coinsJsonList.isEmpty()){
//            String btcprefs = "[{\"id\":\"bitcoin\",\"symbol\":\"btc\",\"name\":\"Bitcoin\",\"img\":\"\"}]";
//            SharedPreferences.Editor myEdit = sharedPreferences.edit();
//            myEdit.putString("coins",btcprefs);
//            myEdit.apply();
//        }
//        try {
//            jsonarray = new JSONArray(coinsJsonList);
//            int arrLength = jsonarray.length();
//            listName = new String[arrLength];
//            listSymbol = new String[arrLength];
//            listID = new String[arrLength];
//            // listImage = new String[arrLength];
//            for (int i = 0; i < arrLength; i++) {
//                JSONObject jsonobject = jsonarray.getJSONObject(i);
//                String name = jsonobject.getString("name");
//                String id = jsonobject.getString("id");
//                String symbol = jsonobject.getString("symbol");
//                listName[i] = name;
//                listSymbol[i] = symbol;
//                listID[i] = id;
//                if(jsonobject.getString("img").isEmpty()){
//
//                }
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}