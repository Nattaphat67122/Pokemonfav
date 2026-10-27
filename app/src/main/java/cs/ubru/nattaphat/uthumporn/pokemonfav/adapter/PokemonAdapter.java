package cs.ubru.nattaphat.uthumporn.pokemonfav.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cs.ubru.nattaphat.uthumporn.pokemonfav.R;
import cs.ubru.nattaphat.uthumporn.pokemonfav.model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private Context context;
    private List<Pokemon> pokemon;

    public PokemonAdapter(Context context, List<Pokemon> pokemon) {
        this.context = context;
        this.pokemon = pokemon;
    }

    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        holder.tvName.setText(pokemon.get(position).getName());
        holder.tvHeight.setText(pokemon.get(position).getHeight());
        holder.tvWeight.setText(pokemon.get(position).getWeight());
        holder.tvDescription.setText(pokemon.get(position).getDescription());

        String imgUrl = pokemon.get(position).getImgUrl();
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.imgvPokemon);

    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvHeight, tvWeight, tvDescription;
        ImageView imgvPokemon;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvHeight = itemView.findViewById(R.id.tv_height);
            tvWeight = itemView.findViewById(R.id.tv_weight);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgvPokemon = itemView.findViewById(R.id.imgv_pokemon);
        }
    }
}
