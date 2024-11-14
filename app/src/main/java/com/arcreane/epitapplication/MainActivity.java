package com.arcreane.epitapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String val = getResources().getString(R.string.prog1);
        val += "whatever!!";
        TextView first = findViewById(R.id.textViewProg1);
        first.setText(val);

        TextView second = findViewById(R.id.textViewProg2);
        second.setText(getResources().getString(R.string.prog2));


    }
}