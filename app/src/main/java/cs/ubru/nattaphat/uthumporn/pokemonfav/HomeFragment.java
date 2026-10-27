package cs.ubru.nattaphat.uthumporn.pokemonfav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs.ubru.nattaphat.uthumporn.pokemonfav.adapter.PokemonAdapter;
import cs.ubru.nattaphat.uthumporn.pokemonfav.model.Pokemon;

public class HomeFragment extends Fragment {
    private PokemonAdapter adapter;
    private List<Pokemon> pokemons;
    private RecyclerView rcvPokemon;
    private DatabaseReference refPokemon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        matchView(view);
        pokemons = new ArrayList<Pokemon>();

        refPokemon = FirebaseDatabase.getInstance().getReference("pokemon");
        refPokemon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pokemons.clear();
                for (DataSnapshot data: snapshot.getChildren()){
                    Pokemon pokemon = data.getValue(Pokemon.class);
                    pokemons.add(pokemon);
                }
                adapter = new PokemonAdapter(getContext(), pokemons);
                rcvPokemon.setAdapter(adapter);
                rcvPokemon.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    private void matchView(View view) {
        rcvPokemon = view.findViewById(R.id.rcv_pokemon);
    }
}
