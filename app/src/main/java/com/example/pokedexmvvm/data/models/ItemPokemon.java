package com.example.pokedexmvvm.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemPokemon implements Serializable {
    private int number;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    public int getNumber() {
        String[] urlPart = url.split("/");
        return Integer.parseInt(urlPart[urlPart.length - 1]);
    }
}
