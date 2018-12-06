package com.example.colorscheme;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.omt.lyrics.SearchLyrics;
import com.omt.lyrics.beans.Lyrics;
import com.omt.lyrics.beans.LyricsServiceBean;
import com.omt.lyrics.beans.SearchLyricsBean;
import com.omt.lyrics.exception.SearchLyricsException;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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

//                //for the heading on colorActivity page and to print out lyrics
                intent.putExtra("message", song.getText().toString() + " by " + artist.getText().toString());
//                + "\n"
//                        + getLyrics());


                startActivity(intent);
                MyAsyncTask asyncTask = new MyAsyncTask();//
                /* new MyAsyncTask(new MyAsyncTask.AsyncResponse() {

                    //@Override
                    public void processFinish(String output) {
                        Log.d("Async task response: ", (output));
                        colorButton.setText(output);
                    }
                });*/
                try {
                    asyncTask.startAPICall(song.getText().toString(), artist.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //asyncTask.execute();
            }
        });
    }
}