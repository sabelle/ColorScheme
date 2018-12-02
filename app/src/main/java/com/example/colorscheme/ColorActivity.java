package com.example.colorscheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ColorActivity extends AppCompatActivity {
    TextView returnText; //user inputted text from Main Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        //text display "Song" by Artist
        returnText = findViewById(R.id.return_text);
        returnText.setText(getIntent().getStringExtra("message")); //getIntent().getStringExtra(output)


    }
}
