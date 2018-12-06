package com.example.colorscheme;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;



/**
 * Second page of app, displays color palette created.
 */
public class ColorActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "ColorScheme";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    /** Message at top of screen. */
    TextView message;



    /**
     * Run when this activity comes to the foreground.
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);


        //text display "Song" by Artist
        message = findViewById(R.id.message);
        message.setText(getIntent().getStringExtra("message"));

//        //API implementation
//        //https://api.lyrics.ovh/v1/artist/song
//        String song = getIntent().getStringExtra("song");
//        String artist = getIntent().getStringExtra("artist");

    }
    public String getArtist() {
        message = findViewById(R.id.message);
        message.setText(getIntent().getStringExtra("message"));
        return getIntent().getStringExtra("artist");
    }
    public String getSong() {
        message = findViewById(R.id.message);
        message.setText(getIntent().getStringExtra("message"));
        return getIntent().getStringExtra("song");
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }
}

