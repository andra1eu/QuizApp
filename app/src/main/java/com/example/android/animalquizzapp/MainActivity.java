package com.example.android.animalquizzapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CompoundButton> buttons = new ArrayList<>();
    private float score = 0;
    EditText textEntryAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textEntryAnswer = (EditText) findViewById(R.id.lizard_answer);


        setupButtons(R.id.wolf_1_button, R.id.wolf_2_button, R.id.wolf_3_button, R.id.eleph_1_button,
                R.id.eleph_2_button, R.id.eleph_3_button, R.id.cat_1_button, R.id.cat_2_button,
                R.id.cat_3_button, R.id.crow_1_button, R.id.crow_2_button, R.id.crow_3_button,
                R.id.monkey_1_button, R.id.monkey_2_button, R.id.monkey_3_button);
    }

    public void setupButtons(@IdRes int... ids) {
        for (int id : ids) buttons.add((CompoundButton) ButterKnife.findById(this, id));
    }

    public void submitAnswer(View view) {
        String textEntry = textEntryAnswer.getText().toString();
        if (!textEntry.isEmpty() && textEntry.equalsIgnoreCase("insects")) {
            score += 5;
            textEntryAnswer.setBackgroundColor(Color.parseColor("#009000"));
        } else {
            textEntryAnswer.setBackgroundColor(Color.parseColor("#900000"));
        }
        for (CompoundButton button : buttons) {
            if (button.getTag() != null)
                if (button.isChecked()) {
                    score += 5;
                    if (button instanceof CheckBox)
                        score -= 2.5;
                    button.setBackgroundColor(Color.parseColor("#009000"));
                } else {
                    button.setBackgroundColor(Color.parseColor("#900000"));
                }
        }

        TextView textView = (TextView) findViewById(R.id.textViewAnswer);
        Toast.makeText(this, "You made " + score + " out of 30", Toast.LENGTH_SHORT).show();
        textView.setText("Each answer equals 5 point.\nYou made " + score + " out of 30");
        score = 0;
    }


    public void sendMail(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String comment = editText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:andralung@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding the quiz");
        intent.putExtra(Intent.EXTRA_TEXT, comment);
        if (!getPackageManager().queryIntentActivities(intent, 0).isEmpty())
            startActivity(intent);
    }
}
