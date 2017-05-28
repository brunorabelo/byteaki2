package com.byteaki.byteaki.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.byteaki.byteaki.R;
import com.byteaki.byteaki.model.NotificationModel;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.byteaki.byteaki.constants.Constants.FIREBASE_URL;


public class NotificationMakerActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_maker);

        Button enviar = (Button) findViewById(R.id.enviar_notificacao);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title=(EditText) findViewById(R.id.title_notification);
                EditText description=(EditText) findViewById(R.id.Ask_Title_Notification);
                List<Integer> idGroup = new ArrayList<Integer>();
                idGroup.add(0);
                idGroup.add(1);




                FirebaseDatabase.getInstance()
                        .getReference("users_notifications/"+FirebaseAuth.getInstance().getCurrentUser().getUid()).push()
                        .setValue(new NotificationModel(title.getText().toString()
                                ,description.getText().toString(), FirebaseAuth.
                                getInstance().getCurrentUser().getEmail(),idGroup)).addOnCompleteListener(NotificationMakerActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(NotificationMakerActivity.this,"Sucesso",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(NotificationMakerActivity.this,"Falha",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });







    }


    void sendNotification(String user,final String message){




//        Firebase ref = new Firebase(FIREBASE_URL);
//        final Firebase notifications = ref.child("notificationRequests");
//        Map notification = new HashMap<>();
//        notification.put("username", user);
//        notification.put("message", message);
//
//        notifications.push().setValue(notification);


    }
}
