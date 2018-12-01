package com.example.colorscheme;

import android.content.Intent;
//import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    TextInputLayout inputSong, inputArtist;
    Button colorButton;
    String artist, song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorButton = findViewById(R.id.color_button);
        inputArtist = findViewById(R.id.input_artist);
        inputSong = findViewById(R.id.input_song);

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);
                intent.putExtra(inputArtist.getEditText().toString(), artist);
                intent.putExtra(inputSong.getEditText().toString(), song);
                //artist = inputArtist.getEditText().toString();
                //song = inputSong.getEditText().toString();
            }
        });
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }
}
//nouns & verbs ~1000, adj ~2000, preps ~50, punc ~30
