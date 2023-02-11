package com.sanapplications.codequizchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.HashMap;
import java.util.Map;

public class WonActivity extends AppCompatActivity {

    CircularProgressBar c_pb;
    TextView result;
    LinearLayout btn_share;
    int correct;
    int index;
    public String choice;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        correct = getIntent().getIntExtra("correct", 0);
        index = getIntent().getIntExtra("index", 0);
        choice = getIntent().getStringExtra("choice");

        Toast.makeText(this, choice, Toast.LENGTH_SHORT).show();

        c_pb = findViewById(R.id.circularProgressBar);
        result = findViewById(R.id.result);
        btn_share = findViewById(R.id.btn_share);

        c_pb.setProgress(correct);
        c_pb.setProgressMax(index);
        result.setText(correct+"/"+index);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference df = fStore.collection("users").document(userID);

        df.update(choice, FieldValue.increment(correct)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(WonActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(WonActivity.this, "Data Adding Failed", Toast.LENGTH_SHORT).show();
                Log.d("fail", e.toString());
            }
        });


        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nI got " + correct + " out of 10! You can also try\n\n";
                    shareMessage = shareMessage + "\nhttps://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

    }

    public void Clicked_account(View view) {
        Intent i = new Intent(WonActivity.this, AccountActivity.class);
        startActivity(i);
        finish();
    }

    public void Clicked_home(View view) {
        Intent i = new Intent(WonActivity.this, DashboardActivity.class);
        startActivity(i);
        finish();
    }
}