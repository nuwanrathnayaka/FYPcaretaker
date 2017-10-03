package com.kerbio.virtualcaretaker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Nuwan rathnayaka on 9/11/2017.
 */

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "Hellooo, alrm worked ----";
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(3000);
//        Intent intent2 = new Intent(context, MedicationActivity.class);
//        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent2);
    }

    public void setAlarm(Context context){
        Toast.makeText(context, "called", Toast.LENGTH_SHORT).show();

        // get a Calendar object with current time
        //Calendar cal = Calendar.getInstance();
        // add 30 seconds to the calendar object
        //cal.add(Calendar.SECOND, 3);
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.MONTH,8);
        cal.set(Calendar.YEAR,2017);
        cal.set(Calendar.DAY_OF_MONTH,11);
        cal.set(Calendar.HOUR_OF_DAY,14);
        cal.set(Calendar.MINUTE,13);
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 1253, intent, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
    }
}
