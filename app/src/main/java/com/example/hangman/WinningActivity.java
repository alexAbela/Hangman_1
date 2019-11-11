package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinningActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView winningText, winningWord;
    private Button restartButton;
    private String winningWordString, winningTextString;
    private int mistakes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);
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
