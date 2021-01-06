package com.godsamix.cryptopricewidgetv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.godsamix.cryptopricewidgetv2.Helpers.SimpleRVAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static SimpleRVAdapter adap;
    public static LinearLayout la;
    public static TextView footer;
    public  static SharedPreferences sharedPreferences;
    public static String coinsJsonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //shared prefs init and get value
        sharedPreferences = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        coinsJsonList = sharedPreferences.getString("coins", "");

        la = findViewById(R.id.linearlay);
        recyclerView = findViewById(R.id.recyclerview);
        footer = (TextView) findViewById(R.id.footer);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adap = new SimpleRVAdapter(new String[] {"1", "2", "3", "4", "5", "6"},new String[] {"11", "22", "33", "44", "55", "66"});
        recyclerView.setAdapter(adap);

        //footer
        footer.setText(
                Html.fromHtml(
                        "Made by " +
                                "<a href='https://github.com/SamixDev'>SamixDev</a>" +
                                " for crypto lovers &#128578;"));
        footer.setMovementMethod(LinkMovementMethod.getInstance());

        la.setOnClickListener(v -> {

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