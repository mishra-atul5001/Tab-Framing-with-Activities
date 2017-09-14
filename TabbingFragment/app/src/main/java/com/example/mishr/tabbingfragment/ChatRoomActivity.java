package com.example.mishr.tabbingfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class ChatRoomActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    String room_name,user_name;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        textView = (TextView)findViewById(R.id.textViewchat);
        editText = (EditText)findViewById(R.id.edittextChat);
        button = (Button)findViewById(R.id.buttonchat);

        room_name = getIntent().getExtras().get("room_name").toString();
        user_name = getIntent().getExtras().get("user_name").toString();
        setTitle("Room : " + room_name);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(room_name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map2 = new HashMap<String,Object >();
                key = databaseReference.push().getKey();
                databaseReference.updateChildren(map2);
                DatabaseReference databasereference1 = databaseReference.child(key);
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("user_name",user_name);
                map.put("message",editText.getText().toString());
                databasereference1.updateChildren(map);



            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_data(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })      ;




    }
    String chat_user_name,chat_user_message;
    private void append_data(DataSnapshot dataSnapshot){
        Iterator iterator = dataSnapshot.getChildren().iterator();
        while (iterator.hasNext()){

            chat_user_message = (String)((DataSnapshot)iterator.next()).getValue();
            chat_user_name = (String)((DataSnapshot)iterator.next()).getValue();
            textView.append(chat_user_name + ": " +chat_user_message+"\n");

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
