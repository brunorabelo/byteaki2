package com.byteaki.byteaki.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.NotificationCompat;

import com.byteaki.byteaki.R;
import com.byteaki.byteaki.UI.MainActivity;
import com.byteaki.byteaki.model.NotificationModel;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by macbook on 28/05/17.
 */
public class MyServices extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int StartId) {


        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            FirebaseDatabase.getInstance().getReference("user_notification/" +
                    FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
//                        if()

                            String a = "Brunofsdsa";

                            a.toString();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

        }


        return START_NOT_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//
//
    public void notifyUpdateDisciplinas(NotificationModel notificacao) {
//        FirebaseAnalytics.getInstance(this).logEvent(FirebaseInterface.EVENT_USER_NOTIFIED,new Bundle());
        Context c = this;
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        Set<String> catsHabilitadas = prefs.getStringSet(getString(R.string.preference_categorias_notificao), null);

        if (notificacao != null) {
             showNotification(notificacao);
                }

    }
//
    private void showNotification(NotificationModel notificacao) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.ic_exam)
                        .setContentTitle(notificacao.getNotificationTitle())
                        .setContentText(notificacao.getNotificationContent())
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(notificacao.getNotificationContent()))
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

}

