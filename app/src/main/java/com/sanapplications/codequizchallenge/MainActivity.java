package com.sanapplications.codequizchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ModelClass> queList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //getting data from DataSnapshot
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelClass modelClass = dataSnapshot.getValue(ModelClass.class);
                    queList.add(modelClass);
                }

                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        queList.add(new ModelClass("Is ur gf Kutiya?", "Hell ya!","Definitely","i guess yess","Haa yaar","Hell ya!"));
//        queList.add(new ModelClass("Are you ho?", "Hell ya!","Definitely","i guess yess","Haa yaar", "Hell ya!"));
//        queList.add(new ModelClass("You eat shit?", "Hell ya!","Definitely","i guess yess","Haa yaar", "Hell ya!"));
//        queList.add(new ModelClass("Is ur father R?", "Hell ya!","Definitely","i guess yess","Haa yaar","Hell ya!"));
//        queList.add(new ModelClass("You are Chakka?", "Hell ya!","Definitely","i guess yess","Haa yaar","Hell ya!"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                startActivity(intent);
            }
        },1500);

    }
}