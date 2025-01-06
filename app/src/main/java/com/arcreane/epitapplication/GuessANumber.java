package com.arcreane.epitapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GuessANumber extends AppCompatActivity {
    private Difficulty m_eDifficulty;
    private boolean m_bSelectDiff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_a_number);
        m_eDifficulty = Difficulty.EASY;

    }

    public void SetDifficulty(View view) {
        RadioGroup rg = findViewById(R.id.difficulty_group);
        RadioButton selected = findViewById(rg.getCheckedRadioButtonId());
        switch (selected.getTag().toString())
        {
            case "Easy":
                m_eDifficulty = Difficulty.EASY;
                break;
            case "Medium":
                m_eDifficulty = Difficulty.MEDIUM;
                break;
            case "Hard":
                m_eDifficulty = Difficulty.HARD;
                break;
            default:
                m_eDifficulty = Difficulty.EASY;
                break;
        }
        CheckBox diffNextRound = findViewById(R.id.select_diff_next_time);
        m_bSelectDiff = diffNextRound.isSelected();
        Toast.makeText(this, m_eDifficulty.toString(),Toast.LENGTH_LONG).show();

        RelativeLayout diff = findViewById(R.id.difficuly_layout);
        diff.setVisibility(View.GONE);

        LinearLayout game_layout = findViewById(R.id.game_layout);
        game_layout.setVisibility(View.VISIBLE);

    }
}