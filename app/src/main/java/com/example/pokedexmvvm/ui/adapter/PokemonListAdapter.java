package com.example.pokedexmvvm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedexmvvm.R;
import com.example.pokedexmvvm.data.models.ItemPokemon;

import java.text.MessageFormat;

import static com.example.pokedexmvvm.ui.Constants.IMAGE_URL;

public class PokemonListAdapter extends PagedListAdapter<ItemPokemon, PokemonListAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView id;
        ImageView imagePokemon;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.namePokemon);
            id = itemView.findViewById(R.id.num);
            imagePokemon = itemView.findViewById(R.id.image_pokemon);
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
        View view = LayoutInflater.from(context)
                .inflate(R.layout.pokemon_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPokemon item = getItem(position);
        if(item == null) return;

        String name = item.getName();
        int id = item.getNumber();

        String url = item.getUrl();

        holder.nameTextView.setText(name);
        holder.id.setText(MessageFormat.format("# = {0}", id));

        Glide.with(context)
                .load( IMAGE_URL + id + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagePokemon);

        holder.itemView.setOnClickListener(v -> itemClickListener.onClick(position, v));

    }
}

