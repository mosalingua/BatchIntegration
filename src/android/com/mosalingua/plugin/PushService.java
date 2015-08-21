package _PACKAGE_;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import com.batch.android.*;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PushService extends IntentService
{
    public PushService()
    {
        super("MyPushService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        try
        {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isNotificationsEnabled = sharedPref.getBoolean("isNotificationsEnabled", true);

            if (isNotificationsEnabled) {
                // Display the notification
                Batch.Push.displayNotification(this, intent);
            }
        }
        finally
        {
            PushReceiver.completeWakefulIntent(intent);
        }
    }
}
