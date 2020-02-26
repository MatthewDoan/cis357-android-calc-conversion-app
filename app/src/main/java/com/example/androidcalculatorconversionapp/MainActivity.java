package com.example.androidcalculatorconversionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent  = getIntent();

        String str = intent.getStringExtra("message_key");
        String str2 = intent.getStringExtra("message_key2");

        TextView fromText = (TextView) findViewById(R.id.gallonsText);
        TextView toText = (TextView) findViewById(R.id.metersText);

        if(str != null && str2 != null && !str.equals(str2)) {
            fromText.setText(str);
            toText.setText(str2);
        }
        else{
            fromText.setText("Yards");
            toText.setText("Meters");
        }
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
                if (!(yards.getText().toString()).matches("") && (meters.getText().toString()).matches("") && fromText.getText().toString().equals("Yards") && toText.getText().toString().equals("Meters")) {
                    meters.setText("");
                    int yardsInt = Integer.parseInt(yards.getText().toString());
                    double conversion = yardsInt * 0.9144;
                    meters.setText(Double.toString(conversion));
                }
                else if (!(yards.getText().toString()).matches("") && (meters.getText().toString()).matches("") && fromText.getText().toString().equals("Yards") && toText.getText().toString().equals("Feet")) {
                    meters.setText("");
                    int yardsInt = Integer.parseInt(yards.getText().toString());
                    double conversion = yardsInt * 3.000;
                    meters.setText(Double.toString(conversion));
                }
                else if (!(yards.getText().toString()).matches("") && (meters.getText().toString()).matches("") && fromText.getText().toString().equals("Meters") && toText.getText().toString().equals("Yards")) {
                    meters.setText("");
                    int yardsInt = Integer.parseInt(yards.getText().toString());
                    double conversion = yardsInt * 1.09361;
                    meters.setText(Double.toString(conversion));
                }
                else if (!(yards.getText().toString()).matches("") && (meters.getText().toString()).matches("") && fromText.getText().toString().equals("Meters") && toText.getText().toString().equals("Feet")) {
                    meters.setText("");
                    int yardsInt = Integer.parseInt(yards.getText().toString());
                    double conversion = yardsInt * 3.28084;
                    meters.setText(Double.toString(conversion));
                }
                else if (!(yards.getText().toString()).matches("") && (meters.getText().toString()).matches("") && fromText.getText().toString().equals("Feet") && toText.getText().toString().equals("Yards")) {
                    meters.setText("");
                    int yardsInt = Integer.parseInt(yards.getText().toString());
                    double conversion = yardsInt * .3333;
                    meters.setText(Double.toString(conversion));
                }
                else if (!(yards.getText().toString()).matches("") && (meters.getText().toString()).matches("") && fromText.getText().toString().equals("Feet") && toText.getText().toString().equals("Meters")) {
                    meters.setText("");
                    int yardsInt = Integer.parseInt(yards.getText().toString());
                    double conversion = yardsInt * 0.348;
                    meters.setText(Double.toString(conversion));
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
        Intent settings = new Intent(this, SettingsActivity.class);
        String flagTest = "2";
        String flag = flagTest.toString();
        settings.putExtra("flagKey", flag);
        startActivity(settings);
        return super.onOptionsItemSelected(item);
    }
}
