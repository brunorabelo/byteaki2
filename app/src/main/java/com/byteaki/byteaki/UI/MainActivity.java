package com.byteaki.byteaki.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.byteaki.byteaki.R;
import com.byteaki.byteaki.alarm.AlarmClass;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlarmClass.iniciarAlarme(this);

        Button fab = (Button) findViewById(R.id.sendnotification);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,NotificationMakerActivity.class);
                a.putExtra("group_name","Zgzz2AXweKWZQIVl882aL73xOn72");
                startActivity(a);
            }
        });





        Button criar_grupo = (Button) findViewById(R.id.makegroup);

        criar_grupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,MakeGroupActivity.class);
                startActivity(a);
            }
        });

        Button view_group  = (Button) findViewById(R.id.viewgroups);

        view_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,ViewGroupsActivity.class);
                startActivity(a);
            }
        });

        Button logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent a = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(a);
            }
        });



        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
        }
        else{
            finish();
        }

    }

}
