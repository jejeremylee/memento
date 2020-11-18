package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


public class NotificationReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyAPP", "onReceive() called");
        Intent i = new Intent(context.getApplicationContext(), NotificationService.class);
        context.getApplicationContext().startService(i);
        MyNotification(context);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void MyNotification(Context context) {

        String notificationTitle = "Happy Birthday";

        String notificationText = "Its " + "birthday, " + "Don't forget to buy some present!";

        NotificationManager MyNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent MyIntent = new Intent(context, MainActivity.class);
        MyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent MyPendingIntent = PendingIntent.getActivity(context, 100, MyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder( context, default_notification_channel_id ) ;
        builder.setContentTitle(notificationTitle) ;
        builder.setContentText(notificationText) ;
        builder.setSmallIcon(R.drawable.template ) ;
        builder.setAutoCancel(true);
        builder.setChannelId( NOTIFICATION_CHANNEL_ID );
        builder.setContentIntent(MyPendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Birthday channel",
                    NotificationManager.IMPORTANCE_HIGH);
            MyNotifyManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        MyNotifyManager.notify(100, builder.build());;
    }
}