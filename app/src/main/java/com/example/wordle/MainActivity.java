package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.BindException;
import java.util.Arrays;
import java.util.Random;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private int i = 0;
    private int j = 0;
    private Button boxes[][];
    private String answer = "TRAIN";
    final Random myRandom = new Random();
    private int alphabet[] = new int[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Arrays.fill(alphabet, 0);
    }

    // Adds a new letter
    public void add(View view){
        if (j < 5) {
            String var = (String)((Button)view).getText();
            getBox(i, j).setText(var);
            j++;

            Log.d("success", var);
            Log.d("success", "Character Added");
        }
        else Log.d("fail", "Row Overflow");
    }

    // Deletes the last character
    public void delete(View view){
        if (j > 0) {
            j--;
            getBox(i, j).setText(" ");
        }
    }

    // Checks combo
    public void enter(View view) {
        if (i < 6) {
            String value = (String) getBox(i, 0).getText() + (String) getBox(i, 1).getText() + (String) getBox(i, 2).getText() + (String) getBox(i, 3).getText() + (String) getBox(i, 4).getText();
            if (j >= 5 && valid(value)) {
                if (value.equals(answer)) Win();
                for (int k = 0; k < 5; k++) {
                    String letter = (String) getBox(i, k).getText();
                    if (answer.contains(letter)) {
                        getBox(i, k).setBackgroundColor(Color.YELLOW);
                        if (alphabet[(int)letter.charAt(0)] != 3) {
                            getButton(letter).setBackgroundColor(Color.YELLOW);
                            alphabet[(int)letter.charAt(0)]=2;
                        }
                        if (k == answer.indexOf(letter)) {
                            getBox(i, k).setBackgroundColor(Color.GREEN);
                            getButton(letter).setBackgroundColor(Color.GREEN);
                            alphabet[(int)letter.charAt(0)]=3;
                        }
                    } else {
                        getBox(i, k).setBackgroundColor(Color.GRAY);
                        getButton(letter).setBackgroundColor(Color.GRAY);
                        alphabet[(int)letter.charAt(0)]=1;
                    }
                }
                i++;
                j = 0;
            }
            Log.d("success", "Value Checked");

        }
        else GameOver();
    }

    // TODO Make game explanation Screen
    public void help(View view){
        view.setEnabled(false);
    }

    // Clears a random letter
    public void clear(View view){
        while(true) {
            int ASCII_value = myRandom.nextInt(26);
            ASCII_value += 65;
            String letter = Character.toString((char) ASCII_value);
            if (alphabet[ASCII_value] == 0 || checkAlphabet()){
                Log.d("Alphabet", letter + " " + ASCII_value);
                if (answer.contains(letter)) {
                    if (alphabet[ASCII_value] != 3) {
                        getButton(letter).setBackgroundColor(Color.YELLOW);
                        alphabet[ASCII_value] = 2;
                    }
                }
                else getButton(letter).setBackgroundColor(Color.GRAY);
                alphabet[ASCII_value] = 1;
                break;
            }
        }
    }
    // TODO Make a home page
    public void home(View view){
        view.setEnabled(false);
    }

    // TODO Make a GameOver Screen
    public void GameOver(){
        Log.d("success", "GameOver");
    }
    // TODO Make a Win Screen
    public void Win(){
        Log.d("success", "Win");
    }

    // TODO Integrate word list and check if input is valid
    public boolean valid(String value){
        //Character.toString(value.charAt(2))
        //getBox(5,2).setText(Character.toString(value.charAt(2)));
        Log.d("success", value);
        return true;
    }

    // Values above 0
    public boolean checkAlphabet(){
        int count = 0;
        for (int k = 65; k <= 90; k++) {
            if(alphabet[k] > 0) count++;
        }
        if (count >= 26) return true;
        return false;
    }

    // Gets Reference for the needed box
    public Button getBox(int i, int j){
        if(i == 0 && j == 0) return ((Button)findViewById(R.id.box_00));
        if(i == 0 && j == 1) return ((Button)findViewById(R.id.box_01));
        if(i == 0 && j == 2) return ((Button)findViewById(R.id.box_02));
        if(i == 0 && j == 3) return ((Button)findViewById(R.id.box_03));
        if(i == 0 && j == 4) return ((Button)findViewById(R.id.box_04));
        if(i == 1 && j == 0) return ((Button)findViewById(R.id.box_10));
        if(i == 1 && j == 1) return ((Button)findViewById(R.id.box_11));
        if(i == 1 && j == 2) return ((Button)findViewById(R.id.box_12));
        if(i == 1 && j == 3) return ((Button)findViewById(R.id.box_13));
        if(i == 1 && j == 4) return ((Button)findViewById(R.id.box_14));
        if(i == 2 && j == 0) return ((Button)findViewById(R.id.box_20));
        if(i == 2 && j == 1) return ((Button)findViewById(R.id.box_21));
        if(i == 2 && j == 2) return ((Button)findViewById(R.id.box_22));
        if(i == 2 && j == 3) return ((Button)findViewById(R.id.box_23));
        if(i == 2 && j == 4) return ((Button)findViewById(R.id.box_24));
        if(i == 3 && j == 0) return ((Button)findViewById(R.id.box_30));
        if(i == 3 && j == 1) return ((Button)findViewById(R.id.box_31));
        if(i == 3 && j == 2) return ((Button)findViewById(R.id.box_32));
        if(i == 3 && j == 3) return ((Button)findViewById(R.id.box_33));
        if(i == 3 && j == 4) return ((Button)findViewById(R.id.box_34));
        if(i == 4 && j == 0) return ((Button)findViewById(R.id.box_40));
        if(i == 4 && j == 1) return ((Button)findViewById(R.id.box_41));
        if(i == 4 && j == 2) return ((Button)findViewById(R.id.box_42));
        if(i == 4 && j == 3) return ((Button)findViewById(R.id.box_43));
        if(i == 4 && j == 4) return ((Button)findViewById(R.id.box_44));
        if(i == 5 && j == 0) return ((Button)findViewById(R.id.box_50));
        if(i == 5 && j == 1) return ((Button)findViewById(R.id.box_51));
        if(i == 5 && j == 2) return ((Button)findViewById(R.id.box_52));
        if(i == 5 && j == 3) return ((Button)findViewById(R.id.box_53));
        if(i == 5 && j == 4) return ((Button)findViewById(R.id.box_54));
        return null;
    }
    // Gets Reference for the needed button
    public Button getButton(String value){
        if(value.equals("Q")) return (Button)findViewById(R.id.btn_Q);
        if(value.equals("W")) return (Button)findViewById(R.id.btn_W);
        if(value.equals("E")) return (Button)findViewById(R.id.btn_E);
        if(value.equals("R")) return (Button)findViewById(R.id.btn_R);
        if(value.equals("T")) return (Button)findViewById(R.id.btn_T);
        if(value.equals("Y")) return (Button)findViewById(R.id.btn_Y);
        if(value.equals("U")) return (Button)findViewById(R.id.btn_U);
        if(value.equals("I")) return (Button)findViewById(R.id.btn_I);
        if(value.equals("O")) return (Button)findViewById(R.id.btn_O);
        if(value.equals("P")) return (Button)findViewById(R.id.btn_P);
        if(value.equals("A")) return (Button)findViewById(R.id.btn_A);
        if(value.equals("S")) return (Button)findViewById(R.id.btn_S);
        if(value.equals("D")) return (Button)findViewById(R.id.btn_D);
        if(value.equals("F")) return (Button)findViewById(R.id.btn_F);
        if(value.equals("G")) return (Button)findViewById(R.id.btn_G);
        if(value.equals("H")) return (Button)findViewById(R.id.btn_H);
        if(value.equals("J")) return (Button)findViewById(R.id.btn_J);
        if(value.equals("K")) return (Button)findViewById(R.id.btn_K);
        if(value.equals("L")) return (Button)findViewById(R.id.btn_L);
        if(value.equals("Z")) return (Button)findViewById(R.id.btn_Z);
        if(value.equals("X")) return (Button)findViewById(R.id.btn_X);
        if(value.equals("C")) return (Button)findViewById(R.id.btn_C);
        if(value.equals("V")) return (Button)findViewById(R.id.btn_V);
        if(value.equals("B")) return (Button)findViewById(R.id.btn_B);
        if(value.equals("N")) return (Button)findViewById(R.id.btn_N);
        if(value.equals("M")) return (Button)findViewById(R.id.btn_M);
        return null;
    }


}