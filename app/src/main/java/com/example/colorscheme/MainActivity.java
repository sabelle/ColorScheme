package com.example.colorscheme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Main class for app, takes the a song title & artist name.
 */
public class MainActivity extends AppCompatActivity {
    /** Variable for song title. */
    EditText song;

    /** Variable for artist name. */
    EditText artist;

    /** Variable for generate button. */
    Button colorButton;

    /**
     * Run when this activity comes to the foreground.
     * @param savedInstanceState unused
     */
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
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                //for the API implementation
                intent.putExtra("song", song.getText().toString());
                intent.putExtra("artist", artist.getText().toString());

                //for the heading on colorActivity page
                intent.putExtra("message", song.getText().toString() + " by " + artist.getText().toString());
                startActivity(intent);
            }
        });
    }
}