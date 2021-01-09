package com.godsamix.cryptopricewidgetv2.Controllers;

import com.google.gson.annotations.SerializedName;

public class CoinsListController {

    @SerializedName("id")
    private  String id;
    @SerializedName("name")
    private  String name;
    @SerializedName("symbol")
    private  String symbol;

    public CoinsListController(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public CoinsListController() {
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}

