package _PACKAGE_;

import android.support.v4.content.WakefulBroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.app.Activity;

public class PushReceiver extends WakefulBroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        ComponentName comp = new ComponentName(context.getPackageName(), PushService.class.getName());
        startWakefulService(context, intent.setComponent(comp));
        setResultCode(Activity.RESULT_OK);
    }

}
