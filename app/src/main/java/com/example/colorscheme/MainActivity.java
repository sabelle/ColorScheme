package com.example.colorscheme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.omt.lyrics.SearchLyrics;
import com.omt.lyrics.beans.Lyrics;
import com.omt.lyrics.beans.LyricsServiceBean;
import com.omt.lyrics.beans.SearchLyricsBean;
import com.omt.lyrics.exception.SearchLyricsException;

import java.util.List;

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

                //for the heading on colorActivity page and to print out lyrics
                intent.putExtra("message", song.getText().toString() + " by " + artist.getText().toString() + "\n"
                        + getLyrics());
                startActivity(intent);
            }
        });
    }
    /** Call API to generate lyrics
     */
    public String getLyrics() {
        // call API to generate lyrics
        SearchLyrics searchLyrics = new SearchLyrics();
        LyricsServiceBean bean = new LyricsServiceBean();
        bean.setSongName(song.getText().toString());
        bean.setSongArtist(artist.getText().toString());
        List<Lyrics> lyrics;
        try {
            lyrics = searchLyrics.searchLyrics(bean);
            for (Lyrics lyric : lyrics) {
                return lyric.getText();
            }
        } catch (SearchLyricsException e) {
            e.printStackTrace();
            return "Song and corresponding artist does not exist.\nPlease double check song title and artist name and try again.";
        }
        return null;
    }
}