package com.godsamix.cryptopricewidgetv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.godsamix.cryptopricewidgetv2.Helpers.SimpleRVAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static SimpleRVAdapter adap;
    public static LinearLayout la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        la = findViewById(R.id.linearlay);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adap = new SimpleRVAdapter(new String[] {"1", "2", "3", "4", "5", "6"},new String[] {"11", "22", "33", "44", "55", "66"});
        recyclerView.setAdapter(adap);

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