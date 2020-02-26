package com.example.androidcalculatorconversionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //input fields
        EditText yards = (EditText) findViewById(R.id.yardsField);
        EditText meters = (EditText) findViewById(R.id.metersField);

        //buttons
        Button calculate = (Button) findViewById(R.id.calculateButton);
        Button clear = (Button) findViewById(R.id.clearButton);
        Button mode = (Button) findViewById(R.id.modeButton);

        View background = (View) findViewById(R.id.background);
        View volumeBackground = (View) findViewById(R.id.volumeBackground);

        clear.setOnClickListener(x -> {
            yards.setText("");
            meters.setText("");
        });

        calculate.setOnClickListener(y -> {
            try {
                //yards to meters
                if (!(yards.getText().toString()).matches("") && (meters.getText().toString()).matches("")) {
                    meters.setText("");
                    int yardsInt = Integer.parseInt(yards.getText().toString());
                    double conversion = yardsInt * 0.9144;
                    meters.setText(Double.toString(conversion));
                }
//                //meters to yards
                else if ((yards.getText().toString()).matches("") && !(meters.getText().toString()).matches("")) {
                    yards.setText("");
                    int metersInt = Integer.parseInt(meters.getText().toString());
                    double conversions = metersInt * 1.09361;
                    yards.setText(Double.toString(conversions));
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid input!");
            }
            hideKeyboard(background);
        });

        mode.setOnClickListener(z -> {
            openActivity(volumeBackground);
        });
    }

    public void openActivity(View view) {
        Intent intent = new Intent(this, VolumeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
