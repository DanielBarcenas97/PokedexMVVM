package com.example.pokedexmvvm.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokedexmvvm.data.models.Pokemon;
import com.example.pokedexmvvm.data.remote.pokemons.DataPokemonRepository;

public class DetailPokemonViewModel extends ViewModel {

    private MutableLiveData<Pokemon> mutableLiveData;
    private DataPokemonRepository dataGameRepository;

    public DetailPokemonViewModel() {
        mutableLiveData = new MutableLiveData<>();
        dataGameRepository = DataPokemonRepository.getInstance();
    }

    public LiveData<Pokemon> getHangmanRepository(String num){
        mutableLiveData = dataGameRepository.getResponse(num);
        return mutableLiveData;
    }

}

