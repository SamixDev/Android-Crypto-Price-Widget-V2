package com.godsamix.cryptopricewidgetv2.Helpers;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.godsamix.cryptopricewidgetv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.godsamix.cryptopricewidgetv2.MainActivity.sharedPreferences;

public class widgetCoinsAdapter extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new widgetCoinsAdapterFactory(getApplicationContext(), intent);
    }
    class widgetCoinsAdapterFactory implements RemoteViewsFactory {
        private Context context;
        private int appWidgetId;
        private String[] exampleData = {"one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten"};
        public  String coinsJsonList;
        public  JSONArray jsonarray;
        public  String[] listName;
        public  String[] listSymbol;
        public  String[] listID;
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
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.coins_list_widget);
            views.setTextViewText(R.id.name, listName[position]);
            views.setTextViewText(R.id.price, "tst");
           // SystemClock.sleep(500);
            return views;
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