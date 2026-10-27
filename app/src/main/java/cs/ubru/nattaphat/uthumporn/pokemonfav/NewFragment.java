package cs.ubru.nattaphat.uthumporn.pokemonfav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cs.ubru.nattaphat.uthumporn.pokemonfav.model.Pokemon;

public class NewFragment extends Fragment {
    private TextInputLayout tiplName, tiplHeight, tiplWeight, tiplDescription, tiplImgUrl;
    private MaterialButton btnAdd;
    private DatabaseReference refPokemon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        matchView(view);

        refPokemon = FirebaseDatabase.getInstance().getReference("pokemon");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String name = tiplName.getEditText().getText().toString();
                String height = tiplHeight.getEditText().getText().toString();
                String weight = tiplWeight.getEditText().getText().toString();
                String description = tiplDescription.getEditText().getText().toString();
                String imgurl = tiplImgUrl.getEditText().getText().toString();

                if(name.isEmpty() || height.isEmpty() || weight.isEmpty() || description.isEmpty() || imgurl.isEmpty()){
                    Toast.makeText(getContext(), "please full fill data!", Toast.LENGTH_SHORT).show();
                }else{
                    String id = refPokemon.push().getKey();
                    Pokemon pokemon = new Pokemon(id, name, height, weight, description, imgurl);
                    refPokemon.child(id).setValue(pokemon);

                    Toast.makeText(getContext(), "add new pokemon successfull!", Toast.LENGTH_SHORT).show();
                    clearText();

                }
            }
        });

        return view;
    }

    private void clearText() {
        tiplHeight.getEditText().setText("");
        tiplWeight.getEditText().setText("");
        tiplDescription.getEditText().setText("");
        tiplImgUrl.getEditText().setText("");
        tiplName.getEditText().setText("");
        tiplName.isFocused();
    }

    private void matchView(View view) {
        btnAdd = view.findViewById(R.id.btn_add);
        tiplName = view.findViewById(R.id.tipl_name);
        tiplHeight = view.findViewById(R.id.tipl_hight);
        tiplWeight = view.findViewById(R.id.tipl_weight);
        tiplDescription = view.findViewById(R.id.tipl_description);
        tiplImgUrl = view.findViewById(R.id.tipl_img_url);
    }
}
