package com.example.multiple_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class show extends AppCompatActivity {
    ListView lstvw;
    DatabaseReference myref;
    String s="";
    String ss="";
    SharedPreferences pref;
    TextView name1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        lstvw=findViewById(R.id.list);
        name1=findViewById(R.id.name);

        myref= FirebaseDatabase.getInstance().getReference();
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
         String name=pref.getString("name", null);
         name1.setText(name);

        myref.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                ArrayList<String> list = new ArrayList<>();

                for(DataSnapshot ds: iterable)
                {
                     s=""+ds.getValue();
                    ss=""+ss+ds.getValue();
                    list.add(s);




                }
                ArrayAdapter adapter=new ArrayAdapter(show.this,android.R.layout.simple_expandable_list_item_1,list);
                lstvw.setAdapter(adapter);

              //  Toast.makeText(show.this, ""+ss, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       // Toast.makeText(this, list.size()+""+list+s+ss, Toast.LENGTH_SHORT).show();

    }
}