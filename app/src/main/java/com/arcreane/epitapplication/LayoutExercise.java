package com.arcreane.epitapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LayoutExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_exercise);

        RelativeLayout rl = findViewById(R.id.main);
        rl.setBackgroundColor(getResources().getColor(R.color.blue, getTheme()));
        String[] infos = {"toto", "titi", "tutu"};

        ListView lv = findViewById(R.id.MyListView);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                infos) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv1 = view.findViewById(android.R.id.text1);
                TextView tv2 = view.findViewById(android.R.id.text2);

                tv1.setText("Name : " + infos[position]);
                tv2.setText(infos[position].toUpperCase(Locale.ROOT));
                return view;
            }
        };
        lv.setAdapter(adapter);
    }
}