package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    SharedPreferences preferences;
    String colour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        button = findViewById(R.id.playButton);
        button.setOnClickListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        colour = preferences.getString("colour","GREEN");
        if(colour.equalsIgnoreCase("Green")){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        } else if (colour.equalsIgnoreCase("white")){
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else if (colour.equalsIgnoreCase("red")){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }

    public void onClick(View v) {
        openMainActivity();
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
