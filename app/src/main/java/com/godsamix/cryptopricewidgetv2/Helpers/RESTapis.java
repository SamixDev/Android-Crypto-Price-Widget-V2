package com.godsamix.cryptopricewidgetv2.Helpers;

import com.godsamix.cryptopricewidgetv2.Controllers.CoinsListController;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RESTapis {
    // Get Coins List
    @GET("coins/list")
    Call<List<CoinsListController>> getCoinsList();

    // Get Coins price
    // ids is id of coins, comma-separated if querying more than 1 coin
    // include_24hr_change set to true to get 24hr change percentage
    @GET("simple/price")
    Call<Object> getCoinsPrice(@Query("ids") String ids,
                               @Query("vs_currencies") String vs_currencies,
                               @Query("include_24hr_change") String include_24hr_change);

    // Get a Coin data
    @GET("coins/{id}")
    Call<JsonObject> getCoinData(@Path("id") String id,
                                 @Query("tickers") String tickers,
                                 @Query("community_data") String community_data,
                                 @Query("market_data") String market_data,
                                 @Query("developer_data") String developer_data,
                                 @Query("sparkline") String sparkline);

}
