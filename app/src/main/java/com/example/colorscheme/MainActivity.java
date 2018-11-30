package com.example.colorscheme;

import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //TextInputLayout inputSong, inputArtist;
    Button getColors;
    String artist, song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getColors = findViewById(R.id.get_colors);
        //inputArtist = findViewById(R.id.input_artist);
        //inputSong = findViewById(R.id.input_song);

        getColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artist = findViewById(R.id.input_artist).toString();
                song = findViewById(R.id.input_song).toString();
            }
        });

    }
}
//nouns & verbs ~1000, adj ~2000, preps ~50, punc ~30
