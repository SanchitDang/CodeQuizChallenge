package com.sanapplications.codequizchallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AccountActivity extends AppCompatActivity {

    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userID;
    TextView user_profile_name, genTT, sqlTT, devopsTT, linuxTT, bashTT, dockerTT, codingTT, cmsTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        genTT = findViewById(R.id.randTT);
        sqlTT = findViewById(R.id.sqlTT);
        devopsTT = findViewById(R.id.devopsTT);
        linuxTT = findViewById(R.id.linuxTT);
        bashTT = findViewById(R.id.bashTT);
        dockerTT = findViewById(R.id.dockerTT);
        codingTT = findViewById(R.id.codingTT);
        cmsTT = findViewById(R.id.cmsTT);
        user_profile_name = findViewById(R.id.user_profile_name);

        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fauth.getCurrentUser().getUid();

        DocumentReference df = fstore.collection("users").document(userID);
        df.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot ds, @Nullable FirebaseFirestoreException error) {
                user_profile_name.setText(ds.getString("email"));
                genTT.setText("General Total Points: " + ds.getLong("gen").intValue());
                sqlTT.setText("SQL Total Points: " + ds.getLong("sql").intValue());
                devopsTT.setText("Devops Total Points: " + ds.getLong("devops").intValue());
                bashTT.setText("Bash Total Points: " + ds.getLong("bash").intValue());
                dockerTT.setText("Docker Total Points: " + ds.getLong("docker").intValue());
                codingTT.setText("Coding Total Points: " + ds.getLong("code").intValue());
                cmsTT.setText("CMS Total Points: " + ds.getLong("cms").intValue());
            }
        });

    }

    public void Clicked_logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
    public void Clicked_home(View view) {
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }
}