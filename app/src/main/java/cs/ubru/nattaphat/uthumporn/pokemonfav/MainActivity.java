package cs.ubru.nattaphat.uthumporn.pokemonfav;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        matchView();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_pokemon, new HomeFragment()).commit();

        bnvMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                if(menuItem.getItemId() == R.id.mn_home){
                    fragment = new HomeFragment();
                }else if(menuItem.getItemId() == R.id.mn_add){
                    fragment = new NewFragment();
                }else if(menuItem.getItemId() == R.id.mn_serch){
                    fragment = new SearchFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_pokemon, fragment).commit();
                return true;
            }
        });
    }

    private void matchView() {
        bnvMenu = findViewById(R.id.bnv_menu);
    }
}