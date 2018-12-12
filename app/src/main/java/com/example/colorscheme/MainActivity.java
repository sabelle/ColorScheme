package com.example.colorscheme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

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

    /** Song title as a string, and capitalized */
    String strSong;

    /** Song artist as a string, and capitalized */
    String strArtist;

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Color Scheme";

    /** String of lyrics. */
    private static String lyrics;

    /** Hexadecimal representation of lyrics. */
    public static String hexa1;

    /** Hexadecimal representation of song title. */
    public static String hexa2;

    /** Hexadecimal representation of song artist. */
    public static String hexa3;

    /**
     * Run when this activity comes to the foreground.
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);

        colorButton = findViewById(R.id.color_button);
        artist = findViewById(R.id.input_artist);
        song = findViewById(R.id.input_song);

        final Button startAPICall = findViewById(R.id.color_button);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reformat song and artist to have uppercase first letters of each word
                strSong = song.getText().toString();
                strArtist = artist.getText().toString();
                reformatInput(strSong, "song");
                reformatInput(strArtist, "artist");
                //start API call
                startAPICall();
            }
        });
    }

    /** Call the lyrics API */
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    //"https://api.lyrics.ovh/v1/\" + artist + \"/\" + song + \"/json",
                    "https://api.lyrics.ovh/v1/" + strArtist + "/" + strSong,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            setLyricsAndHexa(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Intent errorIntent = new Intent(MainActivity.this, ColorActivity.class);
                    errorIntent.putExtra("error", "Error: No lyrics found. Please double check the spelling of the song title and artist.");
                    startActivity(errorIntent);
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Sets the lyrics
     * @param inputLyrics the lyrics
     */
    public void setLyricsAndHexa(String inputLyrics) {
        Intent intent = new Intent(MainActivity.this, ColorActivity.class);
        intent.putExtra("message", strSong + " by " + strArtist);
        lyrics = inputLyrics;
        hexa1 = "#" +  Integer.toHexString(song.getText().toString().hashCode()).toUpperCase();
        hexa2 = "#" + Integer.toHexString(artist.getText().toString().hashCode()).toUpperCase();
        hexa3 = "#" + Integer.toHexString(lyrics.hashCode()).toUpperCase();
        startActivity(intent);
    }
    /**
     * Reformats the song and artist name. The first letter of each word is capitalized.
     * @param input the input string, either the song name or artist name
     * @param type the type of string (song or artist)
     */
    public void reformatInput(String input, String type) {
        for (int i = 0; i < input.length() - 2; i++) {
            if (i == 0) {
                input = input.substring(0, 1).toUpperCase()
                        + input.substring(1);
            } else if (input.substring(i, i + 1).equals(" ")) {
                input = input.substring(0, i + 1)
                        + input.substring(i + 1, i + 2).toUpperCase()
                        + input.substring(i + 2);
            }
        }
        if (type.equals("song")) {
            strSong = input;
        } else {
            strArtist = input;
        }
    }
}