package com.godsamix.cryptopricewidgetv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static SimpleRVAdapter adap;
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout la;
    @SuppressLint("StaticFieldLeak")
    public static TextView footer;
    public  static SharedPreferences sharedPreferences;
    public static String coinsJsonList;
    public static String coinsarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //shared prefs init and get value
        sharedPreferences = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        coinsJsonList = sharedPreferences.getString("coins", "");
        if (coinsJsonList.isEmpty()){
            String btcprefs = "[{\"id\":\"bitcoin\",\"symbol\":\"btc\",\"name\":\"Bitcoin\"}]";
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("coins",btcprefs);
            myEdit.commit();
        }else{

        }
        Log.e("shared pref coins" , coinsJsonList);

        la = findViewById(R.id.linearlay);
        recyclerView = findViewById(R.id.recyclerview);
        footer = (TextView) findViewById(R.id.footer);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adap = new SimpleRVAdapter(new String[] {"1", "2", "3", "4", "5", "6"},new String[] {"11", "22", "33", "44", "55", "66"});
        recyclerView.setAdapter(adap);

        //Grab saved coinsList from shared prefs
        coinsarray = "[{\"id\":\"01coin\",\"symbol\":\"zoc\",\"name\":\"01coin\"}," +
                "{\"id\":\"aelf\",\"symbol\":\"elf\",\"name\":\"elf\"}]";
        Gson gson = new Gson();
       List<JSONObject> jj = gson.fromJson(coinsarray, new TypeToken<List<JsonObject>>(){}.getType());
       CoinsListController coinprefs =  new CoinsListController("aelysir","Aelysir","ael");
       String objString = gson.toJson(coinprefs);
        try {
            JSONObject jsonObject = new JSONObject(objString);
            jj.add(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  Log.e("tst" , jj.toString());

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
        if(adap.getItemCount() <= 8){
            la.setVisibility(View.VISIBLE);
        }else{
            la.setVisibility(View.GONE);
        }
    }
}