package com.example.multiple_user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference myref;
    TextView txt1,txt2,txt3,txt4;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        txt1 = findViewById(R.id.textHome);
        txt2 = findViewById(R.id.textMobile);
        txt3 = findViewById(R.id.textElectronics);
        txt4 = findViewById(R.id.textLogs);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        myref= FirebaseDatabase.getInstance().getReference();
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                final View customLayout = getLayoutInflater().inflate(R.layout.mylayout, null);
                alertDialog.setView(customLayout);
                alertDialog.setTitle("Add to Home database");
                alertDialog.setCancelable(true);
                alertDialog.setIcon(R.drawable.touch);
                alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // send data from the AlertDialog to the Activity
                        EditText editText = customLayout.findViewById(R.id.txt11);
                        Toast.makeText(MainActivity.this,editText.getText().toString(),Toast.LENGTH_LONG).show();
                        myref.child("Home").push().setValue(""+editText.getText());

                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(true);
                alert.show();
            }
            });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                final View customLayout = getLayoutInflater().inflate(R.layout.mylayout, null);
                alertDialog.setView(customLayout);
                alertDialog.setTitle("Add Mobile to database");
                alertDialog.setCancelable(true);
                alertDialog.setIcon(R.drawable.touch);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // send data from the AlertDialog to the Activity
                        EditText editText = customLayout.findViewById(R.id.txt11);
                        Toast.makeText(MainActivity.this,editText.getText().toString(),Toast.LENGTH_LONG).show();
                        myref.child("Mobile").push().setValue(""+editText.getText());

                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(true);
                alert.show();
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                final View customLayout = getLayoutInflater().inflate(R.layout.mylayout, null);
                alertDialog.setView(customLayout);
                alertDialog.setTitle("Add Electronics to database");
                alertDialog.setCancelable(true);
                alertDialog.setIcon(R.drawable.touch);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // send data from the AlertDialog to the Activity
                        EditText editText = customLayout.findViewById(R.id.txt11);
                        Toast.makeText(MainActivity.this,editText.getText().toString(),Toast.LENGTH_LONG).show();
                        myref.child("Electronics").push().setValue(""+editText.getText());

                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setCanceledOnTouchOutside(true);
                alert.show();
            }
        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(MainActivity.this,v);

                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        editor.putString("name",""+item.getTitle());
                        editor.commit();
                        startActivity(new Intent(MainActivity.this,show.class));
                        return true;
                    }
                });

                popup.show();
            }
        });

    }
}