package com.byteaki.byteaki.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

/**
 * Created by macbook on 28/05/17.
 */

public class AlarmClass extends AppCompatActivity {
    public static void iniciarAlarme(Context contexto) {
        AlarmManager gerenciador = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
        long frequenciaAtualizacao = 30*1000*60;

        gerenciador.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, frequenciaAtualizacao,
                frequenciaAtualizacao, obterIntentPendente(contexto));
    }

    private static PendingIntent obterIntentPendente(Context contexto) {
        Intent i = new Intent(contexto, AlarmReceiver.class);
        return PendingIntent.getBroadcast(contexto, 0, i, 0);
    }


}
