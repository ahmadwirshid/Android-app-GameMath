package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView score;
    TextView time;
    TextView life;
    EditText answer;
    TextView question;
    Button ok;
    Button next;
    Random random = new Random();
    int number1;
    int number2;
    int userAnswer;
    int realAnswer;
    int userScore = 0;
    int userLife =3;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;
    private int ore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.textViewScore);
        time = findViewById(R.id.textViewTime);
        life = findViewById(R.id.textViewLife);
        answer = findViewById(R.id.editTextAnswer);
        question = findViewById(R.id.textViewQuestion);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonNext);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message");

        gameContinue(str);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer =Integer.valueOf( answer.getText().toString());
                pauseTimer();
                if(userAnswer==realAnswer)
                {
                    userScore = userScore + 10;
                    score.setText("" + userScore);
                    question.setText("Congratulations, Your answer is true.");

                }
                else
                {
                    userLife = userLife - 1;
                    life.setText("" + userLife);
                    question.setText("Sorry, Your answer is wrong.");
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText("");
                gameContinue(str);
                restTimer();

                if (userLife <= 0)
                {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Game.this,Result.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();
                }
                else {
                    gameContinue(str);
                }
            }
        });
    }
    public void gameContinue(String s)
    {

        String s1 = s;
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        if (s1.equals("addition"))
        {
            realAnswer = number1 + number2;
            question.setText(number1 + " + " + number2);
        } else if (s1.equals("multiplication")) {
            realAnswer = number1 * number2;
            question.setText(number1 + " * " + number2);
        }else if (s1.equals("subtraction")){
            realAnswer = number1 / number2;
            question.setText(number1 + " / " + number2);
        }else if (s1.equals("minus")){
            realAnswer = number1 - number2;
            question.setText(number1 + " - " + number2);
        }
        startTimer();

    }
    public void startTimer(){
        timer = new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                restTimer();
                updateText();
                userLife = userLife - 1;
                life.setText(""+ userLife);
                question.setText("Sorry! Time is up!");
            }
        }.start();
        timer_running = true;
    }
    public void updateText(){
        int second = (int)(time_left_in_milis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void pauseTimer(){
        timer.cancel();
        timer_running = false;
    }
    public void restTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }


}