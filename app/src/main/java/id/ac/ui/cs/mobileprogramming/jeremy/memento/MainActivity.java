package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static id.ac.ui.cs.mobileprogramming.jeremy.memento.DatabaseInitializer.populateAsync;


public class MainActivity extends AppCompatActivity {
    private ProfilesFragment profilesFragment = new ProfilesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppDatabase db = AppDatabase.getInstance(getApplication());
        populateAsync(db);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, profilesFragment)
                .addToBackStack("profiles_fragment")
                .commit();
    }

}