package com.example.colorscheme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText song, artist;
    Button colorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorButton = findViewById(R.id.color_button);
        artist = findViewById(R.id.input_artist);
        song = findViewById(R.id.input_song);

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output = song.getText().toString() + " by " + artist.getText().toString();
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                intent.putExtra("message", output);
                startActivity(intent);

                //intent.putExtra(inputArtist.getEditText().toString(), artist);
                //intent.putExtra(inputSong.getEditText().toString(), song);
            }
        });
    }

    /*
    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }*/
}