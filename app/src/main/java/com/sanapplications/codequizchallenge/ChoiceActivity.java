package com.sanapplications.codequizchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ChoiceActivity extends AppCompatActivity {

    ImageView general_ques, linux_ques, bash_ques, docker_ques, sql_ques,cms_ques,code_ques,devops_ques;
    public static ArrayList<ModelClass> queList;
    String APIlinux, APIgen, APIbash, APIdocker, APIsql, APIcms, APIcode, APIdevops;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        general_ques = findViewById(R.id.general_ques);
        linux_ques = findViewById(R.id.linux_ques);
        bash_ques = findViewById(R.id.bash_ques);
        docker_ques = findViewById(R.id.docker_ques);
        sql_ques = findViewById(R.id.sql_ques);
        cms_ques = findViewById(R.id.cms_ques);
        code_ques = findViewById(R.id.code_ques);
        devops_ques = findViewById(R.id.devops_ques);

        queList = new ArrayList<>();



    }

    public void Clicked_general(View view) {
        APIgen = "https://opentdb.com/api.php?amount=10&category=18&difficulty=easy&type=multiple";
        APIcall(APIgen);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);
    }


    public void Clicked_linux(View view) {
        APIlinux = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=Linux&limit=10";
        APIcall2(APIlinux);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);
    }


    public void Clicked_bash(View view) {
        APIbash = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=Bash&limit=10";
        APIcall2(APIbash);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);
    }


    public void Clicked_docker(View view) {
        APIdocker = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=Bash&limit=10";
        APIcall2(APIdocker);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);
    }


    public void Clicked_cms(View view) {
        APIcms = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=CMS&limit=10";
        APIcall2(APIcms);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);
    }

    public void Clicked_sql(View view) {
        APIsql = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=sql&limit=10";
        APIcall2(APIsql);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);
    }

    public void Clicked_code(View view) {
        APIcode = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=Code&limit=10";
        APIcall2(APIcode);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);
    }


    public void Clicked_devops(View view) {
        APIdevops = "https://quizapi.io/api/v1/questions?apiKey=XXWi9TQjBCmahBVjIzcSeR9uZvQbUcJjsqyaviPP&category=DevOps&limit=10";
        APIcall2(APIdevops);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(ChoiceActivity.this, DashboardActivity.class);
        startActivity(i);

    }


    public void APIcall(String APIurl) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = APIurl;

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

                        //Array to put all options and shuffle them
                        ArrayList<String> ops = new ArrayList<String>();
                        ops.add(op1);ops.add(op2);ops.add(op3);ops.add(op4);
                        Collections.shuffle(ops);

                        queList.add(new ModelClass(que,
                                ops.get(0), ops.get(1), ops.get(2), ops.get(3),
                                ans));
                    }

                } catch (JSONException e) {
                    Log.d("ArrJson","JsonErr");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ArrJson","VolleyErr");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }


    public void APIcall2(String APIurl) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = APIurl;

        // Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject ques = response.getJSONObject(i);
                        Log.d("ArrJson", ques.toString());

                        JSONObject op = ques.getJSONObject("answers");
                        String que = ques.getString("question");
                        String op1 = op.getString("answer_a");
                        String op2 = op.getString("answer_b");
                        String op3 = op.getString("answer_c");
                        String op4 = op.getString("answer_d");
                        String ans = ques.getString("correct_answer");

                        if (ans.equals("answer_a")) ans=op1;
                        else if (ans.equals("answer_b")) ans=op2;
                        else if (ans.equals("answer_c")) ans=op3;
                        else ans=op4;

                        //Array to put all options and shuffle them
                        ArrayList<String> ops = new ArrayList<String>();
                        ops.add(op1);ops.add(op2);ops.add(op3);ops.add(op4);
                        Collections.shuffle(ops);

                        queList.add(new ModelClass(que,
                                ops.get(0), ops.get(1), ops.get(2), ops.get(3), ans));

                    }

                } catch (JSONException e) {
                    Log.d("ArrJson", "JsonErr");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ArrJson","VolleyErr");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);

    }

    public void Clicked_logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void Clicked_account(View view) {
        Intent i = new Intent(ChoiceActivity.this, AccountActivity.class);
        startActivity(i);
    }

}