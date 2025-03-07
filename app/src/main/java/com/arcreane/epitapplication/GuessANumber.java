package com.arcreane.epitapplication;

import static android.widget.Toast.LENGTH_LONG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Result {
    private String m_sHint;
    private int m_iIndex;
    private int m_iValue;

    public Result(int index, int value, String hint) {
        m_sHint = hint;
        m_iIndex = index;
        m_iValue = value;
    }

    public int getIndex() {
        return m_iIndex;
    }


    public String getHint() {
        return m_sHint;
    }


    public int getValue() {
        return m_iValue;
    }

}

public class GuessANumber extends AppCompatActivity {
    private Difficulty m_eDifficulty;
    private boolean m_bSelectDiff;
    private Random rand;
    private int m_iNumberToGuess;
    private TextView m_HintTextView;
    private int m_iMaxNumberOfTries;
    private ProgressBar progressBar;
    private TextView progressTextView;

    private List<Result> m_UserTriesCollection;
    private ArrayAdapter<Result> UserInputs;

    private int m_iScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_a_number);
        m_iScore = 0;
        m_eDifficulty = Difficulty.EASY;
        rand = new Random();
        //Debug only to remove later (it avoids having to switch the layout visible / gone
        RadioButton rb = findViewById(R.id.medium_button);
        SetDifficulty(rb);

        m_UserTriesCollection = new ArrayList<>();

        m_iMaxNumberOfTries = 10;

        progressTextView = findViewById(R.id.progressTextView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(m_iMaxNumberOfTries);
        Button validateGuess = findViewById(R.id.ValidationButton);
        EditText guessText = findViewById(R.id.editTextNumber);
        guessText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0)
                    validateGuess.setEnabled(false);
                else
                    validateGuess.setEnabled(true);
            }
        });


        UserInputs = new ArrayAdapter(this, R.layout.layout_hint, R.id.hintTextView, m_UserTriesCollection) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView indexTV = view.findViewById(R.id.nbTriesTextView);
                int tmp = m_UserTriesCollection.get(position).getIndex();
                indexTV.setText(String.valueOf(tmp));

                TextView valueTV = view.findViewById(R.id.valueTextView);
                tmp = m_UserTriesCollection.get(position).getValue();
                valueTV.setText(String.valueOf(tmp));


                TextView hintTV = view.findViewById(R.id.hintTextView);
                String hint = m_UserTriesCollection.get(position).getHint();
                hintTV.setText(hint);

                return view;
            }
        };

        ListView hintListView = findViewById(R.id.hintListView);
        hintListView.setAdapter(UserInputs);

        m_HintTextView = findViewById(R.id.HintText);
        validateGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberProposed = Integer.parseInt(guessText.getText().toString());
                guessText.setText("");
                checkProposition(numberProposed);
            }
        });
        validateGuess.setEnabled(false);
    }

    private void checkProposition(int p_iNumberProposed) {
        progressBar.incrementProgressBy(1);
        progressTextView.setText(String.valueOf(progressBar.getProgress()));

        String hintText = "";
        boolean found = false;
        if (p_iNumberProposed > m_iNumberToGuess) {
            hintText = "TOO BIG";
        } else if (p_iNumberProposed < m_iNumberToGuess) {
            hintText = "TOO SMALL";
        } else {
            Toast.makeText(this, "Congrats !!! You found the number", LENGTH_LONG).show();
            found = true;
        }
        if (!found) {
            m_HintTextView.setText(hintText);
            m_UserTriesCollection.add(0, new Result(m_UserTriesCollection.size() + 1, p_iNumberProposed, hintText));
            UserInputs.notifyDataSetChanged();
        } else {
            m_UserTriesCollection.clear();
            m_iScore += m_iMaxNumberOfTries - progressBar.getProgress();
            TextView scoreView = findViewById(R.id.ScoreValueView);
            scoreView.setText(String.valueOf(m_iScore));
            progressBar.setProgress(0);
            progressTextView.setText(String.valueOf(progressBar.getProgress()));
            m_iNumberToGuess = rand.nextInt(m_eDifficulty.getValue());
            CheckBox diffNextRound = findViewById(R.id.select_diff_next_time);
            m_bSelectDiff = diffNextRound.isChecked();
            if(m_bSelectDiff)
            {
                RelativeLayout diff = findViewById(R.id.difficuly_layout);
                diff.setVisibility(View.VISIBLE);

                LinearLayout game_layout = findViewById(R.id.game_layout);
                game_layout.setVisibility(View.GONE);
            }
        }
    }

    public void SetDifficulty(View view) {
        RadioGroup rg = findViewById(R.id.difficulty_group);
        RadioButton selected = findViewById(rg.getCheckedRadioButtonId());
        switch (selected.getTag().toString()) {
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

        m_iNumberToGuess = rand.nextInt(m_eDifficulty.getValue());


        Toast.makeText(this, m_eDifficulty.toString(), LENGTH_LONG).show();

        RelativeLayout diff = findViewById(R.id.difficuly_layout);
        diff.setVisibility(View.GONE);

        LinearLayout game_layout = findViewById(R.id.game_layout);
        game_layout.setVisibility(View.VISIBLE);

    }


}