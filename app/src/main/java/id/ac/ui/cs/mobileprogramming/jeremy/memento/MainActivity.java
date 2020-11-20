package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import static id.ac.ui.cs.mobileprogramming.jeremy.memento.DatabaseInitializer.populateAsync;


public class MainActivity extends AppCompatActivity {

    private ProfilesFragment profilesFragment = new ProfilesFragment();
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppDatabase db = AppDatabase.getInstance(getApplication());
        populateAsync(db);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, profilesFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Intent i = new Intent(getApplicationContext(), NotificationService.class);
        startService(i);
    }


}