package com.example.colorscheme;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ColorActivity extends AppCompatActivity {
    TextView returnText; //user inputted text from Main Activity
    MainActivity mainActivity = new MainActivity();
    String output = mainActivity.getSong() + " by " + mainActivity.getArtist();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        returnText = findViewById(R.id.return_text);
        returnText.setText(output); //getIntent().getStringExtra(output)
    }
}
