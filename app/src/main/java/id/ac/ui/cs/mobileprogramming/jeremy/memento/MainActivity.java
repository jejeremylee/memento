package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ProfilesFragment profilesFragment = new ProfilesFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, profilesFragment)
                .addToBackStack("profiles_fragment")
                .commit();
    }
}