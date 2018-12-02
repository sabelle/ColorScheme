package com.example.colorscheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;


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

        //API implementation
        //https://api.lyrics.ovh/v1/artist/song
        String song = getIntent().getStringExtra("song");
        String artist = getIntent().getStringExtra("artist");

        // Setting up queue for API request
        requestQueue = Volley.newRequestQueue(this);
        startAPICall(song, artist);
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the lyrics API.
     * @param song title
     * @param artist name
     */
    void startAPICall(final String song, final String artist) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.lyrics.ovh/v1/" + artist + "/" + song + "/json",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle response from lyrics API.
     * @param response from lyrics API.
     */
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            Log.i(TAG, response.get("host").toString());
        } catch (JSONException ignored) { }
    }
}
