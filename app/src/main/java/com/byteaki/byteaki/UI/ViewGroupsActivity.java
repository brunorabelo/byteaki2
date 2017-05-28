package com.byteaki.byteaki.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.byteaki.byteaki.R;
import com.firebase.client.Firebase;
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


public class ViewGroupsActivity extends AppCompatActivity {
    public ArrayList<String> arr;
    public ArrayAdapter adapter;


    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewgroups);
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("group_admins").
            child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    arr = new ArrayList<>();

        arr.add("Texto aleatorio");

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if(dataSnapshot!=null) {
//                map2list((Map) dataSnapshot.getValue());
//                FirebaseDatabase.getInstance().getReference("groups/"+dataSnapshot.getValue()).child("name").
//                        addListenerForSingleValueEvent(new );

            }
            //formats the datasnapshot entries to strings
            adapter.notifyDataSetChanged();
            //makes the ListView realtime
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
//            System.out.println(databaseError.toException());
            // ...
        }
    };
    mDatabase.addValueEventListener(listener);

    adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, arr);

    ListView listView = (ListView) findViewById(R.id.lv_groups);
    listView.setAdapter(adapter);
}

    public void map2list(Map<String,Long> map) {

        arr.clear();
        arr.add("Texto aleatorio");
        if (map != null) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                arr.add(entry.getValue());
//            }

        }
    }

}