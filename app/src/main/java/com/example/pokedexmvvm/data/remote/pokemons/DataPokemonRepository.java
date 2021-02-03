package com.example.pokedexmvvm.data.remote.pokemons;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.pokedexmvvm.data.models.Pokemon;
import com.example.pokedexmvvm.data.remote.PokemonApi;
import com.example.pokedexmvvm.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPokemonRepository {

    private static PokemonApi pokemonApi;

    private final MutableLiveData<Pokemon> responseData = new MutableLiveData<>();

    private static DataPokemonRepository dataPokemonRepository;

    public static DataPokemonRepository getInstance(){
        if(dataPokemonRepository == null) {
            dataPokemonRepository = new DataPokemonRepository();
        }
        return dataPokemonRepository;
    }


    public DataPokemonRepository(){
        pokemonApi = RetrofitClient.getInstance().getApi();
    }

    public MutableLiveData<Pokemon> getResponse(String num ){
        Call<Pokemon> responseHangmanCall = pokemonApi.getPokemonById(num);

        responseHangmanCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(@NonNull Call<Pokemon> call, @NonNull Response<Pokemon> response) {
                responseData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Pokemon> call, @NonNull Throwable t) {
                responseData.postValue(null);
            }
        });

        return responseData;
    }

}
