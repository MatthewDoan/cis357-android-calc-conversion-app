package com.example.androidcalculatorconversionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.settings_activity);

        Spinner spinner = findViewById(R.id.fromSpinner);
        Spinner spinner2 = findViewById(R.id.toSpinner);
        ArrayList<String> arrayList = new ArrayList<>();

        Intent settings  = getIntent();
        String flagCheck = settings.getStringExtra("flagKey");



        if(flagCheck.equals("1")) {
            arrayList.add("Liters");
            arrayList.add("Gallons");
            arrayList.add("Quarts");
        }
        if(flagCheck.equals("2")) {
            arrayList.add("Yards");
            arrayList.add("Meters");
            arrayList.add("Feet");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button saveButton= (Button)findViewById(R.id.settingsSave);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                // get the value which input by user in EditText
                // and convert it to string
                String send_text1 = spinner.getSelectedItem().toString();
                String send_text2 = spinner2.getSelectedItem().toString();

                // Create the Intent object of this class Context() to Second_activity class
                if(flagCheck.equals("1")) {
                    Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
                    // now by putExtra method put the value in key, value pair
                    // key is message_key by this key we will receive the value, and put the string
                    intent.putExtra("message_key", send_text1);
                    intent.putExtra("message_key2", send_text2);

                    // start the Intent
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("message_key", send_text1);
                    intent.putExtra("message_key2", send_text2);

                    startActivity(intent);
                }

            }
    });
}


}