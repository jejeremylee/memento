package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.app.AlarmManager;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import android.os.IBinder;

import android.util.Log;

import java.util.Calendar;


public class NotificationService extends Service {

    //List<Profiles> profiles;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d("MyAPP", "service() called");
        scheduleNotification();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("MyAPP",  "Service Destroy");
    }


    private void scheduleNotification () {

        Intent MyIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent MyPendIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager MyAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        MyAlarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_DAY, MyPendIntent);

    }

}
