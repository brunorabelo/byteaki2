package com.byteaki.byteaki.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.byteaki.byteaki.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by macbook on 28/05/17.
 */

public class GroupInfoActivity extends AppCompatActivity {
    public ArrayList<String> arr;
    public ArrayList<String> key;

    public ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_view);

        arr = new ArrayList<>();
        key = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    Map<String, String> map = (Map) dataSnapshot.getValue();
                    if (map != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {

                            FirebaseDatabase.getInstance().getReference("groups/" + entry.getKey() + "/name").
                                    addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
//                                map2list((Map) dataSnapshot.getValue());
                                            if(dataSnapshot.getValue()!=null && dataSnapshot.getRef().getParent()!=null) {
                                                arr.add(dataSnapshot.getValue().toString());

                                                key.add(dataSnapshot.getRef().getParent().getKey());
                                            }
                                            adapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
//            }

                        }

//                map2list((Map) dataSnapshot.getValue());

                    }
                    //formats the datasnapshot entries t
                    //makes the ListView realtime
                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError){
                // Getting Post failed, log a message
//            System.out.println(databaseError.toException());
                // ...
            }

        };
//        mDatabase.addValueEventListener(listener);

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arr);
        ListView listView = (ListView) findViewById(R.id.lv_groups);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent a = new Intent(GroupInfoActivity.this, NotificationMakerActivity.class);
                a.putExtra("group_name", key.get(i));
                startActivity(a);
            }
        });



    }




}