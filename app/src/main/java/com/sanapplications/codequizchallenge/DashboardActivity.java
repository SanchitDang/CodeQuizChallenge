package com.sanapplications.codequizchallenge;

import static com.sanapplications.codequizchallenge.ChoiceActivity.queList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    TextView card_question, option1, option2, option3, option4;
    CardView card1, card2, card3, card4;
    LinearLayout btn_next;

    ProgressBar pb;
    CountDownTimer countDownTimer;
    int timerVal = 30;

    List<ModelClass> ques;
    ModelClass modelClass;
    int index=0;

    int correct=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        ques = queList;
        //Collections.shuffle(ques);
        modelClass=queList.get(index);

        card1.setBackgroundColor(getResources().getColor(R.color.white));
        card2.setBackgroundColor(getResources().getColor(R.color.white));
        card3.setBackgroundColor(getResources().getColor(R.color.white));
        card4.setBackgroundColor(getResources().getColor(R.color.white));

        btn_next.setClickable(false);

        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerVal--;
                pb.setProgress(timerVal);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.timeout_dialog);

                dialog.findViewById(R.id.btn_restart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DashboardActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                });

                dialog.show();
            }
        }.start();

        setAllData();

    }

    private void Hooks() {
        pb = findViewById(R.id.progBar);

        card_question = findViewById(R.id.card_question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);

        btn_next = findViewById(R.id.btn_next);
    }

    private void setAllData() {
        card_question.setText(modelClass.getQue());
        option1.setText(modelClass.getOp1());
        option2.setText(modelClass.getOp2());
        option3.setText(modelClass.getOp3());
        option4.setText(modelClass.getOp4());

        timerVal=30;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void GameWon() {
        Intent i = new Intent(DashboardActivity.this, WonActivity.class);
        i.putExtra("correct", correct);
        startActivity(i);
    }

    public void enableButton(){
        card1.setClickable(true);
        card2.setClickable(true);
        card3.setClickable(true);
        card4.setClickable(true);
    }

    public void disableButton(){
        card1.setClickable(false);
        card2.setClickable(false);
        card3.setClickable(false);
        card4.setClickable(false);
    }

    public void resetColor(){
        card1.setBackgroundColor(getResources().getColor(R.color.white));
        card2.setBackgroundColor(getResources().getColor(R.color.white));
        card3.setBackgroundColor(getResources().getColor(R.color.white));
        card4.setBackgroundColor(getResources().getColor(R.color.white));
    }


    public void Correct(CardView c){
        c.setBackgroundColor(getResources().getColor(R.color.green));

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct++;
                index++;

                modelClass = queList.get(index);
                resetColor();
                setAllData();
                enableButton();
            }
        });

    }

    public void Wrong(CardView c){
        c.setBackgroundColor(getResources().getColor(R.color.red));

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(index<queList.size()-1){
                    index++;

                    modelClass = queList.get(index);
                    resetColor();
                    setAllData();
                    enableButton();

                }
                else GameWon();
            }
        });

    }


    public void Clicked_opt1(View view) {
        disableButton();
        btn_next.setClickable(true);

        if(modelClass.getOp1().equals(modelClass.getAns())){
            card1.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<queList.size()-1) Correct(card1);
            else GameWon();
        }
        else Wrong(card1);

    }

    public void Clicked_opt2(View view) {
        disableButton();
        btn_next.setClickable(true);

        if(modelClass.getOp2().equals(modelClass.getAns())){
            card2.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<queList.size()-1) Correct(card2);
            else GameWon();
        }
        else Wrong(card2);

    }

    public void Clicked_opt3(View view) {
        disableButton();
        btn_next.setClickable(true);

        if(modelClass.getOp3().equals(modelClass.getAns())){
            card3.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<queList.size()-1) Correct(card3);
            else GameWon();
        }
        else Wrong(card3);

    }


    public void Clicked_opt4(View view) {
        disableButton();
        btn_next.setClickable(true);

        if(modelClass.getOp4().equals(modelClass.getAns())){
            card4.setBackgroundColor(getResources().getColor(R.color.green));
            if(index<queList.size()-1) Correct(card4);
            else GameWon();
        }
        else Wrong(card4);

    }



}