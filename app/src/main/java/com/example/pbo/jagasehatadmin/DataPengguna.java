package com.example.pbo.jagasehatadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class DataPengguna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengguna);
        final ArrayList<User> dataPengguna = new ArrayList<>();
        DatabaseReference hello = FirebaseDatabase.getInstance().getReference().child("user");
        hello.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    dataPengguna.add(user);
                }
                UserAdapter adapter = new UserAdapter(DataPengguna.this,dataPengguna);
                ListView listView = (ListView) findViewById(R.id.listPengguna);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
