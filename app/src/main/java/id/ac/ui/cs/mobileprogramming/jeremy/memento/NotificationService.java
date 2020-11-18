package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class NotificationService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.d("MyAPP", "service() called");
        scheduleNotification(10, 1,1);

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroy", Toast.LENGTH_LONG).show();
    }

    private void scheduleNotification (int day,int month, int requestCode) {

        Calendar alarmFor = Calendar.getInstance();
        alarmFor.set(Calendar.DATE, day);
        alarmFor.set(Calendar.MONTH,month-1);

        Intent MyIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent MyPendIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager MyAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        //alarmFor.getTimeInMillis()
        MyAlarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 8000, MyPendIntent);
    }

}
