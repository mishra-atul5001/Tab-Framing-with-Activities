package com.example.mishr.tabbingfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class ChatActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private Button button;

    private ArrayList<String> data = new ArrayList<>();
     private ArrayAdapter<String> arrayAdapter;
    private String username;
    private DatabaseReference databasereference;
    private FirebaseDatabase firebasedatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listView = (ListView)findViewById(R.id.listviewchat);
        editText = (EditText)findViewById(R.id.edittext);
        button = (Button)findViewById(R.id.sendbutton);
        arrayAdapter = new ArrayAdapter<String>(ChatActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
        databasereference = FirebaseDatabase.getInstance().getReference().getRoot();

        request_userName();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put(editText.getText().toString(),"");
                databasereference.updateChildren(map);
                Toast.makeText(getApplicationContext(),editText.getText().toString(),Toast.LENGTH_SHORT).show();
                editText.setText("");


            }
        });

        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator iterator = dataSnapshot.getChildren().iterator();

                while (iterator.hasNext()){
                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                data.clear();
                data.addAll(set);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(ChatActivity.this,ChatRoomActivity.class);
                in.putExtra("room_name",((TextView)view).getText().toString());
                in.putExtra("user_name",username);
                startActivity(in);

            }
        });




    }

    private void request_userName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
        builder.setTitle("Enter Username");
        builder.setCancelable(false);

        final EditText edittextusername = new EditText(ChatActivity.this);
        builder.setView(edittextusername);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                username = edittextusername.getText().toString();
            }
        });
      builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              dialogInterface.cancel();
              request_userName();
          }
      });;
        builder.show();
    }



}
