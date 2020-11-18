package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import java.util.Calendar;


import static id.ac.ui.cs.mobileprogramming.jeremy.memento.DatabaseInitializer.populateAsync;


public class MainActivity extends AppCompatActivity {

    private ProfilesFragment profilesFragment = new ProfilesFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scheduleNotification(10, 1);
        AppDatabase db = AppDatabase.getInstance(getApplication());
        populateAsync(db);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, profilesFragment)
                .addToBackStack("profiles_fragment")
                .commit();
    }


    private void scheduleNotification (int minutes, int requestCode) {

        Calendar alarmFor = Calendar.getInstance();
          alarmFor.set(Calendar.HOUR, 23);
          alarmFor.set(Calendar.MINUTE, minutes);
          alarmFor.set(Calendar.SECOND, 0);

        Intent MyIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent MyPendIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager MyAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        MyAlarm.set(AlarmManager.RTC_WAKEUP, alarmFor.getTimeInMillis(), MyPendIntent);
    }


}