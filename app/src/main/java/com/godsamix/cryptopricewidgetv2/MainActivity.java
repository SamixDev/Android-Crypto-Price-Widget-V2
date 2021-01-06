package com.godsamix.cryptopricewidgetv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.godsamix.cryptopricewidgetv2.Helpers.SimpleRVAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SimpleRVAdapter(new String[] {"1", "2", "3", "4", "5", "6"},new String[] {"11", "22", "33", "44", "55", "66"}));

        LinearLayout la = findViewById(R.id.linearlay);
        la.setVisibility(View.GONE);
    }

    public static void UpdateView(){
        
    }
}