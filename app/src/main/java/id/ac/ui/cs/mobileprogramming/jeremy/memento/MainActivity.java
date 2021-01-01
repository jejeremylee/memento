package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;



import java.util.List;
import java.util.concurrent.ExecutionException;

import static id.ac.ui.cs.mobileprogramming.jeremy.memento.DatabaseInitializer.populateAsync;


public class MainActivity extends AppCompatActivity {

    List<Profiles> profileList;

    static {
        System.loadLibrary("check_profile");
    }

    public native String checkProfile(String profilesNumber);

    private ProfilesFragment profilesFragment = new ProfilesFragment();
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppDatabase db = AppDatabase.getInstance(getApplication());
        populateAsync(db);
        checkProfileNumber();


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

    public void checkProfileNumber(){
        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        try {
            profileList = viewModel.getAllProfiles();
        }
        catch(ExecutionException | InterruptedException e){
            profileList = null;
        }

        if(profileList.size()!=0 && profileList != null) {
            String profileNumber = checkProfile(Integer.toString(profileList.size()));
            Toast.makeText(this, profileNumber, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Total profile(s) listed: 0", Toast.LENGTH_LONG).show();
        }
    }


}