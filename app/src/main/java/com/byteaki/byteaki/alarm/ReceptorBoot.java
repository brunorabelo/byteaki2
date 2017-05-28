package com.byteaki.byteaki.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by macbook on 28/05/17.
 */

    public class ReceptorBoot extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            AlarmClass.iniciarAlarme(context);
        }

    }

