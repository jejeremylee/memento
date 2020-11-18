package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import static id.ac.ui.cs.mobileprogramming.jeremy.memento.DatabaseInitializer.populateAsync;


public class MainActivity extends AppCompatActivity {

    private ProfilesFragment profilesFragment = new ProfilesFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        sendBroadcast(intent);
        AppDatabase db = AppDatabase.getInstance(getApplication());
        populateAsync(db);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, profilesFragment)
                .addToBackStack("profiles_fragment")
                .commit();
    }




}