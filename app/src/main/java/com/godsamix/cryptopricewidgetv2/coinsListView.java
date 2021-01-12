package com.godsamix.cryptopricewidgetv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.godsamix.cryptopricewidgetv2.Controllers.CoinsListAdapter;
import com.godsamix.cryptopricewidgetv2.Controllers.CoinsListController;
import com.godsamix.cryptopricewidgetv2.Helpers.RESTapis;
import com.godsamix.cryptopricewidgetv2.Helpers.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class coinsListView extends AppCompatActivity {

    private final List<CoinsListController> viewlist = new ArrayList<>();
    private CoinsListAdapter hardAdapter;
    private RecyclerView recyclerView;
    private ProgressBar spinner;
    private TextInputEditText searchTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_list_view);
        spinner = findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        hardAdapter = new CoinsListAdapter(this, viewlist);
        recyclerView = findViewById(R.id.recyclerview);

        ImageView backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(v -> {
            finish();
        });

        ImageView searchBtn = findViewById(R.id.searchbutton);
        searchTxt = findViewById(R.id.searchinput);
        searchBtn.setOnClickListener(v -> {
            spinner.setVisibility(View.VISIBLE);
            viewlist.clear();
            getCoinsList(searchTxt.getText().toString());
        });
        getCoinsList("");
    }
    private void getCoinsList(String search){
        RESTapis RESTapis = RetrofitService.createService(RESTapis.class);
        Call<List<CoinsListController>> call;
        call = RESTapis.getCoinsList();
        call.enqueue(new Callback<List<CoinsListController>>() {
            @Override
            public void onResponse(@NonNull Call<List<CoinsListController>> call, @NonNull Response<List<CoinsListController>> response) {
                if(response.isSuccessful()) {
                    for(CoinsListController procc: response.body()){
                        if (search.isEmpty()){
                            viewlist.add(procc);
                        }else{
                            if(procc.getName().toLowerCase().equals(search.toLowerCase())){
                                viewlist.add(procc);
                            }
                        }
                    }
                }
                spinner.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(hardAdapter);
                hardAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(@NonNull Call<List<CoinsListController>> call, @NonNull Throwable t) {
                Log.e("failed ", t.getMessage());
            }
        });
    }
}