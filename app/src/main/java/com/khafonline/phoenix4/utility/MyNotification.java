package com.khafonline.phoenix4.utility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.khafonline.phoenix4.activity.HomeActivity;
import com.khafonline.phoenix4.core.Constants;
import com.khafonline.phoenix4.volley.MyApplication;

import java.util.List;

public class MyNotification {

    public static class MyActionIntent {
        public Intent intent;
        public String text;
        public int icon;

        public MyActionIntent(Intent intent, String text, int icon) {
            this.intent = intent;
            this.text = text;
            this.icon = icon;
        }
    }

    private static String CHANNEL_ID = Constants.NOTIFICATION_CHANNEL_ID;

    private static void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = Constants.NOTIFICATION_CHANNEL_NAME;
            String description = Constants.NOTIFICATION_CHANNEL_DESCRIPTION;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public static void notify(String title, String text, @Nullable Intent contentIntent, @Nullable List<MyActionIntent> actionIntents, int icon) {

        Context context = MyApplication.getAppContext();
        createNotificationChannel(context);
        if (contentIntent == null) {
            contentIntent = new Intent(context, HomeActivity.class);
        }

        // Create an explicit intent for an Activity in your app
        contentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, 0, contentIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(text)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true);

        if (actionIntents != null) {
            for (int i = 0; i < actionIntents.size(); i++) {
                MyActionIntent actionIntent = actionIntents.get(i);
                actionIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent actionPendingIntent = PendingIntent.getActivity(context, 0, actionIntent.intent, 0);
                builder.addAction(actionIntent.icon, actionIntent.text,
                        actionPendingIntent);

            }
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
        int notificationId = 3633;
        notificationManager.notify(notificationId, builder.build());
    }
}
