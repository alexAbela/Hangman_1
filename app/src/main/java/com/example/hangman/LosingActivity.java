package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LosingActivity extends AppCompatActivity {

    private TextView losingWord;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing);
        Bundle b = getIntent().getExtras();
        String losingWordString = b.getString("losingWord");

        losingWord = findViewById(R.id.losingWord);
        losingWord.setText(losingWordString);

        restartButton = findViewById(R.id.restartButton);


    }
}
