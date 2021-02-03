package com.example.pokedexmvvm.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedexmvvm.R;
import com.example.pokedexmvvm.data.models.ItemPokemon;
import com.example.pokedexmvvm.data.models.Pokemon;
import com.example.pokedexmvvm.data.models.TypesItem;
import com.example.pokedexmvvm.databinding.FragmentDetailsPokemonBinding;
import com.example.pokedexmvvm.ui.viewModel.DetailPokemonViewModel;

import java.util.List;

import static com.example.pokedexmvvm.ui.Constants.IMAGE_URL;

public class DetailsPokemonFragment extends Fragment {


    private DetailPokemonViewModel dataViewModel;
    private FragmentDetailsPokemonBinding binding;
    private  ItemPokemon item;
    public DetailsPokemonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDetailsPokemonBinding.inflate(inflater, container, false);
        Bundle args = getArguments();
        if(args != null) {
            item = (ItemPokemon) args.getSerializable(getString(R.string.characterBundleKey));
            String URL = item.getUrl();
        }
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.characterNameTextView.setText(item.getName());
        binding.progress.getIndeterminateDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.MULTIPLY);
        Glide.with(requireContext())
                .load( IMAGE_URL + item.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.characterImageView);

        dataViewModel = new ViewModelProvider(this).get(DetailPokemonViewModel.class);
        final Observer<Pokemon> observer = new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon responseDetail) {
                binding.progress.setVisibility(View.INVISIBLE);
                String base = String.valueOf(responseDetail.getBaseExperience());
                String height = String.valueOf(responseDetail.getHeight());
                String weight = String.valueOf(responseDetail.getWeight());

                binding.baseExperience.setText(base);
                binding.height.setText(height);
                binding.weight.setText(weight);
                List<TypesItem> types = responseDetail.getTypes();

                try {
                    int tam = types.size();
                    String type1 = types.get(0).getType().getName();
                    setImage1(type1);

                    if(tam == 2){
                        String type2 = types.get(1).getType().getName();
                        setImage2(type2);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        };


        dataViewModel.getHangmanRepository(String.valueOf(item.getNumber())).observe(getViewLifecycleOwner(),observer);

        //ImageView imageView = view.findViewById(R.id.characterImageView);
        //Glide.with(this).load(binding.getCharacter().getImage()).into(imageView);
    }

    private void setImage1(String type1) {

        switch (type1){
            case "water":
                binding.type1.setImageResource(R.drawable.water);
                break;
            case "bug":
                binding.type1.setImageResource(R.drawable.bug);
                break;
            case "dark":
                binding.type1.setImageResource(R.drawable.dark);
                break;
            case "dragon":
                binding.type1.setImageResource(R.drawable.dragon);
                break;
            case "electric":
                binding.type1.setImageResource(R.drawable.electric);
                break;
            case "fairy":
                binding.type1.setImageResource(R.drawable.fairy);
                break;
            case "fighting":
                binding.type1.setImageResource(R.drawable.fighting);
                break;
            case "fire":
                binding.type1.setImageResource(R.drawable.fire);
                break;
            case "flying":
                binding.type1.setImageResource(R.drawable.flying);
                break;
            case "ghost":
                binding.type1.setImageResource(R.drawable.ghost);
                break;
            case "grass":
                binding.type1.setImageResource(R.drawable.grass);
                break;
            case "ground":
                binding.type1.setImageResource(R.drawable.ground);
                break;
            case "ice":
                binding.type1.setImageResource(R.drawable.ice);
                break;
            case "poison":
                binding.type1.setImageResource(R.drawable.poison);
                break;
            case "psychic":
                binding.type1.setImageResource(R.drawable.psychic);
                break;
            case "rock":
                binding.type1.setImageResource(R.drawable.rock);
                break;
            case "steel":
                binding.type1.setImageResource(R.drawable.steel);
                break;
            default:
                binding.type1.setImageResource(R.drawable.normal);
                break;
        }
    }

    private void setImage2(String type2) {
        binding.type2.setVisibility(View.VISIBLE);
        switch (type2){
            case "water":
                binding.type2.setImageResource(R.drawable.water);
                break;
            case "bug":
                binding.type2.setImageResource(R.drawable.bug);
                break;
            case "dark":
                binding.type2.setImageResource(R.drawable.dark);
                break;
            case "dragon":
                binding.type2.setImageResource(R.drawable.dragon);
                break;
            case "electric":
                binding.type2.setImageResource(R.drawable.electric);
                break;
            case "fairy":
                binding.type2.setImageResource(R.drawable.fairy);
                break;
            case "fighting":
                binding.type2.setImageResource(R.drawable.fighting);
                break;
            case "fire":
                binding.type2.setImageResource(R.drawable.fire);
                break;
            case "flying":
                binding.type2.setImageResource(R.drawable.flying);
                break;
            case "ghost":
                binding.type2.setImageResource(R.drawable.ghost);
                break;
            case "grass":
                binding.type2.setImageResource(R.drawable.grass);
                break;
            case "ground":
                binding.type2.setImageResource(R.drawable.ground);
                break;
            case "ice":
                binding.type2.setImageResource(R.drawable.ice);
                break;
            case "poison":
                binding.type2.setImageResource(R.drawable.poison);
                break;
            case "psychic":
                binding.type2.setImageResource(R.drawable.psychic);
                break;
            case "rock":
                binding.type2.setImageResource(R.drawable.rock);
                break;
            case "steel":
                binding.type2.setImageResource(R.drawable.steel);
                break;
            default:
                binding.type2.setImageResource(R.drawable.normal);
                break;
        }
    }



}