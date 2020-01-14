package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Galgelogik game = new Galgelogik();
    private Button guessButton, restartButton, giveUpButton;
    private TextView guessedCorrectLetters, guessedWrongLetters, info;
    private String correctWord, guessedWord, guessedTotalLetters = "", guessedWrongString = "";
    private ImageView imageView;
    private EditText guess;
    SharedPreferences preferences;
    String colour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        game.nulstil();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        colour = preferences.getString("colour","GREEN");
        if(colour.equalsIgnoreCase("Green")){
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        } else if (colour.equalsIgnoreCase("white")){
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        } else if (colour.equalsIgnoreCase("red")){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }

        guess = findViewById(R.id.guess);

        guessedCorrectLetters = findViewById(R.id.guessedRightLetters);
        guessedWrongLetters = findViewById(R.id.guessedWrongLettersDisplay);
        guessedCorrectLetters.setText(game.getSynligtOrd());
        info = findViewById(R.id.info);

        guessButton = findViewById(R.id.guessButton);
        guessButton.setOnClickListener(this);
        restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(this);
        giveUpButton = findViewById(R.id.giveUpButton);
        giveUpButton.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);

        //Henter ord fra DR's server er taget fra vejledningen fra Underviser Jacob Nordfalk

        info.setText("Henter ord fra DRs server....");
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    game.hentOrdFraDr();
                    return "Ordene blev korrekt hentet fra DR's server";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: "+e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                info.setText("resultat: \n" + resultat);
                game.opdaterSynligtOrd();
            }
        }.execute();
    }

    public void onClick(View v) {

        if (v == guessButton) {
            boolean b = isLetterUsed(guess.getText().toString());
            game.gætBogstav(guess.getText().toString());
            guessedCorrectLetters.setText(game.getSynligtOrd());
            imageViewChanger();
            if (b) {
                Toast.makeText(this, "Du har allerade gættede " + guess.getText().toString(), Toast.LENGTH_SHORT).show();
            } else if (!game.erSidsteBogstavKorrekt() && game.getAntalForkerteBogstaver() < 6) {
                Toast.makeText(this, "Du har gættede forkert " + game.getAntalForkerteBogstaver() + " gang(e)", Toast.LENGTH_SHORT).show();
                guessedWrongString += " " + guess.getText().toString();
                guessedWrongLetters.setText(guessedWrongString);
            } else if (game.erSidsteBogstavKorrekt() && !game.erSpilletVundet()) {
                Toast.makeText(this, "Du gættede rigtigt: '" + guess.getText().toString().toUpperCase() + "' er i ordet.", Toast.LENGTH_SHORT).show();
            } else if (game.erSpilletVundet()) {
                Intent winIntent = new Intent(this, WinningActivity.class);
                winIntent.putExtra("mistakes",game.getAntalForkerteBogstaver());
                winIntent.putExtra("word",game.getOrdet());
                startActivity(winIntent);
            } else if (game.erSpilletTabt()) {
                Intent loseIntent = new Intent(this,LosingActivity.class);
                loseIntent.putExtra("losingWord",game.getOrdet());
                startActivity(loseIntent);
            }
            guess.setText("");
        } else if (v == giveUpButton) {
            Intent loseIntent = new Intent(this,LosingActivity.class);
            loseIntent.putExtra("losingWord",game.getOrdet());
            startActivity(loseIntent);
        } else if (v == restartButton) {
            Toast.makeText(this, "Nyt spil!", Toast.LENGTH_SHORT).show();
            game.nulstil();
            imageViewChanger();
            guessedCorrectLetters.setText(game.getSynligtOrd());
            guessedWrongLetters.setText(guessedWrongString = "");
        }
        info.setText("");
    }

    public void imageViewChanger() {
        if (game.getAntalForkerteBogstaver() == 0) {
            imageView.setImageResource(R.drawable.galge);
        }
        if (game.getAntalForkerteBogstaver() == 1) {
            imageView.setImageResource(R.drawable.forkert1);
        }
        if (game.getAntalForkerteBogstaver() == 2) {
            imageView.setImageResource(R.drawable.forkert2);
        }
        if (game.getAntalForkerteBogstaver() == 3) {
            imageView.setImageResource(R.drawable.forkert3);
        }
        if (game.getAntalForkerteBogstaver() == 4) {
            imageView.setImageResource(R.drawable.forkert4);
        }
        if (game.getAntalForkerteBogstaver() == 5) {
            imageView.setImageResource(R.drawable.forkert5);
        }
        if (game.getAntalForkerteBogstaver() == 6) {
            imageView.setImageResource(R.drawable.forkert6);
        }
    }

    public boolean isLetterUsed(String letter){
        boolean b = false;
        for(String s: game.getBrugteBogstaver()){
            if(s.equalsIgnoreCase(letter)){
                b = true;
            }
        }
        return b;
    }

}
 /*public String wordDisplayer(String word){
        String[] wordArray = new String[word.length()];
        String blankWord = "";
        for(String s: wordArray){
            blankWord += "_";
        }
        return blankWord;
    }

    public String correctGuess(String word, String letter, String guessedWord){
        String processedWord = "";
        String[] correctWord = stringToArray(word);
        String[] guessWord = stringToArray(guessedWord);
        int i = 0;
        for(String s: correctWord){
            if(correctWord[i].equalsIgnoreCase(letter)){
                guessWord[i] = letter;
                i++;
            }
        }
        for(String s: guessWord){
            processedWord += s;
        }
        return processedWord;
    }

    public String[] stringToArray(String word) {
        String[] wordArray = new String[word.length()];
        int i = 0;
        for (String s : wordArray) {
            s = word.substring(i);
            i++;
        }
        return wordArray;
    }*/