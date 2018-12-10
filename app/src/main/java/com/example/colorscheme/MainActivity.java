package com.example.colorscheme;

import android.content.Intent;
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

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Color Scheme";

    /** String of lyrics */
    private static String lyrics;

    /** Hexadecimal representation of lyrics */
    public static String hexa;

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
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                //for the API implementation
                intent.putExtra("song", song.getText().toString());
                intent.putExtra("artist", artist.getText().toString());
                // start API call and display lyrics
                startAPICall();
                //for the heading on colorActivity page and to print out lyrics
                intent.putExtra("message", song.getText().toString() + " by " + artist.getText().toString());
                startActivity(intent);

            }
        });
    }

    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    //"https://api.lyrics.ovh/v1/\" + artist + \"/\" + song + \"/json",
                    "https://api.lyrics.ovh/v1/Adele/Hello",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            setLyricsAndHexa(response.toString());
                            System.out.println(lyrics);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
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
        lyrics = inputLyrics;
        hexa = Integer.toHexString(lyrics.hashCode()).toUpperCase();
        System.out.println("Hexa: " + hexa);
    }
}