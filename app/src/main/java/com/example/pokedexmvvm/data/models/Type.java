package com.example.pokedexmvvm.data.models;

import com.google.gson.annotations.SerializedName;

public class Type{

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
}