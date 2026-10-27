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

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs.ubru.nattaphat.uthumporn.pokemonfav.adapter.PokemonAdapter;
import cs.ubru.nattaphat.uthumporn.pokemonfav.model.Pokemon;

public class SearchFragment extends Fragment {
    private TextInputLayout tiplSearch;
    private List<Pokemon> pokemons;
    private PokemonAdapter adapter;
    private RecyclerView rcvSearch;
    private DatabaseReference refPokemon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        matchView(view);
        refPokemon = FirebaseDatabase.getInstance().getReference("pokemon");
        pokemons = new ArrayList<Pokemon>();

        tiplSearch.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tiplSearch.getEditText().getText().toString();
                Query query = refPokemon.orderByChild("name").startAt(name).endAt(name + "~");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pokemons.clear();
                        for(DataSnapshot data: snapshot.getChildren()){
                            Pokemon pokemon = data.getValue(Pokemon.class);
                            pokemons.add(pokemon);
                        }
                        adapter = new PokemonAdapter(getContext(), pokemons);
                        rcvSearch.setAdapter(adapter);
                        rcvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                tiplSearch.getEditText().setText("");
            }
        });
        return view;
    }

    private void matchView(View view) {
        rcvSearch = view.findViewById(R.id.rcv_search);
        tiplSearch = view.findViewById(R.id.tipl_search);
    }
}
