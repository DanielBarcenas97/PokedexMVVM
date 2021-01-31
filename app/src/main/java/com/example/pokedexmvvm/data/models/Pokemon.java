package com.example.pokedexmvvm.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {

    @SerializedName("location_area_encounters")
    private String locationAreaEncounters;

    @SerializedName("base_experience")
    private int baseExperience;

    @SerializedName("held_items")
    private List<Object> heldItems;

    @SerializedName("weight")
    private int weight;

    @SerializedName("is_default")
    private boolean isDefault;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("height")
    private int height;

    @SerializedName("order")
    private int order;

    public String getLocationAreaEncounters(){
        return locationAreaEncounters;
    }

    public int getBaseExperience(){
        return baseExperience;
    }

    public List<Object> getHeldItems(){
        return heldItems;
    }

    public int getWeight(){
        return weight;
    }

    public boolean isIsDefault(){
        return isDefault;
    }


    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public int getHeight(){
        return height;
    }

    public int getOrder(){
        return order;
    }

}
