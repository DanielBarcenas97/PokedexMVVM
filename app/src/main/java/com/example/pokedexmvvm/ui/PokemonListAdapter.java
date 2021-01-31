package com.example.pokedexmvvm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedexmvvm.data.models.ItemPokemon;
import com.example.pokedexmvvm.databinding.PokemonItemBinding;

import java.text.MessageFormat;

public class PokemonListAdapter extends PagedListAdapter<ItemPokemon, PokemonListAdapter.ViewHolder> {

    public static final String IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final PokemonItemBinding binding;
        private ViewHolder(@NonNull PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private static DiffUtil.ItemCallback<ItemPokemon> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ItemPokemon>() {
                @Override
                public boolean areItemsTheSame(@NonNull ItemPokemon oldItem, @NonNull ItemPokemon newItem) {
                    return oldItem.getUrl().equals(newItem.getUrl());
                }

                @Override
                public boolean areContentsTheSame(@NonNull ItemPokemon oldItem, @NonNull ItemPokemon newItem) {
                    return (
                            oldItem.getName().equals(newItem.getName())
                    );
                }
            };

    public interface OnItemClickListener {
        void onClick(int position, View view);
    }

    private OnItemClickListener itemClickListener;
    private Context context;

    public PokemonListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PokemonItemBinding binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPokemon item = getItem(position);
        if(item == null) return;

        String name = item.getName();
        int id = item.getNumber();

        holder.binding.namePokemon.setText(name);
        holder.binding.num.setText(MessageFormat.format("Nº = {0}", id));

        Glide.with(context)
                .load( IMAGE_URL + id + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.binding.imagePokemon);

        holder.itemView.setOnClickListener(v -> itemClickListener.onClick(position, v));

    }
}
