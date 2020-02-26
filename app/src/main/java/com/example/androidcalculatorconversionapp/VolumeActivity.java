package com.example.androidcalculatorconversionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class VolumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume_activity);

        //input fields
        EditText gallons = (EditText) findViewById(R.id.gallonsField);
        EditText liters = (EditText) findViewById(R.id.litersField);

        //buttons
        Button calculateVol = (Button) findViewById(R.id.calculateButtonVolume);
        Button clearVol = (Button) findViewById(R.id.clearButtonVolume);
        Button modeVol = (Button) findViewById(R.id.modeButtonVolume);

        View backgroundVol = (View) findViewById(R.id.background);
        View volumeBackgroundVol = (View) findViewById(R.id.volumeBackground);

        clearVol.setOnClickListener(x -> {
            gallons.setText("");
            liters.setText("");
        });

        calculateVol.setOnClickListener(y -> {
            try {
                //gallons to liters
                if(!(gallons.getText().toString()).matches("") && (liters.getText().toString()).matches("")){
                    liters.setText("");
                    int gallonsInt = Integer.parseInt(gallons.getText().toString());
                    double conversion = gallonsInt * 3.78541;
                    liters.setText(Double.toString(conversion));
                }
                //liters to gallons
                else if((gallons.getText().toString()).matches("") && !(liters.getText().toString()).matches("")) {
                    gallons.setText("");
                    int litersInt = Integer.parseInt(liters.getText().toString());
                    double conversions = litersInt * 0.264172;
                    gallons.setText(Double.toString(conversions));
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid input!");
            }
            hideKeyboard(volumeBackgroundVol);
        });

        modeVol.setOnClickListener(z -> {
            openActivity(backgroundVol);
        });
    }

    public void openActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void hideKeyboard (View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
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
