package com.example.pokedexmvvm.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePokemon {

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("count")
    private int count;

    @SerializedName("results")
    private List<ItemPokemon> results;

    public String getNext(){
        return next;
    }

    public String getPrevious(){
        return previous;
    }

    public int getCount(){
        return count;
    }

    public List<ItemPokemon> getResults(){
        return results;
    }

}
