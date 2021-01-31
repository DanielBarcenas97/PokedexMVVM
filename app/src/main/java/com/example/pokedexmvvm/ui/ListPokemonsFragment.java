package com.example.pokedexmvvm.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pokedexmvvm.R;
import com.example.pokedexmvvm.data.models.ItemPokemon;
import com.example.pokedexmvvm.data.remote.pokemons.PokemonsDataSource;
import com.example.pokedexmvvm.databinding.FragmentListPokemonsBinding;
import com.example.pokedexmvvm.ui.adapter.PokemonListAdapter;
import com.example.pokedexmvvm.ui.viewModel.PokemonViewModel;

import java.util.Objects;

public class ListPokemonsFragment extends Fragment {

    private PokemonViewModel viewModel;
    private PokemonListAdapter adapter;
    private TextView errorTextView;
    private RecyclerView recyclerView;
    private NavController navController;

    public ListPokemonsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_pokemons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        navController = Navigation.findNavController(view);
        errorTextView = view.findViewById(R.id.loadErrorTextView);
        recyclerView = view.findViewById(R.id.recyclerView);



        // initialize recyclerview
        adapter = new PokemonListAdapter(getContext());
        adapter.setOnItemClickListener(characterItemClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create ViewModel and set up LiveData Observers
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        viewModel.getCharactersList().observe(getViewLifecycleOwner(), pagedList ->
                adapter.submitList(pagedList)
        );

        viewModel.getPokemonDataSourceLiveData().observe(getViewLifecycleOwner(), pokemonsDataSource ->
                pokemonsDataSource.getInitialLoadLiveData().observe(getViewLifecycleOwner(), throwable -> {
                    // If initial Data loaded successfully
                    if(throwable == null) {
                        onLoadSuccess();
                    } else {
                        onLoadFailure(pokemonsDataSource, Objects.requireNonNull(throwable.getMessage()));
                    }
                })
        );


        onLoading();
    }



    private void onLoading() {
        recyclerView.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(View.GONE);

    }

    private void onLoadSuccess() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void onLoadFailure(PokemonsDataSource charactersDataSource, String error) {
        recyclerView.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(View.VISIBLE);

        if(error.equals(getString(R.string.notFoundErrorCode))) {
            errorTextView.setText(R.string.notFoundErrorText);
        } else {
            errorTextView.setText(R.string.somethingWentWrongErrorText);
        }
    }

    private PokemonListAdapter.OnItemClickListener characterItemClickListener = (position, v) -> {
        if(adapter.getCurrentList() == null || navController == null) return;

        ItemPokemon character = adapter.getCurrentList().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.characterBundleKey), character);

        navController.navigate(R.id.action_charactersListFragment_to_characterDetailsFragment, bundle);
    };

}