package com.godsamix.cryptopricewidgetv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.godsamix.cryptopricewidgetv2.Controllers.CoinsListController;
import com.godsamix.cryptopricewidgetv2.Helpers.SimpleRVAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static SimpleRVAdapter adap;
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout la;
    @SuppressLint("StaticFieldLeak")
    public static TextView footer;
    public  static SharedPreferences sharedPreferences;
    public static String coinsJsonList;
    public static String[] listName;
    public static String[] listSymbol;
    public static String[] listID;
    public static JSONArray jsonarray;
    public static Boolean canAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //shared prefs init
        sharedPreferences = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        coinsJsonList = sharedPreferences.getString("coins", "");
        if (coinsJsonList.isEmpty()){
            String btcprefs = "[{\"id\":\"bitcoin\",\"symbol\":\"btc\",\"name\":\"Bitcoin\"}]";
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("coins",btcprefs);
            myEdit.apply();
        }

        la = findViewById(R.id.linearlay);
        recyclerView = findViewById(R.id.recyclerview);
        footer = (TextView) findViewById(R.id.footer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //footer
        footer.setText(
                Html.fromHtml(
                        "Made by " +
                                "<a href='https://github.com/SamixDev'>SamixDev</a>" +
                                " for crypto lovers &#128578;"));
        footer.setMovementMethod(LinkMovementMethod.getInstance());

        la.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(),coinsListView.class);
            startActivity(i);
        });

        UpdateView();
    }

    public static void UpdateView(){
        coinsJsonList = sharedPreferences.getString("coins", "");
        if (coinsJsonList.isEmpty()){
            String btcprefs = "[{\"id\":\"bitcoin\",\"symbol\":\"btc\",\"name\":\"Bitcoin\"}]";
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("coins",btcprefs);
            myEdit.apply();
        }
        Log.e("shared pref coins" , coinsJsonList);
        try {
            jsonarray = new JSONArray(coinsJsonList);
            int arrLength = jsonarray.length();
            listName = new String[arrLength];
            listSymbol = new String[arrLength];
            listID = new String[arrLength];
            for (int i = 0; i < arrLength; i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                String id = jsonobject.getString("id");
                String symbol = jsonobject.getString("symbol");
                listName[i] = name;
                listSymbol[i] = symbol;
                listID[i] = id;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adap = new SimpleRVAdapter(listID,listName,listSymbol);
        recyclerView.setAdapter(adap);
        if(adap.getItemCount() < 8){
            canAdd = true;
        }else{
            canAdd = false;
        }
    }
}