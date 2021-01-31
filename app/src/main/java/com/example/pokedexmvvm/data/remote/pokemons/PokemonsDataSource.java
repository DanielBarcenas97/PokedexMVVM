package com.example.pokedexmvvm.data.remote.pokemons;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.pokedexmvvm.data.models.ItemPokemon;
import com.example.pokedexmvvm.data.models.ResponsePokemon;
import com.example.pokedexmvvm.data.remote.PokemonApi;
import com.example.pokedexmvvm.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonsDataSource extends PageKeyedDataSource<Integer, ItemPokemon> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private static int offsetLoadBefore = 0;
    private static int offsetLoadNext = 0;



    private MutableLiveData<Throwable> initialLoadLiveData = new MutableLiveData<>();
    private String queryName;


    PokemonsDataSource(String queryName) {
        this.queryName = queryName;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ItemPokemon> callback) {
        PokemonApi api = RetrofitClient.getInstance().getApi();
        api.getListPokemon(50,0)
                .enqueue(new Callback<ResponsePokemon>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponsePokemon> call, @NonNull Response<ResponsePokemon> response) {
                        if(!response.isSuccessful()) {
                            initialLoadLiveData.postValue(new Throwable("404"));
                        } else if(response.body() != null) {
                            if(isInvalid()) return;
                            callback.onResult(response.body().getResults(),
                                    null, FIRST_PAGE + 1);
                            initialLoadLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponsePokemon> call, @NonNull Throwable t) {
                        if(isInvalid()) return;
                        initialLoadLiveData.postValue(t);
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ItemPokemon> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ItemPokemon> callback) {
        offsetLoadBefore +=50;
        PokemonApi api = RetrofitClient.getInstance().getApi();
        api.getListPokemon(50,offsetLoadBefore)
                .enqueue(new Callback<ResponsePokemon>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponsePokemon> call, @NonNull Response<ResponsePokemon> response) {
                        if(response.body() != null) {
                            String nextURL = response.body().getNext();

                            if(nextURL != null){
                                Integer nextPage = null;
                                if(!nextURL.isEmpty()) {
                                    // subtracting '0' converts from ascii code to actual digit
                                    nextPage = nextURL.charAt(nextURL.length() - 1) - '0';
                                }

                                callback.onResult(response.body().getResults(), nextPage);
                            }

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponsePokemon> call, @NonNull Throwable t) {

                    }
                });
    }

    public MutableLiveData<Throwable> getInitialLoadLiveData() {
        return initialLoadLiveData;
    }

}
