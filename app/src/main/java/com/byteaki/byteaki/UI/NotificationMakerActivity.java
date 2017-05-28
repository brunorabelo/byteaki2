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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.byteaki.byteaki.constants.Constants.FIREBASE_URL;


public class NotificationMakerActivity extends AppCompatActivity {



String groupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_maker);



        if(!getIntent().hasExtra("group_name")) {
            //Mensagem de erro
            finish();
        }

       groupId= getIntent().getStringExtra("group_name");




        FirebaseDatabase.getInstance().getReference("groups/"+groupId+"/name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    EditText grupo = (EditText) findViewById(R.id.Ask_Group);
                    grupo.setText(dataSnapshot.getValue().toString());
                    grupo.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Button enviar = (Button) findViewById(R.id.enviar_notificacao);




        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title=(EditText) findViewById(R.id.title_notification);
                EditText description=(EditText) findViewById(R.id.Ask_Title_Notification);


                Long tsLong = System.currentTimeMillis()/1000;

                FirebaseDatabase.getInstance()
                        .getReference("users_notification/"+FirebaseAuth.getInstance().getCurrentUser().getUid()).push()
                        .setValue(new NotificationModel(tsLong,title.getText().toString()
                                ,description.getText().toString(), FirebaseAuth.
                                getInstance().getCurrentUser().getUid(),groupId)).addOnCompleteListener(NotificationMakerActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(NotificationMakerActivity.this,"Sucesso",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(NotificationMakerActivity.this,"Erro",Toast.LENGTH_SHORT).show();

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
