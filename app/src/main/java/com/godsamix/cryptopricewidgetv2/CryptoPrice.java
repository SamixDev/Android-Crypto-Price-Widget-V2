package com.godsamix.cryptopricewidgetv2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import static com.godsamix.cryptopricewidgetv2.MainActivity.sharedPreferences;

/**
 * Implementation of App Widget functionality.
 */
public class CryptoPrice extends AppWidgetProvider {
    public static String coinsJsonList;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.crypto_price);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        coinsJsonList = sharedPreferences.getString("coins", "");
        if (coinsJsonList.isEmpty()){
            String btcprefs = "[{\"id\":\"bitcoin\",\"symbol\":\"btc\",\"name\":\"Bitcoin\",\"img\":\"\"}]";
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("coins",btcprefs);
            myEdit.apply();
        }





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