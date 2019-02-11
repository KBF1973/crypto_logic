package com.example.crypto_logic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import android.widget.*;
import java.util.Random;
import java.lang.*;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> secretWords;
    private String shuffledWord = "";
    private String OriginalWord;
    private String win;
    private int correct_guesses;
    private int incorrect_guesses;
    private EditText playersLetter;
    private TextView correctOrder;
    private TextView showValue;
    protected Button enter_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        secretWords = new ArrayList<>(Arrays.asList("APPLE", "BANANA", "CHERRY"));
        enter_text = findViewById(R.id.enter_text);
        playersLetter = findViewById(R.id.playersLetter);
        correctOrder = findViewById(R.id.correctOrder);
        showValue = findViewById(R.id.incorrect);
        correct_guesses = 0;
        incorrect_guesses = 0;
        win = "Congrats! You Did It!";

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                
            }
        });

        randomWord();

    }

    public void randomWord(){
        Random ran = new Random();
        int word = ran.nextInt(secretWords.size());
        OriginalWord = secretWords.get(word);
        ArrayList<String> splitWord = new ArrayList<>(Arrays.asList(secretWords.get(word).split("")));

        Collections.shuffle(splitWord);
        for (String c : splitWord)
            shuffledWord += c;
            ( (TextView) findViewById(R.id.mysteryWord)).setText(shuffledWord);

        }
    public void onButtonClick(View view) {


        //EditText cast to string
        String str = playersLetter.getText().toString().toLowerCase();

        //making sure the Original Word is cast to string
        String str2 = OriginalWord.toLowerCase();

        //checking to see if the players letter matches OriginalWord one character at a time
        if (str.charAt(0) == str2.charAt(correct_guesses)) {
            correct_guesses++;
            correctOrder.append(str.toUpperCase());
            playersLetter.getText().clear();


            } else {
                incorrect_guesses++;
                showValue.setText(Integer.toString(incorrect_guesses));
                playersLetter.getText().clear();
            }

        for(int i = 0; i < OriginalWord.length(); i++ )
            if(correctOrder.getText().toString().equals(OriginalWord)){
                ((TextView) findViewById(R.id.winningMessage)).setText(win);
            }

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
