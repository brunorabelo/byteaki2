package com.byteaki.byteaki.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.byteaki.byteaki.R;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import static com.byteaki.byteaki.constants.Constants.FIREBASE_URL;


public class NotificationMakerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_maker);
        //
    }


    void sendNotification(String user,final String message){
        Firebase ref = new Firebase(FIREBASE_URL);
        final Firebase notifications = ref.child("notificationRequests");

        Map notification = new HashMap<>();
        notification.put("username", user);
        notification.put("message", message);

        notifications.push().setValue(notification);


    }
}
