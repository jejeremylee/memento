package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;



import static id.ac.ui.cs.mobileprogramming.jeremy.memento.DatabaseInitializer.populateAsync;


public class MainActivity extends AppCompatActivity {

    private ProfilesFragment profilesFragment = new ProfilesFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppDatabase db = AppDatabase.getInstance(getApplication());
        populateAsync(db);

        Intent i = new Intent(getApplicationContext(), NotificationService.class);
        startService(i);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, profilesFragment)
                .addToBackStack("profiles_fragment")
                .commit();
    }




}