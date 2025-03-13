package edu.lewisu.cs.example.todonotify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationAlertReceiver extends BroadcastReceiver {
    //make sure that we and this class in the manifest

    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String ACTION_REVIEW_REMINDER = "review-reminder";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action  = intent.getAction();
        //to help us see that something was done
        Log.d(NotificationAlertReceiver.class.getSimpleName(), "'action = " + action);

        if(action.equals(ACTION_REVIEW_REMINDER)){
            NotificationUtils.remindUser(context);
        }else if (action.equals(ACTION_DISMISS_NOTIFICATION)){
            //dismiss notification
            NotificationUtils.clearAllNotifications(context);// we  made this method

        }

    }

}//end of class
