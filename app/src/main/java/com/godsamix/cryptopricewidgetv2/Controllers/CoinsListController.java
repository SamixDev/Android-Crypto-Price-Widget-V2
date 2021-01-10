package com.godsamix.cryptopricewidgetv2.Controllers;

import com.google.gson.annotations.SerializedName;

public class CoinsListController {

    @SerializedName("id")
    private  String id;
    @SerializedName("name")
    private  String name;

    @SerializedName("symbol")
    private  String symbol;



    private  String img;

    public CoinsListController(String id, String name, String symbol, String img) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.img = img;
    }

    public CoinsListController() {
    }

    public String getID() {
        return id;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}

