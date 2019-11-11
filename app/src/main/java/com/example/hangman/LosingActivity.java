package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LosingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView losingWord, losingText;
    private Button restartButton;
    SharedPreferences preferences;
    String colour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        colour = preferences.getString("colour","GREEN");
        if(colour.equalsIgnoreCase("Green")){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        } else if (colour.equalsIgnoreCase("white")){
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else if (colour.equalsIgnoreCase("red")){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }


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
