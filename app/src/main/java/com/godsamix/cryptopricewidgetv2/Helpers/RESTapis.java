package com.godsamix.cryptopricewidgetv2.Helpers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RESTapis {

    @GET("coins/list")
    Call<Object> getCoinsList(@Query("code") String Code);


}
