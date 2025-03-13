package edu.lewisu.cs.example.todonotify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class NotificationUtils {
    private static final int TODO_REMINDER_NOTIFICATION_ID = 26;
    private static final String  REMINDER_NOTIFICATION_CHANNEL = "reminder_notification_channel";
    private static final int TO_DO_REMINDER_PENDING_INTENT = 4321; //used when they clink on the notification
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 41;

    public static void remindUser(Context context){
        Intent startActivity = new Intent(context, MainActivity.class);
        PendingIntent startActivityPendongintent = PendingIntent.getActivity(
                context,
                TO_DO_REMINDER_PENDING_INTENT,
                startActivity,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //this is the cancel intent
        Intent ignoreReminderIntent = new Intent(context, NotificationAlertReceiver.class);
        ignoreReminderIntent.setAction(NotificationAlertReceiver.ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getBroadcast(
                context, ACTION_IGNORE_PENDING_INTENT_ID, ignoreReminderIntent,0);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //checking is were are on oreo or later
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    REMINDER_NOTIFICATION_CHANNEL,
                    context.getString(R.string.channel_name),
                    NotificationManager.IMPORTANCE_HIGH
            );

            notificationManager.createNotificationChannel(channel);//now we have a nofity channel
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, REMINDER_NOTIFICATION_CHANNEL)
                .setColor(ContextCompat.getColor(context, R.color.purple_500))
                .setSmallIcon(R.drawable.ic_assignment_black_24dp)
                .setContentTitle(context.getString(R.string.reminder_title))
                .setContentText(context.getString(R.string.reminder_text))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.reminder_text)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(startActivityPendongintent)
                .addAction(R.drawable.ic_assignment_black_24dp, context.getString(R.string.not_now), ignoreReminderPendingIntent)
        //this will add a button on the notification to dismiss^^^
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(TODO_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
        //this is what will send it

    }//end of method

    public static void clearAllNotifications(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(TODO_REMINDER_NOTIFICATION_ID);
    }

}//end of class
