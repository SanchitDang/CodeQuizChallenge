package com.sanapplications.codequizchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ModelClass> queList;
    DatabaseReference databaseReference;
    public String API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queList = new ArrayList<>();


//        // getting ques using firebase
//        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                //getting data from DataSnapshot
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    ModelClass modelClass = dataSnapshot.getValue(ModelClass.class);
//                    queList.add(modelClass);
//                }
//
//                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                startActivity(intent);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });





        // getting ques using API
        API = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=Linux&limit=10";


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api.php?amount=10&category=18&difficulty=easy&type=multiple";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            Log.d("ArrJson", jsonArray.toString());

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject ques = jsonArray.getJSONObject(i);
                                JSONArray jsonArray_incAns = ques.getJSONArray("incorrect_answers");

                                String que = ques.getString("question");
                                String op1 = jsonArray_incAns.getString(0);
                                String op2 = jsonArray_incAns.getString(1);
                                String op3 = jsonArray_incAns.getString(2);
                                String op4 = ques.getString("correct_answer");
                                String ans = ques.getString("correct_answer");

                                Log.d("Que", que+op1+op2);
                                queList.add(new ModelClass(que, op1, op2, op3, op4, ans));
                            }


                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            startActivity(intent);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //String u = response.toString();
                        //Log.d("Json Req", u);
//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("Json Error","err");
                //textView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);



        // getting ques using ArrayList
//        queList.add(new ModelClass("Is ur gf Kutiya?", "Hell ya!","Definitely","i guess yess","Haa yaar","Hell ya!"));
//        queList.add(new ModelClass("Are you ho?", "Hell ya!","Definitely","i guess yess","Haa yaar", "Hell ya!"));
//        queList.add(new ModelClass("You eat shit?", "Hell ya!","Definitely","i guess yess","Haa yaar", "Hell ya!"));
//        queList.add(new ModelClass("Is ur father R?", "Hell ya!","Definitely","i guess yess","Haa yaar","Hell ya!"));
//        queList.add(new ModelClass("You are Chakka?", "Hell ya!","Definitely","i guess yess","Haa yaar","Hell ya!"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // getting ques using ArrayList
//                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                startActivity(intent);



            }
        },1500);

    }
}