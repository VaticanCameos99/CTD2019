package com.pisb.ctd;

import android.app.PendingIntent;
import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());//Generates the Notificatio

    }

    /*EXPLANATION OF ABOVE CODE: once we push a notification thru firebase, onMessageReceived()
     is called, which in turn calls showNotification(). This function create a notification obj
      using the notification builder and will notify it using the notification manager.
      This means, our notification will have a String title, String message that we've sent,
      thru firebase */

    //User defined function.
    public void showNotification(String title, String message) {

        PendingIntent pi = PendingIntent.getActivity(this, 1, new Intent(this, splashscreen.class), PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotification")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_ctd)
                .setAutoCancel(true)    //To cancel the notification once it has been clicked
                .setContentText(message);

        //Builder obj creates a notification object

        NotificationManagerCompat manager = NotificationManagerCompat.from(this); //Compat nesures that the code is working on all versions of android
        //manager creates new notifications
        manager.notify(99, builder.build());//build() creates the notification obj
    }
    //THis method is called in onMessageReceived()
}
