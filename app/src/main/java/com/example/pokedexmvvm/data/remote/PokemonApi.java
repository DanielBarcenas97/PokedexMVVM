package com.example.pokedexmvvm.data.remote;

import com.example.pokedexmvvm.data.models.Pokemon;
import com.example.pokedexmvvm.data.models.ResponsePokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApi {

    @GET("pokemon")
    Call<ResponsePokemon> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") String id);
}

