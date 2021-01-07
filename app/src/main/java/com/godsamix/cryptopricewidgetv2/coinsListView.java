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

import com.godsamix.cryptopricewidgetv2.Controllers.CoinsListAdapter;
import com.godsamix.cryptopricewidgetv2.Controllers.CoinsListController;
import com.godsamix.cryptopricewidgetv2.Helpers.RESTapis;
import com.godsamix.cryptopricewidgetv2.Helpers.RetrofitService;

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
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins_list_view);
        spinner = findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        hardAdapter = new CoinsListAdapter(this, viewlist);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(hardAdapter);


        getCoinsList();

    }
    private void getCoinsList(){
        RESTapis RESTapis = RetrofitService.createService(RESTapis.class);
        Call<List<CoinsListController>> call;
        call = RESTapis.getCoinsList();
        call.enqueue(new Callback<List<CoinsListController>>() {
            @Override
            public void onResponse(@NonNull Call<List<CoinsListController>> call, @NonNull Response<List<CoinsListController>> response) {
                if(response.isSuccessful()) {
 //                   Log.e("test ","test ");
//                    Log.e("tst ",response.body().toString());
                    for(CoinsListController procc: response.body()){
                        viewlist.add(procc);
                        spinner.setVisibility(View.GONE);
                    //    Log.e("tst ",procc.getName());
                    }
                    hardAdapter.notifyDataSetChanged();
                }else{
                     Log.e("failed ", "response.message()");
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<CoinsListController>> call, @NonNull Throwable t) {
                //   Log.e(TAG, t.getMessage());
                Log.e("failed ", t.getMessage());
            }
        });
    }
}