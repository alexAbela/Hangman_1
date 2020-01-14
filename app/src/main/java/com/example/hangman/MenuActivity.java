package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    String colour;
    SharedPreferences preferences;
    Button newGameDR, newGameCustom, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        colour = preferences.getString("colour","GREEN");
        if(colour.equalsIgnoreCase("Green")){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        } else if (colour.equalsIgnoreCase("white")){
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else if (colour.equalsIgnoreCase("red")){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }

        newGameDR = findViewById(R.id.NewGameDR);
        newGameCustom = findViewById(R.id.NewGameCustom);
        settings = findViewById(R.id.settings);

        newGameDR.setOnClickListener(this);
        newGameCustom.setOnClickListener(this);
        settings.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v == newGameDR){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        if(v == newGameCustom){

        }
        if(v == settings){
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
        }

    }
}
