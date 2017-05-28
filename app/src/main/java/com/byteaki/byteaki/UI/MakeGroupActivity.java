package com.byteaki.byteaki.UI;

import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.byteaki.byteaki.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MakeGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makegroup);


        Button criarGrupo = (Button) findViewById(R.id.create_group_button);


        criarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nome = (EditText) findViewById(R.id.field_groupname);
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("groups").push();
                ref.child("name").setValue(nome.getText().toString());
                ref.child("admin").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(true);
                ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(true);

                DatabaseReference ref2 =FirebaseDatabase.getInstance().getReference("group_admins");
                ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(
                        ref.getKey()).setValue(true);



            }
        });






    }
}
