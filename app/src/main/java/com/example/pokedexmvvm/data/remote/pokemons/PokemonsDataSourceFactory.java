package com.example.pokedexmvvm.data.remote.pokemons;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.pokedexmvvm.data.models.ItemPokemon;


public class PokemonsDataSourceFactory extends DataSource.Factory<Integer, ItemPokemon>{

    private MutableLiveData<PokemonsDataSource>
            charactersLiveDataSource = new MutableLiveData<>();

    private String queryName;


    @Override
    public PokemonsDataSource create() {
        PokemonsDataSource charactersDataSource = new PokemonsDataSource(queryName);
        charactersLiveDataSource.postValue(charactersDataSource);
        return charactersDataSource;
    }

    public MutableLiveData<PokemonsDataSource> getCharactersLiveDataSource() {
        return charactersLiveDataSource;
    }

    public void searchCharacter(String name) {
        queryName = name;
    }
}
