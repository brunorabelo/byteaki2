package com.byteaki.byteaki.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by macbook on 28/05/17.
 */


public class ViewGroupsActivity extends AppCompatActivity {
    public ArrayList<String> arr;
    public ArrayList<String> key;

    public MyAdapter adapter;


    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewgroups);
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("group_admins").
            child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
                                addListenerForSingleValueEvent(new ValueEventListener() {
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
    mDatabase.addValueEventListener(listener);

    adapter = new MyAdapter(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, arr);

    ListView listView = (ListView) findViewById(R.id.lv_groups);
    listView.setAdapter(adapter);



}






class MyAdapter extends ArrayAdapter{


    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       super.getView(position,convertView,parent);
        View row = convertView;

        /* ...Code for holder and so on... */
        if(row!=null) {
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent a = new Intent(ViewGroupsActivity.this, NotificationMakerActivity.class);

                    getIntent().putExtra("group_name", key.get(view.getId()));


                }
            });
        }


        return row;

    }


//    @
//    protected onItemClick




}




}