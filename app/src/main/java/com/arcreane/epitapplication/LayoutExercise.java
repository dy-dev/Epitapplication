package com.arcreane.epitapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_exercise);

        RelativeLayout rl = findViewById(R.id.main);
        rl.setBackgroundColor(getResources().getColor(R.color.blue,getTheme()) );
    }
}