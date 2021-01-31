package com.example.pokedexmvvm.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.pokedexmvvm.data.models.ItemPokemon;
import com.example.pokedexmvvm.data.remote.pokemons.PokemonsDataSource;
import com.example.pokedexmvvm.data.remote.pokemons.PokemonsDataSourceFactory;

public class PokemonViewModel extends ViewModel {

    private final LiveData<PagedList<ItemPokemon>> pokemons;
    private final MutableLiveData<PokemonsDataSource> pokemonsDataSourceLiveData;
    private final PokemonsDataSourceFactory pokemonsDataSourceFactory;

    public PokemonViewModel() {
        pokemonsDataSourceFactory = new PokemonsDataSourceFactory();
        pokemonsDataSourceLiveData = pokemonsDataSourceFactory.getCharactersLiveDataSource();

        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PokemonsDataSource.PAGE_SIZE)
                .build();

        pokemons = (new LivePagedListBuilder<>(pokemonsDataSourceFactory, pagedListConfig)).build();
    }

    public LiveData<PagedList<ItemPokemon>> getCharactersList() {
        return pokemons;
    }

    public MutableLiveData<PokemonsDataSource> getPokemonDataSourceLiveData() {
        return pokemonsDataSourceLiveData;
    }

}
