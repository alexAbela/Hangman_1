package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    TextView settingsText;
    Button save, buttonRed, buttonGreen, buttonWhite, buttonBack;
    String colour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsText = findViewById(R.id.settingsText);
        settingsText.setText("Hvilken farve vil du have dit spil?");

        save = findViewById(R.id.saveButton);
        save.setOnClickListener(this);
        buttonGreen = findViewById(R.id.buttonColourGreen);
        buttonGreen.setOnClickListener(this);
        buttonRed = findViewById(R.id.buttonColourRed);
        buttonRed.setOnClickListener(this);
        buttonWhite = findViewById(R.id.buttonColourWhite);
        buttonWhite.setOnClickListener(this);
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this); // i et fragment, skal der stå "getActivity()" i stedet for "this"



    }

    @Override
    public void onClick(View view) {

        if (view == buttonGreen){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            colour = "GREEN";
        } else if (view == buttonRed){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
            colour = "RED";
        } else if (view == buttonWhite){
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            colour = "WHITE";
        } else if (view == buttonBack){
            goBack();
        } else if (view == save){
            saveColour(colour);
        }
    }

    public void goBack(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void saveColour(String colour){
        if(colour.equalsIgnoreCase("GREEN")){
            preferences.edit().putString("colour","Green").apply();
        } else if(colour.equalsIgnoreCase("RED")){
            preferences.edit().putString("colour","Red").apply();
        } else if (colour.equalsIgnoreCase("WHITE")){
            preferences.edit().putString("colour","White").apply();
        }
        goBack();
    }


}
