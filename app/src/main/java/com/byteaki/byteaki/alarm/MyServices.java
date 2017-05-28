package com.byteaki.byteaki.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
//    public void notifyUpdateDisciplinas(List<Notificacao> notificacoes) {
//        FirebaseAnalytics.getInstance(this).logEvent(FirebaseInterface.EVENT_USER_NOTIFIED,new Bundle());
//        Context c = this;
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        Set<String> catsHabilitadas = prefs.getStringSet(getString(R.string.preference_categorias_notificao), null);
//
//        if (notificacoes != null && notificacoes.size() > 0) {
//            for (Notificacao notificacao : notificacoes) {
//                if (catsHabilitadas != null && catsHabilitadas.contains(String.valueOf(notificacao.getCATEGORIA()))) {
//                    showNotification(notificacao);
//                }
//            }
//        }
//    }
//
//    private void showNotification(Notificacao notificacao) {
//        Notificacao.cont++;
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.ic_exam)
//                        .setContentTitle(notificacao.getTitle())
//                        .setContentText(notificacao.getMessage())
//                        .setStyle(new NotificationCompat.BigTextStyle()
//                                .bigText(notificacao.getMessage()))
//                        .setAutoCancel(true);
//
//        Intent resultIntent = new Intent(this, MainActivity.class);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//        mBuilder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(Notificacao.cont, mBuilder.build());
//    }

}

