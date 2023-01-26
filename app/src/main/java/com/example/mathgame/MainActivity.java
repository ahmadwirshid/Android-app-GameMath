package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addition;
    Button multiplication;
    Button subtraction;
    Button minus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addition = findViewById(R.id.buttonAdd);
        minus = findViewById(R.id.buttonMinus);
        multiplication = findViewById(R.id.buttonMulti);
        subtraction = findViewById(R.id.buttonSub);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                String str =addition.getText().toString();
                intent.putExtra("message", str);
                startActivity(intent);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                String str =minus.getText().toString();
                intent.putExtra("message", str);
                startActivity(intent);
            }
        });
        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                String str =multiplication.getText().toString();
                intent.putExtra("message", str);
                startActivity(intent);
            }
        });
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                String str =subtraction.getText().toString();
                intent.putExtra("message", str);
                startActivity(intent);
            }
        });

    }
}