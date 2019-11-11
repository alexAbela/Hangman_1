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

public class WinningActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView winningText, winningWord;
    private Button restartButton;
    private String winningWordString, winningTextString;
    private int mistakes;
    SharedPreferences preferences;
    String colour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);

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
        winningWordString = "Ordet var: \n" + b.getString("word");
        mistakes = b.getInt("mistakes");
        winningTextString = "Du vinder!\nMed kun " + mistakes + " fejl!";

        winningText = findViewById(R.id.winningText);
        winningText.setText(winningTextString);
        winningWord = findViewById(R.id.winningWord);
        winningWord.setText(winningWordString);
        restartButton = findViewById(R.id.winRestartButton);
        restartButton.setOnClickListener(this);

    }

    public void onClick(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
