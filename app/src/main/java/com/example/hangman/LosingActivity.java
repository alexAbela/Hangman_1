package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LosingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView losingWord, losingText;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing);
        Bundle b = getIntent().getExtras();
        String losingWordString = b.getString("losingWord");

        losingText = findViewById(R.id.losingText);
        losingText.setText("Du har tabt :/ \nOrdet var:");

        losingWord = findViewById(R.id.losingWord);
        losingWord.setText(losingWordString);

        restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(this);

    }

    public void onClick(View v){
            Intent restartGameIntent = new Intent(this, MainActivity.class);
            startActivity(restartGameIntent);

    }
}
